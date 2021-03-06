package com.shangyong.thjdq.service.impl;

import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.rest.feign.OrderUserCloudHystrixService;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.JdqConstant;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.dao.*;
import com.shangyong.thjdq.dto.*;
import com.shangyong.thjdq.entity.*;
import com.shangyong.thjdq.enums.CompanyIndustryEnum;
import com.shangyong.thjdq.enums.UserChannelEnum;
import com.shangyong.thjdq.event.PushRiskEvent;
import com.shangyong.thjdq.service.*;
import com.shangyong.thjdq.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ybds on 2019-03-21.
 */
@Service
public class RiskJdqServiceImpl implements RiskJdqService {

    private static final Logger log = LoggerFactory.getLogger(RiskJdqServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserCompanyInfoMapper userCompanyInfoMapper;
    @Autowired
    private UserAddressBookMapper userAddressBookMapper;
    @Autowired
    private UserContactMapper userContactMapper;
    @Autowired
    private RiskService riskService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    private MongoService mongoService;
    @Autowired
    private JdqCreateOrderPushEventService jdqCreateOrderPushEventService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OrderUserCloudHystrixService orderUserCloudHystrixService;
    @Autowired
    private BatchRedisTemplate batchRedisTemplate;
    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean jdqPushUserInfoToRisk(UserInfo userInfo) {

        String jdqOrderId = userInfo.getJdqOrderId();
        try {

            String appSerialNumber = "jdq_" + UUIDUtils.getUUID();
            // 更新用户信息为审核初始状态
            if(!userInfoService.pushUserInfoToWaitStatus(userInfo.getUserInfoId(), jdqOrderId, appSerialNumber)) {
                log.error("无需推送给风控 {}", jdqOrderId);
                return false;
            }

            // 校验下同一个身份证不需要重复推送给风控
            String idNumberRedisKey = RedisKeyCoreConstant.JDQ_JOB.PUSH_RISK_IN_NUMBER_REPEAT + userInfo.getIdNumber();
            if(!batchRedisTemplate.setNX(idNumberRedisKey, userInfo.getJdqOrderId(), 30, TimeUnit.DAYS)) {
                // 校验下订单号是否一致，如果不一致，则不推
                if (!batchRedisTemplate.get(idNumberRedisKey).equals(userInfo.getJdqOrderId())) {
                    userInfoService.pushUserInfoToRepeatStatus(userInfo.getUserInfoId(), jdqOrderId);
                    return true;
                }
            }

            // 用户基础信息
            UserBaseInfoDto userBaseInfoDto = new UserBaseInfoDto();
            userBaseInfoDto.setAppId(jdqCommonProperties.getRiskAppId());
            userBaseInfoDto.setAppName(jdqCommonProperties.getRiskAppName());
            userBaseInfoDto.setSource("0");
            userBaseInfoDto.setAppChannel(UserChannelEnum.JDQ.getName());
            userBaseInfoDto.setAppSerialNumber(appSerialNumber);

            // 查询魔蝎报文数据
            MongoDto mongoDto  = mongoService.findOne(new Query(Criteria.where("mongoId").is(userInfo.getJdqOrderId())), MongoDto.class, JdqConstant.MONGO_JDQ_COLLECTION_NAME);
            if (mongoDto == null) {
                log.error("未找到mongo数据 mongoId:{}", jdqOrderId);
                // 返回订单状态
//                userInfoService.pushUserInfoToInitStatus(userInfo.getUserInfoId(), jdqOrderId);
                return false;
            }

            // 用户设备信息
            DeviceInfo deviceInfo = deviceInfoMapper.selectByUserInfoId(userInfo.getUserInfoId());
            if (deviceInfo != null) {
                userBaseInfoDto.setLoanIp(deviceInfo.getIp());
                if (deviceInfo.getDeviceType()!=null&&deviceInfo.getDeviceType().equals("IOS")) {
                    userBaseInfoDto.setSource("1");
                }
            }
            // 申请单审核结果 回调地址
            String auditcallBackUrl = jdqCommonProperties.getGateWayUrl()+jdqCommonProperties.getRiskAuditCallBackUrl();
            // 信息重新采集 回调地址
            String recollectCallBackUrl = jdqCommonProperties.getGateWayUrl()+jdqCommonProperties.getOperatorCollectCallBackUrl();
            // 判断新老用户 客户标识(0:新客户 1：老客户)
            String appLevel = "0";
            // 校验此用户最后一笔订单是否已经结清，未结清怎还是传新用户
            RestResult<Void> restResult = orderUserCloudHystrixService.checkOlder(jdqCommonProperties.getRiskAppId(), userInfo.getIdNumber());
            if (restResult == null) {
                log.error("校验用户是不是老用户失败 {}", userInfo.getIdNumber());
                // 返回订单状态
                userInfoService.pushUserInfoToInitStatus(userInfo.getUserInfoId(), jdqOrderId);
                return false;
            }
            // 老用户的话
            if (restResult.isSuccess()) {
                appLevel = "1";
            }
            RiskApplicationDto riskApplicationDto = jdqRiskApplicationDtoTransform(userBaseInfoDto, userInfo, userBaseInfoDto.getLoanIp(), appLevel, auditcallBackUrl,recollectCallBackUrl);
            //公司信息
            UserCompanyInfo userCompanyInfo = userCompanyInfoMapper.selectByUserInfoId(userInfo.getUserInfoId());
            RiskCompanyInfoDto riskCompanyInfoDto = jdqRiskCompanyInfoDtoTransform(userCompanyInfo);
            //用户基础信息
            RiskUserInfoDto riskUserInfoDto = jdqRiskUserInfoDtoTransform(userBaseInfoDto, userInfo, String.valueOf(userCompanyInfo.getIndustry()));
            //通讯录信息
            RiskAddressBookDto riskAddressBookDto = jdqRiskAddressBookTransform(userInfo.getUserInfoId(), userInfo.getCustomerId(), userBaseInfoDto.getAppName());
            //紧急联系人信息
            RiskContactListDto riskContactListDto = jdqRiskContactListTransform(userInfo.getUserInfoId());
            //人脸信息
            RiskFaceInfoDto riskFaceInfoDto = jdqRiskFaceInfoTransform(userInfo);
            if(riskService.pushUserInfoToRisk(riskApplicationDto, riskUserInfoDto, riskCompanyInfoDto, riskContactListDto, riskFaceInfoDto, riskAddressBookDto, mongoDto.getJsonInfo(), userInfo.getUserInfoId(), jdqOrderId)) {
                // 推送借点钱订单状态事件
                jdqCreateOrderPushEventService.createOrderPushEvent(userInfo.getJdqOrderId(),null,null);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("借点钱信息推送风控失败 {}", e);
        }

        // 返回订单状态
        userInfoService.pushUserInfoToInitStatus(userInfo.getUserInfoId(), jdqOrderId);
        return false;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean jdqPushUserInfoToRiskEvent(PushRiskEvent pushRiskEvent) {
        log.info("风控推送开始 {}", pushRiskEvent.getJdqOrderId());
        String jdqOrderId = pushRiskEvent.getJdqOrderId();

        UserInfo userInfo = userInfoService.selectByJdqOrderId(jdqOrderId);
        if (userInfo == null) {
            log.error("不存在此订单{}", jdqOrderId);
            return true;
        }

        return jdqPushUserInfoToRisk(userInfo);
    }

    /**
     * 借点钱申请单信息转换
     * @param userBaseInfoDto
     * @param userInfo
     * @param loanIp
     * @param appLevel
     * @param auditcallBackUrl
     * @param recollectCallBackUrl
     * @return
     */
    private RiskApplicationDto jdqRiskApplicationDtoTransform(UserBaseInfoDto userBaseInfoDto ,UserInfo userInfo, String loanIp, String appLevel, String auditcallBackUrl,String recollectCallBackUrl) {
        RiskApplicationDto riskApplicationDto = new RiskApplicationDto();
        // APP应用请求流水号
        riskApplicationDto.setAppSerialNumber(userBaseInfoDto.getAppSerialNumber());
        // APP名称
        riskApplicationDto.setAppName(userBaseInfoDto.getAppName());
        // app 产品标识
        riskApplicationDto.setAppId(userBaseInfoDto.getAppId());
        // APP应用客户编号
        riskApplicationDto.setCustomerId(userInfo.getCustomerId());
        // 客户姓名
        riskApplicationDto.setName(userInfo.getName());
        // 证件类型 1.身份证 2.护照 3.其他）
        riskApplicationDto.setCertType("1");
        // 证件号码
        riskApplicationDto.setCertCode(userInfo.getIdNumber());
        // phoneNum
        riskApplicationDto.setPhoneNum(userInfo.getMobile());
        // 产品额度
        riskApplicationDto.setProductQuota(null);
        // 周期
        riskApplicationDto.setDays(null);
        // 借款用户公网IP
        riskApplicationDto.setLoanIp(loanIp);
        // 申请来源（0——Android；1——IOS）
        riskApplicationDto.setSource(userBaseInfoDto.getSource());
        // 同盾black_box
        riskApplicationDto.setTdBlackBox("0");
        // 聚信立用户token
        riskApplicationDto.setJxlUseToken(null);
        // 芝麻用户标识：芝麻会员在商户端的身份标识
        riskApplicationDto.setZhiMaOpenId(null);
        // 客户标识(0:新客户 1：老客户)
        riskApplicationDto.setAppLevel(appLevel);
        // app渠道标识
        riskApplicationDto.setAppChannel(UserChannelEnum.JDQ.getName());
        // APP版本号
        riskApplicationDto.setAppVersion(null);
        // 芝麻分数
        riskApplicationDto.setZhiMaScore(null);
        riskApplicationDto.setMobileWebsite("moxie");
        // 申请单审核结果 回调地址
        riskApplicationDto.setCallBackUrlAuditStatus(auditcallBackUrl);
        // 信息重新采集 回调地址
        riskApplicationDto.setCallBackUrlInfoRecollect(null);
        return riskApplicationDto;
    }

    /**
     * 用户基础信息转换
     * @param userBaseInfoDto
     * @param userInfo
     * @return
     */
    private RiskUserInfoDto jdqRiskUserInfoDtoTransform(UserBaseInfoDto userBaseInfoDto, UserInfo userInfo,  String industry) {
        RiskUserInfoDto riskUserInfoDto = new RiskUserInfoDto();
        // APP应用客户编号
        riskUserInfoDto.setCustomerId(userInfo.getCustomerId());
        // APP名称：1-闪贷；2-速贷 用户注册时的 appName
        riskUserInfoDto.setAppName(userBaseInfoDto.getAppName());
        // 客户姓名
        riskUserInfoDto.setName(userInfo.getName());
        // 手机号
        riskUserInfoDto.setPhoneNum(userInfo.getMobile());
        // 证件类型（1.身份证 2.护照 3.其他）
        riskUserInfoDto.setCertType("1");
        // 证件号码
        riskUserInfoDto.setCertCode(userInfo.getIdNumber());
        // 邮箱
        riskUserInfoDto.setEmail(userInfo.getEmail());
        // 年龄
        riskUserInfoDto.setAge(userInfo.getAge());
        // 学历
        riskUserInfoDto.setEducationId(userInfo.getEducate());
        // 银行卡号
        riskUserInfoDto.setBankName(null);
        // 婚姻状态
        riskUserInfoDto.setIfMarriage(userInfo.getMarry());
        // 家庭住址
        riskUserInfoDto.setHomeAddress(null);
        // 户籍地址
        riskUserInfoDto.setRegisteredAddress(userInfo.getIdCardAddress());
        // 通讯地址
        riskUserInfoDto.setContactAddress(null);
        // 银行卡号所属银行名称
        riskUserInfoDto.setBankName(null);
        // 民族
        riskUserInfoDto.setNation(userInfo.getNation());
        // 出身年月日
        riskUserInfoDto.setBirth(null);
        // 备注
        riskUserInfoDto.setRemark(null);

        // 格式：2012.11.08-2022.11.08
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
        try {
            // 有效期限
            riskUserInfoDto.setExpirationDate(sdf2.format(sdf1.parse(userInfo.getIdStartDate())) + "-" + sdf2.format(sdf1.parse(userInfo.getIdExpiryDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 签发机构
        riskUserInfoDto.setIssueInstitution(userInfo.getIdSigningAuthority());
        // 居住时长
        riskUserInfoDto.setLiveTime(null);
        // 省份名称
        riskUserInfoDto.setProvinceName(null);
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        // 注册时间
        riskUserInfoDto.setRegisterTime(sdf3.format(userInfo.getCreateTime()));
        // 从事行业编码
        riskUserInfoDto.setIndustryCode(industry);
        // 市场
        riskUserInfoDto.setMarket(UserChannelEnum.JDQ.getName());
        return riskUserInfoDto;

    }

    /**
     * 借点钱公司信息信息转换
     * @param userCompanyInfo
     * @return
     */
    private RiskCompanyInfoDto jdqRiskCompanyInfoDtoTransform(UserCompanyInfo userCompanyInfo) {


        RiskCompanyInfoDto riskCompanyInfoDto = new RiskCompanyInfoDto();
        if (userCompanyInfo == null) {
            return riskCompanyInfoDto;
        }
        // 公司行业

        // 借点钱 公司所属行业code码（1=批发/零售 2=制造业 3=金融/保险/证券 4=住宿/餐饮/旅游 5=商业服务/娱乐/艺术/体育 6=计算机/互联网 7=通讯电子 8=建筑/房地产 9=法律/咨询 10=卫生/教育/社会服务 11=公共事业/社会团体 12=生物/制药 13=广告/媒体 14=能源 15=贸易 16=交通运输/仓储/物流 17=农/林/牧/渔 18=其他）
        // 1	计算机、互联网、电子
        // 10	能源/原材料
        // 11	政府/非盈利机构/其他
        // 12	其他
        // 2	会计/金融/银行/保险
        // 3	贸易/消费/制造/运营
        // 4	制药/医疗
        // 5	广告/媒体
        // 6	房地产/建筑
        // 7	专业服务/教育/培训
        // 8	服务业
        // 9	物流/运输
        riskCompanyInfoDto.setCompanyIndustry(CompanyIndustryEnum.findByCode(String.valueOf(userCompanyInfo.getIndustry())));
        // 公司名称
        riskCompanyInfoDto.setCompanyName(userCompanyInfo.getCompanyName());
        // 公司电话
        riskCompanyInfoDto.setCompanyTel(null);
        // 公司地址
        riskCompanyInfoDto.setCompanyAddress(null);
        // 街道地址
        riskCompanyInfoDto.setStreetAddress(null);
        // 客户工作时长
        riskCompanyInfoDto.setWorkingHours(null);
        // 经度
        riskCompanyInfoDto.setLng(null);
        // 纬度
        riskCompanyInfoDto.setLat(null);
        // 工作照片存储URL
        riskCompanyInfoDto.setWorkPhoto(null);
        // 省
        riskCompanyInfoDto.setProvince(null);
        // 市
        riskCompanyInfoDto.setCity(null);
        // 区
        riskCompanyInfoDto.setArea(null);
        // 备注
        riskCompanyInfoDto.setRemark(null);
        // ADVANCED-高级资深人员、INTERMEDIATES-中级技术人员、BEGINNERS-初级、助理人员、PRACTICE-见习专员、SENIOR-高层管理人员、MIDDLE-中层管理人员、JUNIOR-基层管理人员、NORMAL-普通员工
        riskCompanyInfoDto.setProfessionId(null);
        return riskCompanyInfoDto;
    }

    /**
     * 借点钱通讯录信息转换
     * @param userInfoId
     * @param customerId
     * @param appName
     * @return
     */
    private RiskAddressBookDto jdqRiskAddressBookTransform(String userInfoId, String customerId, Integer appName) {
        RiskAddressBookDto riskAddressBookDto = new RiskAddressBookDto();
        List<UserAddressBook> userAddressBookList = userAddressBookMapper.selectByUserInfoId(userInfoId);
        if (userAddressBookList == null || userAddressBookList.size() == 0) {
            return riskAddressBookDto;
        }

        RiskAddressBookJsonInfoListDto riskAddressBookJsonInfoListDto = new RiskAddressBookJsonInfoListDto();
        List<RiskAddressBookJsonInfoDto> riskAddressBookJsonInfoDtoList = new ArrayList<>();
        userAddressBookList.forEach(userAddressBook -> {
            RiskAddressBookJsonInfoDto riskAddressBookJsonInfoDto = new RiskAddressBookJsonInfoDto();
            // 通讯录姓名
            riskAddressBookJsonInfoDto.setContactName(userAddressBook.getName());
            // 通讯录手机号码
            riskAddressBookJsonInfoDto.setContactPhone(userAddressBook.getMobile());
            riskAddressBookJsonInfoDtoList.add(riskAddressBookJsonInfoDto);
        });

        riskAddressBookJsonInfoListDto.setRiskAddressBookJsonInfoDtoList(riskAddressBookJsonInfoDtoList);

        // 通讯录jsonInfo文档
        riskAddressBookDto.setJsonInfo(riskAddressBookJsonInfoDtoList);
        // appName
        riskAddressBookDto.setAppName(appName);
        // 客户ID
        riskAddressBookDto.setCustomerId(customerId);

        return riskAddressBookDto;

    }

    /**
     * 借点钱通紧急联系人转换
     * @param userInfoId
     * @return
     */
    private RiskContactListDto jdqRiskContactListTransform(String userInfoId) {
        RiskContactListDto riskContactListDto = new RiskContactListDto();
        UserContact userContact = userContactMapper.selectByUserInfoId(userInfoId);
        if (userContact == null) {
            return riskContactListDto;
        }

        List<RiskContactDto> riskContactDtoList = new ArrayList<>();

        if (StringUtils.isNotEmpty(userContact.getName()) && StringUtils.isNotEmpty(userContact.getMobile()) && StringUtils.isNotEmpty(userContact.getRelation())) {
            RiskContactDto riskContactDto = new RiskContactDto();
            // 手机号码
            riskContactDto.setPhoneNum(userContact.getMobile());
            // 真实姓名
            riskContactDto.setTrueName(userContact.getName());
            // 关系
            riskContactDto.setType(userContact.getRelation());
            riskContactDtoList.add(riskContactDto);
        }

        if (StringUtils.isNotEmpty(userContact.getNameSpare()) && StringUtils.isNotEmpty(userContact.getMobileSpare()) && StringUtils.isNotEmpty(userContact.getRelationSpare())) {
            RiskContactDto riskContactDtoSpare = new RiskContactDto();
            // 手机号码
            riskContactDtoSpare.setPhoneNum(userContact.getMobileSpare());
            // 真实姓名
            riskContactDtoSpare.setTrueName(userContact.getNameSpare());
            // 关系
            riskContactDtoSpare.setType(userContact.getRelationSpare());
            riskContactDtoList.add(riskContactDtoSpare);
        }

        riskContactListDto.setRiskContactDtoList(riskContactDtoList);

        return riskContactListDto;

    }

    /**
     * 借点钱人脸信息信息转换
     * @param userInfo
     * @return
     */
    private RiskFaceInfoDto jdqRiskFaceInfoTransform(UserInfo userInfo) {
        RiskFaceInfoDto riskFaceInfoDto = new RiskFaceInfoDto();
        // 人脸认证分数
        riskFaceInfoDto.setFaceAuthenticationScore(null);
        // 人脸配置阈值分数
        riskFaceInfoDto.setFaceThresholdScore(null);
        // 身份证正面认证分数
        riskFaceInfoDto.setFrontofIdCardScore(null);
        // 身份证正面配置阈值分数
        riskFaceInfoDto.setFrontofIdCardThresholdScore(null);
        // 身份证反面认证分数
        riskFaceInfoDto.setIdcardNegativeScore(null);
        // 身份证反面配置阈值分数
        riskFaceInfoDto.setIdcardNegativeThresholdScore(null);
        // 综合认证分数
        riskFaceInfoDto.setComprehensiveScore(null);
        // 综合认证配置阈值分数
        riskFaceInfoDto.setComprehensiveThresholdScore(null);
        // 用户活体检测视频存储URL
        riskFaceInfoDto.setAliveUrl(null);
        // 人脸识别图存储URL
        riskFaceInfoDto.setFaceUrl(userInfo.getFace());
        // 身份证反面存储URL
        riskFaceInfoDto.setBackUrl(userInfo.getIdNegative());
        // 身份证正面存储URL
        riskFaceInfoDto.setFrontUrl(userInfo.getIdPositive());
        return riskFaceInfoDto;
    }

}
