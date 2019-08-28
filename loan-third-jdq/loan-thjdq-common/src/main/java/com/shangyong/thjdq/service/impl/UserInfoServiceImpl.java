package com.shangyong.thjdq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.dto.OrderLoanDto;
import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thjdq.config.properties.AliyunOssProperties;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.JdqConstant;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.constants.UuidPrefix;
import com.shangyong.thjdq.dao.*;
import com.shangyong.thjdq.dto.MongoDto;
import com.shangyong.thjdq.dto.PushPhaseOneDto;
import com.shangyong.thjdq.dto.PushPhaseTwoDto;
import com.shangyong.thjdq.entity.*;
import com.shangyong.thjdq.enums.ActiveProfileEnum;
import com.shangyong.thjdq.enums.RabbitMqEnum;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseOtherInfoEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;
import com.shangyong.thjdq.event.PushRiskEvent;
import com.shangyong.thjdq.mq.MqSender;
import com.shangyong.thjdq.service.UserInfoService;
import com.shangyong.thjdq.util.*;
import com.shangyong.thjdq.vo.PushPhaseOneVo;
import com.shangyong.thjdq.vo.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

//import com.shangyong.thjdq.service.MqSenderService;

/**
 * 用户基础信息serviceimpl
 * Created by zbb on 2019-03-13.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserLoanInfoMapper userLoanInfoMapper;
    @Autowired
    private OperatorDataMapper operatorDataMapper;
    @Autowired
    private OperatorSmsesMapper operatorSmsesMapper;
    @Autowired
    private OperatorSmsesItemsMapper operatorSmsesItemsMapper;
    @Autowired
    private OperatorNetsMapper operatorNetsMapper;
    @Autowired
    private OperatorNetsItemsMapper operatorNetsItemsMapper;
    @Autowired
    private OperatorBillsMapper operatorBillsMapper;
    @Autowired
    private OperatorMonthInfoMapper operatorMonthInfoMapper;
    @Autowired
    private OperatorMonthListMapper operatorMonthListMapper;
    @Autowired
    private OperatorPackagesMapper operatorPackagesMapper;
    @Autowired
    private OperatorPackagesItemsMapper operatorPackagesItemsMapper;
    @Autowired
    private OperatorFamiliesMapper operatorFamiliesMapper;
    @Autowired
    private OperatorFamiliesItemsMapper operatorFamiliesItemsMapper;
    @Autowired
    private OperatorRechargesMapper operatorRechargesMapper;
    @Autowired
    private OperatorCallsMapper operatorCallsMapper;
    @Autowired
    private OperatorCallsItemsMapper operatorCallsItemsMapper;
    @Autowired
    private UserCompanyInfoMapper userCompanyInfoMapper;
    @Autowired
    private UserContactMapper userContactMapper;
    @Autowired
    private UserAddressBookMapper userAddressBookMapper;
    @Autowired
    private BatchRedisTemplate batchRedisTemplate;
    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    private AliyunOssProperties aliyunOssProperties;
    @Autowired
    private MqSender mqSenderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<PushPhaseOneVo> pushPhaseOne(PushPhaseOneDto pushPhaseOneDto) {
        String idNumber = pushPhaseOneDto.getUser_info().getId_card().toUpperCase();
        String mobile = pushPhaseOneDto.getUser_info().getPhone();
        String jdqOrderId = pushPhaseOneDto.getJdq_order_id();

        // 根据身份证和手机查询是否存在user_info
        UserInfo userInfo = selectByJdqOrderId(jdqOrderId);
        if (userInfo != null) {
            PushPhaseOneVo pushPhaseOneVo = new PushPhaseOneVo();
            pushPhaseOneVo.setBind_card_flag("1");
            pushPhaseOneVo.setOrder_id(userInfo.getLocalOrderId());
            return ResponseCode.OK.toResponse(pushPhaseOneVo, "进件成功");
        }

        userInfo = new UserInfo();
        userInfo.setUserInfoId(pushPhaseOneDto.getUserInfoId());
        userInfo.setJdqOrderId(pushPhaseOneDto.getJdq_order_id());
        userInfo.setMobile(mobile);
        userInfo.setIdNumber(idNumber);
        userInfo.setPhoneIdNumberMd5(DigestUtils.md5Hex(mobile + idNumber).toLowerCase());
        userInfo.setName(pushPhaseOneDto.getUser_info().getUser_name());
        userInfo.setBirth(IdNumberUtils.getBirthByIdCard(idNumber));
        userInfo.setAge(IdNumberUtils.getAgeByIdCard(idNumber));
        userInfo.setRole(String.valueOf(pushPhaseOneDto.getUser_info().getRole()));
        userInfo.setIdCardAddress(pushPhaseOneDto.getUser_info().getId_card_address());
        userInfo.setNation(pushPhaseOneDto.getUser_info().getNation());
        userInfo.setIdPositive(pushPhaseOneDto.getUser_info().getId_positive());
        userInfo.setIdNegative(pushPhaseOneDto.getUser_info().getId_negative());
        userInfo.setFace(pushPhaseOneDto.getUser_info().getFace());
        userInfo.setIdSigningAuthority(pushPhaseOneDto.getUser_info().getId_signing_authority());
        userInfo.setIdStartDate(pushPhaseOneDto.getUser_info().getId_start_date());
        userInfo.setIdExpiryDate(pushPhaseOneDto.getUser_info().getId_expiry_date());
        userInfo.setPushPhaseState(0);
        userInfo.setPushRiskState(0);
        userInfo.setPushOperatorState(0);
        userInfo.setCustomerId("JDQ" + idNumber);
        userInfo.setAppName(pushPhaseOneDto.getApp_name());
        try {
            userInfo.setFaceResult(URLEncoder.encode(pushPhaseOneDto.getFaceResult(), "utf-8"));
            userInfo.setFaceResultActive(URLEncoder.encode(pushPhaseOneDto.getFaceResultActive(), "utf-8"));
        } catch (Exception e) {
            log.info("urlencoder失败", e);
        }
        userInfo.setCreateTime(new Date());
        userInfoMapper.insert(userInfo);

        OrderLoanDto orderLoanDto = new OrderLoanDto();
        orderLoanDto.setOtherOrderId(jdqOrderId);
        RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderCreate(jdqCommonProperties.getRiskAppId(), orderLoanDto);
        if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess()) {
            log.error("调用服务创建本地订单失败 借点钱订单号：{} ", jdqOrderId);
            throw new RuntimeException("调用服务创建本地订单失败 借点钱订单号:" + jdqOrderId);
        }

        String localOrderId = orderLoanVoRestResult.getData().getBody().getOrderId();
        userInfo.setLocalOrderId(localOrderId);
        userInfoMapper.updateByPrimaryKey(userInfo);

        PushPhaseOneVo pushPhaseOneVo = new PushPhaseOneVo();
        pushPhaseOneVo.setBind_card_flag("1");
        pushPhaseOneVo.setOrder_id(localOrderId);
        return ResponseCode.OK.toResponse(pushPhaseOneVo, "进件成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean pushPhaseOneDataAsync(PushPhaseOneEvent pushPhaseOneEvent) {

        log.info("一推基本信息入库开始 {}", pushPhaseOneEvent.getJdqOrderId());
        String jdqOrderId = pushPhaseOneEvent.getJdqOrderId();

        PushPhaseOneDto pushPhaseOneDto = (PushPhaseOneDto) batchRedisTemplate.get(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_DTO + jdqOrderId);
        if (pushPhaseOneDto == null) {
            log.error("一推基本信息入库 redis信息不存在 redisKey:{}", RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_DTO + jdqOrderId);
            return true;
        }

        String moxieReport = batchRedisTemplate.get(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_MOXIE_REPORT_DTO + jdqOrderId);
        if (StringUtils.isEmpty(moxieReport)) {
            log.error("一推基本信息入库 redis信息不存在 redisKey:{}", RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_MOXIE_REPORT_DTO + jdqOrderId);
            return true;
        }

        log.debug("一推基本信息入库 参数 --> {}", JSONObject.toJSONString(pushPhaseOneDto));
        log.debug("一推基本信息入库 魔蝎分析报文参数 --> {}", moxieReport);

        String userInfoId = pushPhaseOneDto.getUserInfoId();

        // 用户基本信息
        UserInfo userInfo = selectLockByUserInfoId(userInfoId);
        log.info("获取用户信息 {}", userInfo);

        // 非初始化状态，不允许一推入库
        if (userInfo.getPushPhaseState().intValue() != 0) {
            log.info("非初始化状态，无需一推入库");
            return true;
        }

        // 一推成功
        userInfo.setPushPhaseState(2);

        if (ActiveProfileEnum.PRODUCT.name().equalsIgnoreCase(SpringContextUtils.getActiveProfile())) {
            // 身份证正面图片url保存到阿里云oss服务器
            if (StringUtils.isNotEmpty(userInfo.getIdPositive())) {
                log.info("一推基本信息入库 身份证正面图片url");
//                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdPositive(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdPositive());
                if (picUrl != null) {
                    userInfo.setIdPositive(picUrl);
                }
            }

            // 身份证反面图片url保存到阿里云oss服务器
            if (StringUtils.isNotEmpty(userInfo.getIdNegative())) {
                log.info("一推基本信息入库 身份证反面图片url");
//                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdNegative(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdNegative());
                if (picUrl != null) {
                    userInfo.setIdNegative(picUrl);
                }
            }

            // 人脸照片url保存到阿里云oss服务器
            if (StringUtils.isNotEmpty(userInfo.getFace())) {
                log.info("一推基本信息入库 人脸照片url");
//                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getFace(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getFace());
                if (picUrl != null) {
                    userInfo.setFace(picUrl);
                }
            }

            // 手持身份证照片URL保存到阿里云oss服务器
            if (StringUtils.isNotEmpty(userInfo.getHandIdPhoto())) {
                log.info("一推基本信息入库 手持身份证照片URL");
//                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getHandIdPhoto(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getHandIdPhoto());
                if (picUrl != null) {
                    userInfo.setHandIdPhoto(picUrl);
                }
            }
        }

        userInfoMapper.updateByPrimaryKey(userInfo);

        Date date = new Date();

        // 贷款信息user_loan_info
        if (pushPhaseOneDto.getLoan_info() != null) {
            log.info("一推基本信息入库 贷款信息");
            UserLoanInfo userLoanInfo = new UserLoanInfo();
            userLoanInfo.setLoanInfoId(UUIDUtils.getUUID());
            userLoanInfo.setUserInfoId(userInfoId);
            userLoanInfo.setJdqOrderId(jdqOrderId);
            userLoanInfo.setLoanAmount(pushPhaseOneDto.getLoan_info().getLoan_amount());
            userLoanInfo.setLoanTerm(pushPhaseOneDto.getLoan_info().getLoan_term());
            userLoanInfo.setCreateTime(date);
            userLoanInfoMapper.insert(userLoanInfo);
        }

        //  运营商原始数据 operator_data
        log.info("一推基本信息入库 运营商原始数据");
        OperatorData operatorData = new OperatorData();
        operatorData.setOperatorDataId(UUIDUtils.getUUID());
        operatorData.setUserInfoId(userInfoId);
        operatorData.setJdqOrderId(jdqOrderId);
        if (pushPhaseOneDto.getMoxie_telecom() != null) {
            operatorData.setMobile(pushPhaseOneDto.getMoxie_telecom().getMobile());
            operatorData.setName(pushPhaseOneDto.getMoxie_telecom().getName());
            operatorData.setIdcard(pushPhaseOneDto.getMoxie_telecom().getIdcard());
            operatorData.setCarrier(pushPhaseOneDto.getMoxie_telecom().getCarrier());
            operatorData.setProvince(pushPhaseOneDto.getMoxie_telecom().getProvince());
            operatorData.setCity(pushPhaseOneDto.getMoxie_telecom().getCity());
            operatorData.setOpenTime(pushPhaseOneDto.getMoxie_telecom().getOpen_time());
            operatorData.setLevel(pushPhaseOneDto.getMoxie_telecom().getLevel());
            operatorData.setPackageName(pushPhaseOneDto.getMoxie_telecom().getPackage_name());
            operatorData.setState(pushPhaseOneDto.getMoxie_telecom().getState());
            operatorData.setReliability(pushPhaseOneDto.getMoxie_telecom().getReliability());
            operatorData.setAvailableBalance(pushPhaseOneDto.getMoxie_telecom().getAvailable_balance());
            operatorData.setLastModifyTime(pushPhaseOneDto.getMoxie_telecom().getLast_modify_time());
            operatorData.setCode(pushPhaseOneDto.getMoxie_telecom().getCode());
            operatorData.setMessageDesc(pushPhaseOneDto.getMoxie_telecom().getMessage());
        }

        operatorDataMapper.insert(operatorData);

        // TODO 运营商数据暂时不入库
//        String operatorDataId = operatorData.getOperatorDataId();
//
//        //运营商
//        if (pushPhaseOneDto.getMoxie_telecom() != null) {
//            // 运营商-短信信息详情
//            if (pushPhaseOneDto.getMoxie_telecom().getSmses() != null && pushPhaseOneDto.getMoxie_telecom().getSmses().size() > 0) {
//                log.info("一推基本信息入库 短信信息");
//                pushPhaseOneDto.getMoxie_telecom().getSmses().forEach(moxieTelecomSmses -> {
//                    OperatorSmses operatorSmses = new OperatorSmses();
//                    operatorSmses.setOperatorSmsesId(UUIDUtils.getUUID());
//                    operatorSmses.setUserInfoId(userInfoId);
//                    operatorSmses.setOperatorDataId(operatorDataId);
//                    operatorSmses.setBillMonth(moxieTelecomSmses.getBill_month());
//                    operatorSmses.setTotalSize(moxieTelecomSmses.getTotal_size());
//                    operatorSmsesMapper.insert(operatorSmses);
//
//                    // 运营商-短信信息详情明细
//                    if (moxieTelecomSmses.getItems() != null && moxieTelecomSmses.getItems().size() > 0) {
//                        log.info("一推基本信息入库 短信信息详情明细");
//                        moxieTelecomSmses.getItems().forEach(moxieTelecomSmsesItems -> {
//                            OperatorSmsesItems operatorSmsesItems = new OperatorSmsesItems();
//                            operatorSmsesItems.setOperatorSmsesId(operatorSmses.getOperatorSmsesId());
//                            operatorSmsesItems.setOperatorSmsesItemsId(UUIDUtils.getUUID());
//                            operatorSmsesItems.setDetailsId(moxieTelecomSmsesItems.getDetails_id());
//                            operatorSmsesItems.setMsgTime(moxieTelecomSmsesItems.getTime());
//                            operatorSmsesItems.setPeerNumber(moxieTelecomSmsesItems.getPeer_number());
//                            operatorSmsesItems.setLocation(moxieTelecomSmsesItems.getLocation());
//                            operatorSmsesItems.setSendType(moxieTelecomSmsesItems.getSend_type());
//                            operatorSmsesItems.setMsgType(moxieTelecomSmsesItems.getMsg_type());
//                            operatorSmsesItems.setServiceName(moxieTelecomSmsesItems.getService_name());
//                            operatorSmsesItems.setFee(moxieTelecomSmsesItems.getFee());
//                            operatorSmsesItemsMapper.insert(operatorSmsesItems);
//                        });
//                    }
//
//                });
//            }
//
//            // 运营商-流量详情
//            if (pushPhaseOneDto.getMoxie_telecom().getNets() != null && pushPhaseOneDto.getMoxie_telecom().getNets().size() > 0) {
//                log.info("一推基本信息入库 流量详情");
//                pushPhaseOneDto.getMoxie_telecom().getNets().forEach(nets -> {
//                    OperatorNets operatorNets = new OperatorNets();
//                    operatorNets.setOperatorNetsId(UUIDUtils.getUUID());
//                    operatorNets.setOperatorDataId(operatorDataId);
//                    operatorNets.setUserInfoId(userInfoId);
//                    operatorNets.setBillMonth(nets.getBill_month());
//                    operatorNets.setTotalSize(nets.getTotal_size());
//                    operatorNetsMapper.insert(operatorNets);
//
//                    // 运营商-流量详情明细
//                    if (nets.getItems() != null && nets.getItems().size() > 0) {
//                        log.info("一推基本信息入库 流量详情明细");
//                        nets.getItems().forEach(netsItems -> {
//                            OperatorNetsItems operatorNetsItems = new OperatorNetsItems();
//                            operatorNetsItems.setOperatorNetsId(operatorNets.getOperatorNetsId());
//                            operatorNetsItems.setOperatorNetsItemsId(UUIDUtils.getUUID());
//                            operatorNetsItems.setDetailsId(netsItems.getDetails_id());
//                            operatorNetsItems.setUsedTime(netsItems.getTime());
//                            operatorNetsItems.setDuration(netsItems.getDuration());
//                            operatorNetsItems.setLocation(netsItems.getLocation());
//                            operatorNetsItems.setSubflow(netsItems.getSubflow());
//                            operatorNetsItems.setNetType(netsItems.getNet_type());
//                            operatorNetsItems.setServiceName(netsItems.getService_name());
//                            operatorNetsItems.setFee(netsItems.getFee());
//                            operatorNetsItemsMapper.insert(operatorNetsItems);
//                        });
//                    }
//
//                });
//            }
//
//            // 运营商-账单详情
//            if (pushPhaseOneDto.getMoxie_telecom().getBills() != null && pushPhaseOneDto.getMoxie_telecom().getBills().size() > 0) {
//                log.info("一推基本信息入库 账单详情");
//                pushPhaseOneDto.getMoxie_telecom().getBills().forEach(bills -> {
//                    OperatorBills operatorBills = new OperatorBills();
//                    operatorBills.setOperatorBillsId(UUIDUtils.getUUID());
//                    operatorBills.setOperatorDataId(operatorDataId);
//                    operatorBills.setUserInfoId(userInfoId);
//                    operatorBills.setBillMonth(bills.getBill_month());
//                    operatorBills.setBillStartDate(bills.getBill_start_date());
//                    operatorBills.setBillEndDate(bills.getBill_end_date());
//                    operatorBills.setBaseFee(bills.getBase_fee());
//                    operatorBills.setExtraServiceFee(bills.getExtra_service_fee());
//                    operatorBills.setVoiceFee(bills.getVoice_fee());
//                    operatorBills.setSmsFee(bills.getSms_fee());
//                    operatorBills.setWebFee(bills.getWeb_fee());
//                    operatorBills.setExtraFee(bills.getExtra_fee());
//                    operatorBills.setTotalFee(bills.getTotal_fee());
//                    operatorBills.setDiscount(bills.getDiscount());
//                    operatorBills.setExtraDiscount(bills.getExtra_discount());
//                    operatorBills.setActualFee(bills.getActual_fee());
//                    operatorBills.setPaidFee(bills.getPaid_fee());
//                    operatorBills.setUnpaidFee(bills.getUnpaid_fee());
//                    operatorBills.setPoint(bills.getPoint());
//                    operatorBills.setLastPoint(bills.getLast_point());
//                    operatorBills.setRelatedMobiles(bills.getRelated_mobiles());
//                    operatorBills.setNotes(bills.getNotes());
//                    operatorBillsMapper.insert(operatorBills);
//                });
//            }
//
//            // 运营商-语音月份信息
//            if (pushPhaseOneDto.getMoxie_telecom().getMonth_info() != null) {
//                log.info("一推基本信息入库 语音月份信息");
//                OperatorMonthInfo operatorMonthInfo = new OperatorMonthInfo();
//                operatorMonthInfo.setOperatorDataId(operatorDataId);
//                operatorMonthInfo.setOperatorMonthInfoId(UUIDUtils.getUUID());
//                operatorMonthInfo.setUserInfoId(userInfoId);
//                operatorMonthInfo.setPhoneNo(pushPhaseOneDto.getMoxie_telecom().getMonth_info().getPhone_no());
//                operatorMonthInfo.setMonthCount(pushPhaseOneDto.getMoxie_telecom().getMonth_info().getMonth_count());
//                operatorMonthInfo.setMissMonthCount(pushPhaseOneDto.getMoxie_telecom().getMonth_info().getMiss_month_count());
//                operatorMonthInfo.setNoCallMonth(pushPhaseOneDto.getMoxie_telecom().getMonth_info().getNo_call_month());
//                operatorMonthInfo.setUserId(pushPhaseOneDto.getMoxie_telecom().getMonth_info().getUser_id());
//                operatorMonthInfoMapper.insert(operatorMonthInfo);
//
//                // 运营商-语音月份记录
//                if (pushPhaseOneDto.getMoxie_telecom().getMonth_info().getMonth_list() != null && pushPhaseOneDto.getMoxie_telecom().getMonth_info().getMonth_list().size() > 0) {
//                    log.info("一推基本信息入库 语音月份记录");
//                    pushPhaseOneDto.getMoxie_telecom().getMonth_info().getMonth_list().forEach(monthList -> {
//                        OperatorMonthList operatorMonthList = new OperatorMonthList();
//                        operatorMonthList.setOperatorMonthInfoId(operatorMonthInfo.getOperatorMonthInfoId());
//                        operatorMonthList.setOperatorMonthListId(UUIDUtils.getUUID());
//                        operatorMonthList.setMonth(monthList.getKey());
//                        operatorMonthList.setCount(monthList.getValue());
//                        operatorMonthListMapper.insert(operatorMonthList);
//                    });
//
//                }
//            }
//
//            // 运营商-套餐详情
//            if (pushPhaseOneDto.getMoxie_telecom().getPackages() != null && pushPhaseOneDto.getMoxie_telecom().getPackages().size() > 0) {
//                log.info("一推基本信息入库 套餐详情");
//                pushPhaseOneDto.getMoxie_telecom().getPackages().forEach(packages -> {
//                    OperatorPackages operatorPackages = new OperatorPackages();
//                    operatorPackages.setOperatorPackagesId(UUIDUtils.getUUID());
//                    operatorPackages.setOperatorDataId(operatorDataId);
//                    operatorPackages.setUserInfoId(userInfoId);
//                    operatorPackages.setBillStartDate(packages.getBill_start_date());
//                    operatorPackages.setBillEndDate(packages.getBill_end_date());
//                    operatorPackagesMapper.insert(operatorPackages);
//
//                    // 运营商-套餐详情明细
//                    if (packages.getItems() != null && packages.getItems().size() > 0) {
//                        log.info("一推基本信息入库 套餐详情明细");
//                        packages.getItems().forEach(packagesItems -> {
//                            OperatorPackagesItems operatorPackagesItems = new OperatorPackagesItems();
//                            operatorPackagesItems.setOperatorPackagesId(operatorPackages.getOperatorPackagesId());
//                            operatorPackagesItems.setOperatorPackagesItemsId(UUIDUtils.getUUID());
//                            operatorPackagesItems.setItem(packagesItems.getItem());
//                            operatorPackagesItems.setTotal(packagesItems.getTotal());
//                            operatorPackagesItems.setUsed(packagesItems.getUsed());
//                            operatorPackagesItems.setUnit(packagesItems.getUnit());
//                            operatorPackagesItemsMapper.insert(operatorPackagesItems);
//                        });
//                    }
//
//                });
//            }
//
//            // 运营商-亲情网详情
//            if (pushPhaseOneDto.getMoxie_telecom().getFamilies() != null && pushPhaseOneDto.getMoxie_telecom().getFamilies().size() > 0) {
//                log.info("一推基本信息入库 亲情网详情");
//                pushPhaseOneDto.getMoxie_telecom().getFamilies().forEach(families -> {
//                    OperatorFamilies operatorFamilies = new OperatorFamilies();
//                    operatorFamilies.setOperatorFamiliesId(UUIDUtils.getUUID());
//                    operatorFamilies.setOperatorDataId(operatorDataId);
//                    operatorFamilies.setUserInfoId(userInfoId);
//                    operatorFamilies.setFamilyNum(families.getFamily_num());
//                    operatorFamiliesMapper.insert(operatorFamilies);
//
//                    // 运营商-亲情网详情明细
//                    if (families.getItems() != null && families.getItems().size() > 0) {
//                        log.info("一推基本信息入库 亲情网详情明细");
//                        families.getItems().forEach(familiesItems -> {
//                            OperatorFamiliesItems operatorFamiliesItems = new OperatorFamiliesItems();
//                            operatorFamiliesItems.setOperatorFamiliesId(operatorFamilies.getOperatorFamiliesId());
//                            operatorFamiliesItems.setOperatorFamiliesItemsId(UUIDUtils.getUUID());
//                            operatorFamiliesItems.setLongNumber(familiesItems.getLong_number());
//                            operatorFamiliesItems.setShortNumber(familiesItems.getShort_number());
//                            operatorFamiliesItems.setMemberType(familiesItems.getMember_type());
//                            operatorFamiliesItems.setJoinDate(familiesItems.getJoin_date());
//                            operatorFamiliesItems.setExpireDate(familiesItems.getExpire_date());
//                            operatorFamiliesItemsMapper.insert(operatorFamiliesItems);
//                        });
//                    }
//
//                });
//            }
//
//            // 运营商-充值记录
//            if (pushPhaseOneDto.getMoxie_telecom().getRecharges() != null && pushPhaseOneDto.getMoxie_telecom().getRecharges().size() > 0) {
//                log.info("一推基本信息入库 充值记录");
//                pushPhaseOneDto.getMoxie_telecom().getRecharges().forEach(recharges -> {
//
//                    OperatorRecharges operatorRecharges = new OperatorRecharges();
//                    operatorRecharges.setOperatorRechargesId(UUIDUtils.getUUID());
//                    operatorRecharges.setOperatorDataId(operatorDataId);
//                    operatorRecharges.setUserInfoId(userInfoId);
//                    operatorRecharges.setDetailsId(recharges.getDetails_id());
//                    operatorRecharges.setRechargeTime(recharges.getRecharge_time());
//                    operatorRecharges.setAmount(recharges.getAmount());
//                    operatorRecharges.setType(recharges.getType());
//                    operatorRechargesMapper.insert(operatorRecharges);
//                });
//            }
//
//            // 运营商-语音详情
//            if (pushPhaseOneDto.getMoxie_telecom().getCalls() != null && pushPhaseOneDto.getMoxie_telecom().getCalls().size() > 0) {
//                log.info("一推基本信息入库 语音详情");
//                pushPhaseOneDto.getMoxie_telecom().getCalls().forEach(calls -> {
//                    OperatorCalls operatorCalls = new OperatorCalls();
//                    operatorCalls.setOperatorCallsId(UUIDUtils.getUUID());
//                    operatorCalls.setOperatorDataId(operatorDataId);
//                    operatorCalls.setUserInfoId(userInfoId);
//                    operatorCalls.setBillMonth(calls.getBill_month());
//                    operatorCalls.setTotalSize(calls.getTotal_size());
//                    operatorCallsMapper.insert(operatorCalls);
//
//                    // 运营商-语音详情明细
//                    if (calls.getItems() != null && calls.getItems().size() > 0) {
//                        log.info("一推基本信息入库 语音详情明细");
//                        calls.getItems().forEach(callsItems -> {
//                            OperatorCallsItems operatorCallsItems = new OperatorCallsItems();
//                            operatorCallsItems.setOperatorCallsId(operatorCalls.getOperatorCallsId());
//                            operatorCallsItems.setOperatorCallsItemsId(UUIDUtils.getUUID());
//                            operatorCallsItems.setPeerNumber(callsItems.getPeer_number());
//                            operatorCallsItems.setDuration(callsItems.getDuration());
//                            operatorCallsItems.setDetailsId(callsItems.getDetails_id());
//                            operatorCallsItems.setDialType(callsItems.getDial_type());
//                            operatorCallsItems.setFee(callsItems.getFee());
//                            operatorCallsItems.setLocation(callsItems.getLocation());
//                            operatorCallsItems.setCallTime(callsItems.getTime());
//                            operatorCallsItems.setLocationType(callsItems.getLocation_type());
//                            operatorCallsItemsMapper.insert(operatorCallsItems);
//                        });
//                    }
//
//                });
//            }
//        }

        // 魔蝎分析报文mongo insert
        log.info("一推基本信息入库 魔蝎分析报文");
        MongoDto mongoDto = new MongoDto();
        mongoDto.setJsonInfo(moxieReport);
        mongoDto.setMongoId(jdqOrderId);
        mongoDto.setCreateTime(date.getTime());
        mongoTemplate.insert(mongoDto, JdqConstant.MONGO_JDQ_COLLECTION_NAME);

        // 删除redisKey
        batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_DTO + jdqOrderId);
        batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_MOXIE_REPORT_DTO + jdqOrderId);
        log.info("一推基本信息入库结束 {}", pushPhaseOneEvent.getJdqOrderId());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean pushPhaseTwoDataAsync(PushPhaseTwoEvent pushPhaseTwoEvent) {
        log.info("二推补充信息入库开始 {}", pushPhaseTwoEvent.getJdqOrderId());

        String jdqOrderId = pushPhaseTwoEvent.getJdqOrderId();

        PushPhaseTwoDto pushPhaseTwoDto = (PushPhaseTwoDto) batchRedisTemplate.get(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_TWO_DTO + jdqOrderId);
        if (pushPhaseTwoDto == null) {
            log.error("二推补充信息入库 redis信息不存在 redisKey:{}", RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_TWO_DTO + jdqOrderId);
            return true;
        }

        log.debug("二推补充信息入库 参数 --> {}", JSONObject.toJSONString(pushPhaseTwoDto));

        String userInfoId = pushPhaseTwoDto.getUserInfoId();
        Date date = new Date();

        // 用户基本信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userInfoId);

        if (userInfo == null) {
            log.info("基本信息不存在");
            return false;
        }

        if (userInfo.getPushPhaseState().intValue() == 5) {
            log.info("二推补充信息已入库，直接返回");
            return true;
        }
        if (userInfo.getPushPhaseState().intValue() != 2) {
            log.info("二推状态不对 {}", userInfo.getPushPhaseState());
            return false;
        }

        log.info("获取用户信息");
        userInfo = selectLockByUserInfoId(userInfoId);
        log.info("获取用户信息 {}", userInfo);

        if (pushPhaseTwoDto.getUser_info() != null) {
            log.info("二推基本信息入库 用户信息");
            // jdq 婚姻状况code码 (1=已婚 2=未婚 3=离异 4=其他)
            // SPINSTERHOOD-未婚、MARRIED-已婚、DIVORCED-离异、WIDOWED-丧偶、REMARRY-再婚、REMARRY_FORMER-复婚
            String marry = "SPINSTERHOOD";
            switch (pushPhaseTwoDto.getUser_info().getMarry()) {
                case 1:
                    marry = "MARRIED";
                    break;
                case 2:
                    marry = "SPINSTERHOOD";
                    break;
                case 3:
                    marry = "DIVORCED";
                    break;
//            case 4:
//                marry = "其他";
//                break;
                default:
                    break;
            }
            userInfo.setMarry(marry);

            //{ "1" : "博士",  "2" : "硕士", "3" : "本科", "4" : "专科","5" : "中专", "6" : "高中", "7" : "职高",  "8" : "初中及以下"};
            String educate = "";
            // jdq 1=大专以下 2=大专 3=本科 4=研究生及以上 5=高中 6=中专 7=初中 8=初中以下
            switch (pushPhaseTwoDto.getUser_info().getEducate()) {
                case 7:
                case 8:
                    educate = "8";
                    break;
                case 1:
                    educate = "7";
                    break;
                case 5:
                    educate = "6";
                    break;
                case 6:
                    educate = "5";
                    break;
                case 2:
                    educate = "4";
                    break;
                case 3:
                    educate = "3";
                    break;
                case 4:
                    educate = "2";
                    break;
                default:
                    break;
            }
            userInfo.setEducate(educate);
            userInfo.setEmail(pushPhaseTwoDto.getUser_info().getCommon_email());
            userInfo.setLivingAddress(pushPhaseTwoDto.getUser_info().getLiving_address());
            userInfo.setPushPhaseState(5);
            userInfo.setPushOperatorState(0);
            userInfo.setPushRiskState(0);
            userInfo.setModifyTime(date);
            userInfoMapper.updateByPrimaryKey(userInfo);

            // 公司信息
            log.info("二推基本信息入库 公司信息");
            UserCompanyInfo userCompanyInfo = new UserCompanyInfo();
            userCompanyInfo.setUserCompanyInfoId(UUIDUtils.getUUID());
            userCompanyInfo.setUserInfoId(userInfoId);
            userCompanyInfo.setJdqOrderId(jdqOrderId);
            userCompanyInfo.setCompanyName(pushPhaseTwoDto.getUser_info().getCompany_name());

            // jdq 公司所属行业code码（1=批发/零售 2=制造业 3=金融/保险/证券 4=住宿/餐饮/旅游 5=商业服务/娱乐/艺术/体育 6=计算机/互联网 7=通讯电子 8=建筑/房地产 9=法律/咨询 10=卫生/教育/社会服务 11=公共事业/社会团体 12=生物/制药 13=广告/媒体 14=能源 15=贸易 16=交通运输/仓储/物流 17=农/林/牧/渔 18=其他）

//            1000101	互联网/电子商务
//            1000201	计算机软件
//            1000301	计算机硬件
//            1000401	计算机服务/系统集成
//            1000501	通信/电信/网络设备/电信运营、增值服务
//            1000601	电子技术/半导体/集成电路
//            1000701	仪器仪表/工业自动化
//            1000801	网络游戏
//            1000902	会计/审计
//            1001002	金融/投资/证券/基金
//            1001102	银行
//            1001202	保险
//            1001302	信托/担保/拍卖/典当
//            1001403	贸易/进出口
//            1001503	批发/零售
//            1001603	快速消费品（食品、饮料、化妆品）
//            1001703	服装/纺织/皮革
//            1001803	家具/家电/玩具/礼品
//            1001903	奢侈品/收藏品/工艺品/珠宝
//            1002003	办公用品及设备
//            1002103	机械/设备/重工
//            1002203	汽车及零配件
//            1002304	制药/生物工程
//            1002404	医疗/护理/卫生
//            1002504	医疗设备/器械
//            1002605	广告/创意
//            1002705	公关/市场推广/会展
//            1002805	影视/媒体艺术/文化传播
//            1002905	文字媒体/出版
//            1003005	印刷/包装/造纸
//            1003106	物业管理/商业中心
//            1003206	房地产/建筑/建材/工程
//            1003306	家居/室内设计/装潢
//            1003407	中介服务
//            1003507	专业服务（咨询、人力资源、财会）
//            1003607	外包服务
//            1003707	检测，认证
//            1003807	法律/法务
//            1003907	教育/培训/院校
//            1004007	学术/科研
//            1004107	租赁服务
//            1004308	餐饮业
//            1004408	酒店/旅游
//            1004508	娱乐休闲/餐饮/体育
//            1004608	美容/保健
//            1004708	生活服务
//            1004809	交通/运输/物流
//            1004909	航天/航空
//            1005010	石油/化工/矿产/地质
//            1005110	采掘业/冶炼
//            1005210	电气/电力/水利
//            1005310	新能源
//            1005410	原材料和加工
//            1005511	政府/公共事业/非盈利机构
//            1005611	环保
//            1005711	农/林/牧/渔
//            1005811	多元化集团
//            1005912	学生
//            1006012	无业

            String industry = "1006012";
            switch (pushPhaseTwoDto.getUser_info().getIndustry()) {

                case 1:
                    // 1=批发/零售
                    industry = "1001503";
                    break;
                case 2:
                    // 2=制造业
                    industry = "1000701";
                    break;
                case 3:
                    // 3=金融/保险/证券
                    industry = "1001002";
                    break;
                case 4:
                    // 4=住宿/餐饮/旅游
                    industry = "1004408";
                    break;
                case 5:
                    // 5=商业服务/娱乐/艺术/体育
                    industry = "1004508";
                    break;
                case 6:
                    // 6=计算机/互联网
                    industry = "1000201";
                    break;
                case 7:
                    // 7=通讯电子
                    industry = "1000501";
                    break;
                case 8:
                    // 8=建筑/房地产
                    industry = "1003206";
                    break;
                case 9:
                    // 9=法律/咨询
                    industry = "1003807";
                    break;
                case 10:
                    // 10=卫生/教育/社会服务
                    industry = "1003907";
                    break;
                case 11:
                    // 11=公共事业/社会团体
                    industry = "1005511";
                    break;
                case 12:
                    // 12=生物/制药
                    industry = "1002304";
                    break;
                case 13:
                    // 13=广告/媒体
                    industry = "1002605";
                    break;
                case 14:
                    // 14=能源
                    industry = "1005310";
                    break;
                case 15:
                    // 15=贸易
                    industry = "1001403";
                    break;
                case 16:
                    // 16=交通运输/仓储/物流
                    industry = "1004809";
                    break;
                case 17:
                    // 17=农/林/牧/渔
                    industry = "1005711";
                    break;
                case 18:
                    // 18=其他
                    industry = "1006012";
                    break;
                default:
                    break;
            }

            userCompanyInfo.setIndustry(Integer.valueOf(industry));
            userCompanyInfo.setCompanyTel(pushPhaseTwoDto.getUser_info().getCompany_tel());
            userCompanyInfo.setCompanyAddress(pushPhaseTwoDto.getUser_info().getCompany_address());
            if (pushPhaseTwoDto.getUser_info().getCompany_work_year() > 0) {
                userCompanyInfo.setCompanyWorkYear(pushPhaseTwoDto.getUser_info().getCompany_work_year());
            }
            userCompanyInfo.setCompanyCity(pushPhaseTwoDto.getUser_info().getCompany_city());
            // jdq 工作职业code码（1=农牧业 2=木材/森林业 3=矿业/采石业 4=交通运输业 5=餐旅业 6=建筑工程业 7=制造业 8=娱乐业 9=文教 10=金融业 11=服务业 12=治安人员 13=军人 14=其他）
            userCompanyInfo.setWorkProfession(pushPhaseTwoDto.getUser_info().getWork_profession());
            // 如果公司行业是其他，再通过工作职业进行判断是否是其他
            if (userCompanyInfo.getIndustry().equals("1006012")) {
                userCompanyInfo.setIndustry(checkIndustryByWorkProfession(pushPhaseTwoDto.getUser_info().getWork_profession()));
            }
            userCompanyInfo.setCreateTime(date);
            userCompanyInfoMapper.insert(userCompanyInfo);
        }


        // 联系人
        if (pushPhaseTwoDto.getUser_contact() != null) {
            log.info("二推基本信息入库 联系人");
            UserContact userContact = new UserContact();
            userContact.setUserContactId(UUIDUtils.getUUID());
            userContact.setUserInfoId(userInfoId);
            userContact.setJdqOrderId(jdqOrderId);
            userContact.setMobile(pushPhaseTwoDto.getUser_contact().getMobile());
            userContact.setName(pushPhaseTwoDto.getUser_contact().getName());
            userContact.setRelation(relationChange(pushPhaseTwoDto.getUser_contact().getRelation()));
            userContact.setMobileSpare(pushPhaseTwoDto.getUser_contact().getMobile_spare());
            userContact.setNameSpare(pushPhaseTwoDto.getUser_contact().getName_spare());
            userContact.setRelationSpare(relationChange(pushPhaseTwoDto.getUser_contact().getRelation_spare()));
            userContact.setCreateTime(date);
            userContactMapper.insert(userContact);
        }

        // 通讯录
        if (pushPhaseTwoDto.getAddress_book() != null && pushPhaseTwoDto.getAddress_book().size() > 0) {
            log.info("二推基本信息入库 通讯录");
            pushPhaseTwoDto.getAddress_book().forEach(addressBook -> {
                UserAddressBook userAddressBook = new UserAddressBook();
                userAddressBook.setAddressBookId(UUIDUtils.getUUID());
                userAddressBook.setJdqOrderId(jdqOrderId);
                userAddressBook.setUserInfoId(userInfoId);
                userAddressBook.setMobile(addressBook.getMobile());
                userAddressBook.setName(addressBook.getName());
                userAddressBook.setCreateTime(date);
                userAddressBookMapper.insert(userAddressBook);
            });

        }

        // 设备信息
        if (pushPhaseTwoDto.getDevice_info() != null) {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceInfoId(UUIDUtils.getUUID());
            deviceInfo.setJdqOrderId(jdqOrderId);
            deviceInfo.setUserInfoId(userInfoId);
            deviceInfo.setDeviceId(pushPhaseTwoDto.getDevice_info().getDevice_id());
            deviceInfo.setIp(pushPhaseTwoDto.getDevice_info().getIp());
            deviceInfo.setDeviceType(pushPhaseTwoDto.getDevice_info().getDevice_type());
            deviceInfo.setDeviceModel(pushPhaseTwoDto.getDevice_info().getDevice_model());
            deviceInfo.setDeviceOs(pushPhaseTwoDto.getDevice_info().getDevice_os());
            deviceInfo.setOpenudid(pushPhaseTwoDto.getDevice_info().getOpenudid());
            deviceInfo.setJailbreakFlag(pushPhaseTwoDto.getDevice_info().getJailbreak_flag());
            deviceInfo.setRootFlag(pushPhaseTwoDto.getDevice_info().getRoot_flag());
            deviceInfo.setCreateTime(date);
            deviceInfoMapper.insert(deviceInfo);
        }

        log.info("二推补充信息入库结束 {}", pushPhaseTwoEvent.getJdqOrderId());
        // 删除redisKey
        batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_TWO_DTO + jdqOrderId);

        // 推送风控事件
        PushRiskEvent pushRiskEvent = new PushRiskEvent();
        pushRiskEvent.setJdqOrderId(jdqOrderId);
        mqSenderService.sendMq(RabbitMqEnum.PUSH_PHASE_RISK, pushRiskEvent ,true);
        return true;
    }

    @Override
    public boolean pushPhaseOtherInfoAsync(PushPhaseOtherInfoEvent pushPhaseOtherInfoEvent) {
        log.info("其他信息入库开始 {}", pushPhaseOtherInfoEvent.getJdqOrderId());
        String jdqOrderId = pushPhaseOtherInfoEvent.getJdqOrderId();

         try {
             UserInfo userInfo = userInfoMapper.selectByJdqOrderId(jdqOrderId);
             if (userInfo == null) {
                 log.error("不存在此订单 {}", jdqOrderId);
                 return true;
             }

             // 校验是否需要上传身份证相关图片到阿里云
             String bucketName = aliyunOssProperties.getBucketName();
             if (StringUtils.isNotEmpty(userInfo.getIdPositive()) && !userInfo.getIdPositive().contains(bucketName)) {
                 log.info("其他信息入库 身份证正面图片url");
//                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdPositive(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdPositive());
                 if (picUrl != null) {
                     userInfo.setIdPositive(picUrl);
                 }
             }

             if (StringUtils.isNotEmpty(userInfo.getIdNegative()) && !userInfo.getIdNegative().contains(bucketName)) {
                 log.info("其他信息入库 身份证反面图片url");
//                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdNegative(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getIdNegative());
                 if (picUrl != null) {
                     userInfo.setIdNegative(picUrl);
                 }
             }

             if (StringUtils.isNotEmpty(userInfo.getFace()) && !userInfo.getFace().contains(bucketName)) {
                 log.info("其他信息入库 人脸照片url");
//                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getFace(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getFace());
                 if (picUrl != null) {
                     userInfo.setFace(picUrl);
                 }
             }

             if (StringUtils.isNotEmpty(userInfo.getHandIdPhoto()) && !userInfo.getHandIdPhoto().contains(bucketName)) {
                 log.info("其他信息入库 手持身份证照片url");
//                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getHandIdPhoto(), aliyunOssProperties.getBasePath(), aliyunOssProperties.getApiPath(), aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(), aliyunOssProperties.getBucketName());
                 String picUrl = AliyunOssUtils.uploadUrlToOss(userInfo.getHandIdPhoto());
                 if (picUrl != null) {
                     userInfo.setHandIdPhoto(picUrl);
                 }
             }

             log.info("更新其他信息入库 {} {}", userInfo.getJdqOrderId(), userInfo);
             userInfoMapper.updateOtherInfoByJdqOrderId(userInfo);

             return true;
         } catch (Exception e) {
             e.printStackTrace();
             log.error("其他信息入库失败", e);
         }
         return true;
    }


    private String relationChange(int jdqRelation) {
        // father	父亲、mother	母亲、spouse	配偶、child	子女、other_relative	其他亲属、friend	朋友、coworker	同事、others	其他**/
        // jdq 第一联系人关系code（81=父母姓名、82=配偶姓名、91=兄弟姓名、92=姐妹姓名、94=亲属姓名、101=子女姓名、83=兄弟姐妹姓名、76=同事姓名、77=同学姓名、23=紧急联系人、96=其他联系人、138=朋友）
        String relation = "others";
        switch (jdqRelation) {
            case 81:
                relation = "father";
                break;
            case 82:
                relation = "spouse";
                break;
            case 91:
            case 92:
            case 94:
            case 101:
            case 83:
                relation = "other_relative";
                break;
            case 76:
                relation = "coworker";
                break;
            case 77:
            case 138:
                relation = "friend";
                break;
            case 23:
            case 96:
                relation = "others";
                break;
            default:
                break;
        }

        return relation;
    }

    /**
     * 根据工作职业返回公司行业
     * @param workProfession
     * @return
     */
    private Integer checkIndustryByWorkProfession(int workProfession) {
        // 工作职业code码（1=农牧业 2=木材/森林业 3=矿业/采石业 4=交通运输业 5=餐旅业 6=建筑工程业 7=制造业 8=娱乐业 9=文教 10=金融业 11=服务业 12=治安人员 13=军人 14=其他）
        Integer industry =1006012;
        switch (workProfession) {
            case 1:
            case 2:
                industry = 1005711;
                break;
            case 3:
                industry = 1005010;
                break;
            case 4:
                industry = 1004809;
                break;
            case 5:
                industry = 1004508;
                break;
            case 6:
                industry = 1003206;
                break;
            case 7:
                industry = 1005410;
                break;
            case 8:
                industry = 1004508;
                break;
            case 9:
                industry = 1003907;
                break;
            case 10:
                industry = 1001002;
                break;
            case 11:
                industry = 1004708;
                break;
            case 12:
                industry = 1003106;
                break;
            case 13:
                industry = 1005511;
                break;
            case 14:
                industry = 1006012;
                break;
            default:
                break;
        }

        return industry;
    }

    @Override
    @SleuthLoggerExclude
    public UserInfo selectByIdNumberAndMobile(String idNumber, String mobile) {
        return userInfoMapper.selectByIdNumberAndMobile(idNumber, mobile);
    }

    @Override
    @SleuthLoggerExclude
    public List<UserInfo> selectByIdNumber(String idNumber) {
        return userInfoMapper.selectByIdNumber(idNumber);
    }

    @Override
    public UserInfo selectByJdqOrderId(String jdqOrderId) {
        return userInfoMapper.selectByJdqOrderId(jdqOrderId);
    }

    @Override
    @SleuthLoggerExclude
    public List<UserInfo> selectUnRiskUserInfo() {
        return userInfoMapper.selectUnRiskUserInfo();
    }

    @Override
    @SleuthLoggerExclude
    public List<UserInfo> selectNotCallBackRiskUserInfo() {
        return userInfoMapper.selectNotCallBackRiskUserInfo();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo selectLockByUserInfoId(String userInfoId) {
        return userInfoMapper.selectLockByUserInfoId(userInfoId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo selectLockByIdNumber(String idNumber) {
        return userInfoMapper.selectLockByIdNumber(idNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo selectLockByAppSerialNumber(String appSerialNumber) {
        return userInfoMapper.selectLockByAppSerialNumber(appSerialNumber);
    }

    @Override
    public List<UserInfo> selectByPhoneIdNumberMd5(String phoneIdNumberMd5) {
        return userInfoMapper.selectByPhoneIdNumberMd5(phoneIdNumberMd5);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean pushUserInfoToWaitStatus(String userInfoId, String jdqOrderId, String appSerialNumber) {

        log.info("风控推送处理 {}", jdqOrderId);

        // 防止重复推送风控
        UserInfo userInfo = selectLockByUserInfoId(userInfoId);

        if (userInfo == null) {
            log.error("不存在此用户信息 {}", jdqOrderId);
            return false;
        }

        // 二推数据未入库，不允许发送给风控
        if (userInfo.getPushPhaseState().intValue() != 5) {
            log.error("二推数据未入库,不允许发送给风控 {}", userInfo);
            return false;
        }

        // 运营商数据未推送成功，不允许发送给风控
        if (userInfo.getPushOperatorState().intValue() != 0) {
            log.error("运营商数据未审核成功,不允许发送给风控 {}", userInfo);
            return false;
        }

        // 非初始化，不允许发送给风控
        if (userInfo.getPushRiskState().intValue() != 0) {
            log.error("已经推送给风控系统,不允许发送给风控 {}", userInfo);
            return false;
        }

        // 先推送到待审核
        try {
            RestResult<Void> restResult = orderCloudHystrixService.orderToWaitAudit(jdqCommonProperties.getRiskAppId(), userInfo.getLocalOrderId());
            if (restResult == null || !restResult.isSuccess()) {
                log.error("订单修改为待审核失败 {}", userInfo.getLocalOrderId());
            }
        } catch (Exception ex) {
            log.error("订单修改为待审核失败 {}", ex);
        }

        // 修改本地订单为待审核
        userInfo.setPushRiskState(1);
        userInfo.setPushOperatorState(1);
        userInfo.setAppSerialNumber(appSerialNumber);
        userInfo.setModifyTime(new Date());
        userInfoMapper.updateByPrimaryKey(userInfo);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean pushUserInfoToInitStatus(String userInfoId, String jdqOrderId) {

        log.info("风控推送失败，用户信息修复处理 {}", jdqOrderId);
        UserInfo userInfo = selectLockByUserInfoId(userInfoId);

        if (userInfo == null) {
            log.error("不存在此用户信息 {}", jdqOrderId);
            return false;
        }

        // 二推数据未入库，不允许发送给风控
        if (userInfo.getPushPhaseState().intValue() != 5) {
            log.error("二推数据未入库 {}", userInfo);
            return false;
        }

        // 运营商数据未推送成功，不允许发送给风控
        if (userInfo.getPushOperatorState().intValue() != 1) {
            log.error("运营商数据未审核成功,不允许发送给风控 {}", userInfo);
            return true;
        }

        // 非初始化，不允许发送给风控
        if (userInfo.getPushRiskState().intValue() != 1) {
            log.error("已经推送给风控系统,不允许发送给风控 {}", userInfo);
            return true;
        }

        // 修改本地订单为初始状态
        userInfo.setPushRiskState(0);
        userInfo.setPushOperatorState(0);
        userInfo.setAppSerialNumber(null);
        userInfo.setModifyTime(new Date());
        userInfoMapper.updateByPrimaryKey(userInfo);
        return true;

    }

    @Override
    public boolean pushUserInfoToRepeatStatus(String userInfoId, String jdqOrderId) {
        log.error("用户身份证风控重复推送，不需要推送{}", jdqOrderId);
        UserInfo userInfo = selectLockByUserInfoId(userInfoId);

        if (userInfo == null) {
            log.error("不存在此用户信息 {}", jdqOrderId);
            return false;
        }

        // 二推数据未入库，不允许发送给风控
        if (userInfo.getPushPhaseState().intValue() != 5) {
            log.error("二推数据未入库 {}", userInfo);
            return false;
        }

        // 运营商数据未推送成功，不允许发送给风控
        if (userInfo.getPushOperatorState().intValue() != 1) {
            log.error("运营商数据未审核成功,不允许发送给风控 {}", userInfo);
            return true;
        }

        // 非初始化，不允许发送给风控
        if (userInfo.getPushRiskState().intValue() != 1) {
            log.error("已经推送给风控系统,不允许发送给风控 {}", userInfo);
            return true;
        }

        // 修改本地订单为重复身份证
        userInfo.setPushRiskState(4);
        userInfo.setPushOperatorState(4);
        userInfo.setModifyTime(new Date());
        userInfoMapper.updateByPrimaryKey(userInfo);
        return true;
    }

    @Override
    public List<UserInfo> selectUnionPhoneIdNumberMd5ByIdNumber(String idNumber) {
        return userInfoMapper.selectUnionPhoneIdNumberMd5ByIdNumber(idNumber);
    }

    /**
     * 审核成功事件
     * @param userInfo
     * @return
     */
    public boolean auditSuccess(UserInfo userInfo) {


        try {
            // 审核事件
            AuditEvent auditEvent = new AuditEvent();
            // 事件头
            EventHeader eventHeader = new EventHeader();
            eventHeader.setAppid(jdqCommonProperties.getRiskAppId());
            eventHeader.setAppName(jdqCommonProperties.getRiskAppName().toString());
            eventHeader.setHappenTime(System.currentTimeMillis());
            eventHeader.setMessageId(IdUtil.getNumberUuid(UuidPrefix.TH_MESSAGE));
            eventHeader.setOrderId(userInfo.getLocalOrderId());
            eventHeader.setSuccess(true);
            auditEvent.setEventHeader(eventHeader);

            // 事件主体信息
            EventUserInfo eventUserInfo = new EventUserInfo();
            eventUserInfo.setMobile(userInfo.getMobile());
            eventUserInfo.setIdentityNo(userInfo.getIdNumber());
            eventUserInfo.setUserName(userInfo.getName());
            eventUserInfo.setAddress(userInfo.getIdCardAddress());
            eventUserInfo.setOtherOrderId(userInfo.getJdqOrderId());
            auditEvent.setEventUserInfo(eventUserInfo);

            return mqSenderService.sendMq(RabbitMqEnum.AUDIT_EVENT, auditEvent, true);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("推送审核成功事件失败 {}", e);
        }

        return false;

    }


    /**
     * 审核失败事件
     * @param userInfo
     * @param msg
     * @return
     */
    public boolean auditFailed(UserInfo userInfo, String msg) {

        try {
            // 审核事件
            AuditEvent auditEvent = new AuditEvent();
            // 事件头
            EventHeader eventHeader = new EventHeader();
            eventHeader.setAppid(jdqCommonProperties.getRiskAppId());
            eventHeader.setAppName(jdqCommonProperties.getRiskAppName().toString());
            eventHeader.setHappenTime(System.currentTimeMillis());
            eventHeader.setMessageId(IdUtil.getNumberUuid(UuidPrefix.TH_MESSAGE));
            eventHeader.setOrderId(userInfo.getLocalOrderId());
            eventHeader.setSuccess(false);
            auditEvent.setEventHeader(eventHeader);

            // 事件主体信息
            EventUserInfo eventUserInfo = new EventUserInfo();
            eventUserInfo.setMobile(userInfo.getMobile());
            eventUserInfo.setIdentityNo(userInfo.getIdNumber());
            eventUserInfo.setUserName(userInfo.getName());
            eventUserInfo.setAddress(userInfo.getIdCardAddress());
            eventUserInfo.setOtherOrderId(userInfo.getJdqOrderId());
            if (StringUtils.isEmpty(userInfo.getPhoneIdNumberMd5())) {
                userInfo.setPhoneIdNumberMd5(DigestUtils.md5Hex((userInfo.getMobile()+userInfo.getIdNumber().toUpperCase())).toLowerCase());
            }
            eventUserInfo.setPhoneIdNumberMd5(userInfo.getPhoneIdNumberMd5());
            auditEvent.setEventUserInfo(eventUserInfo);

            // 事件错误信息
            EventFailureResult eventFailureResult = new EventFailureResult();
            eventFailureResult.setCode("500");
            eventFailureResult.setReason(msg);
            auditEvent.setEventFailureResult(eventFailureResult);

            return mqSenderService.sendMq(RabbitMqEnum.AUDIT_EVENT, auditEvent, true);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("推送审核失败事件失败 {}", e);
        }

        return false;

    }


}
