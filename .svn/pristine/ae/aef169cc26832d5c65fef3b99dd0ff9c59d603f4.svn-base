package com.shangyong.thjdq.controller;

import com.alibaba.fastjson.JSONObject;
import com.shangyong.backend.fegin.BlacklistCloudHystrixService;
import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.rest.feign.BaseUserCloudHystrixService;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.dto.CheckUserInfoDto;
import com.shangyong.thcore.vo.BaseUserInfoVo;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.dto.CheckUserDto;
import com.shangyong.thjdq.dto.JdqInvokeChannelDto;
import com.shangyong.thjdq.dto.PushPhaseOneDto;
import com.shangyong.thjdq.dto.PushPhaseTwoDto;
import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.enums.RabbitMqEnum;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;
import com.shangyong.thjdq.handler.exception.JdqSignException;
import com.shangyong.thjdq.mq.MqSender;
import com.shangyong.thjdq.service.JdqSignService;
import com.shangyong.thjdq.service.UserInfoService;
import com.shangyong.thjdq.service.UserService;
import com.shangyong.thjdq.util.UUIDUtils;
import com.shangyong.thjdq.vo.CheckUserVo;
import com.shangyong.thjdq.vo.PushPhaseOneVo;
import com.shangyong.thjdq.vo.PushPhaseTwoVo;
import com.shangyong.thjdq.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 【借点钱】用户检测，用户信息推送接收相关接口
 * Created by zbb on 2019-03-13.
 */
@Api(tags = "zbb-郑斌斌-用户检测，用户信息推送接收相关接口")
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private BatchRedisTemplate batchRedisTemplate;
    @Autowired
    private JdqSignService jdqSignService;
    @Autowired
    private MqSender mqSenderService;
    @Autowired
    private BlacklistCloudHystrixService blacklistCloudHystrixService;
    @Autowired
    private BaseUserCloudHystrixService baseUserCloudHystrixService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;

    /**
     * 接收用户检测接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收用户检测接口")
    @RequestMapping(value="/checkUser", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<CheckUserVo> checkUser(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        CheckUserVo checkUserVo = new CheckUserVo();
        Date now = new Date();
        // 默认不可借
        checkUserVo.setIf_can_loan("0");
        try {

            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.info("检测用户接口 data {}", jdqInvokeChannelDto.getData());
            CheckUserDto checkUserDto = JSONObject.parseObject(jdqInvokeChannelDto.getData(), CheckUserDto.class);

            // 校验用户类型
            BaseUserInfoVo baseUserInfoVo = null;
            try {
                // 0-借点钱老用户 (定义：由借点钱渠道导流到合作方的用户，非第一次申请) 1-新用户 （定义：由借点钱渠道导流到合作方的用户，第一次申请） 2-合作方老用户 （定义：该用户为机构原有用户，且非借点钱渠道注册）
                checkUserVo.setUser_type("2");
                CheckUserInfoDto checkUserInfoDto = new CheckUserInfoDto();
                checkUserInfoDto.setIdentityNoPrefix(checkUserDto.getId_number().replace("*",""));
                checkUserInfoDto.setMobilePrefix(checkUserDto.getPhone().replace("*",""));
                checkUserInfoDto.setSignMd5(checkUserDto.getPhone_id_number_md5());
                checkUserInfoDto.setUserName(checkUserDto.getUser_name());
                log.info("checkUserInfoDto:{}",checkUserInfoDto);
                RestResult<BaseUserInfoVo> baseUserInfoVoRestResult = baseUserCloudHystrixService.checkAndSearch(jdqCommonProperties.getRiskAppId(), checkUserInfoDto);
                log.info("校验用户result:{}", baseUserInfoVoRestResult);

                if (baseUserInfoVoRestResult == null || !baseUserInfoVoRestResult.isSuccess()) {
                    log.error("cloud接口异常 baseUserCloudHystrixService.checkAndSearch");
                    return ResponseCode.FAILED.toResponse();
                }

                // 校验新老用户
                baseUserInfoVo = baseUserInfoVoRestResult.getData().getBody();
                log.info("校验用户数据:{}", baseUserInfoVo);
                if (!baseUserInfoVo.isIfExistUser()) {
                    // 新用户
                    checkUserVo.setUser_type("1");
                } else {
                    // 校验是否是老用户
                    if (baseUserInfoVo.getAppid().equals(jdqCommonProperties.getRiskAppId())) {
                        // 是借点钱老用户
                        checkUserVo.setUser_type("0");
                    } else {
                        // 是自己老用户
                        checkUserVo.setUser_type("2");
                    }
                }

                // 校验是否存在隔离期
                if (baseUserInfoVo.isIfQuarantine()) {
                    // 处于隔离期
                    checkUserVo.setIf_can_loan("0");
                    checkUserVo.setReason("3");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(LocalDateUtil.parseToDate(baseUserInfoVo.getQuarantineEndTime(), "yyyy-MM-dd HH:mm:ss"));
                    calendar.add(Calendar.DATE, 1);
                    checkUserVo.setCan_loan_time(LocalDateUtil.dateToString(calendar.getTime(),"yyyy-MM-dd"));
                    return ResponseCode.OK.toResponse(checkUserVo);
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error("校验用户 异常 {}", e);
                return ResponseCode.FAILED.toResponse();
            }

            // 校验在途
            try {

                // 本地在途订单校验 同一个身份证在还订单只能允许存在一笔
                // 通过md5查询到身份证，再通过身份证去查在途
                String identityNo = null;
                if (baseUserInfoVo == null || StringUtils.isEmpty(baseUserInfoVo.getIdentityNo())) {
                    if (!StringUtils.isEmpty(checkUserDto.getPhone_id_number_md5())) {
                        List<UserInfo> userInfoList = userInfoService.selectByPhoneIdNumberMd5(checkUserDto.getPhone_id_number_md5());
                        if (userInfoList != null && userInfoList.size() > 0) {
                            identityNo = userInfoList.get(0).getIdNumber();
                        }
                    }

                } else if (baseUserInfoVo != null && !StringUtils.isEmpty(baseUserInfoVo.getIdentityNo())) {
                    identityNo = baseUserInfoVo.getIdentityNo();
                }

                if (!StringUtils.isEmpty(identityNo)) {
                    RestResult<Void>  restResult = orderCloudHystrixService.orderOnWayCheck(jdqCommonProperties.getRiskAppId(), identityNo);

                    if (restResult == null) {
                        log.error("cloud接口异常 orderCloudHystrixService.orderOnWayCheck");
                        return ResponseCode.FAILED.toResponse();
                    }

                    log.info("在途result:{}", restResult);
                    if (restResult != null && restResult.isSuccess()) {
                        checkUserVo.setIf_can_loan("0");
                        checkUserVo.setReason("2");
                        return ResponseCode.OK.toResponse(checkUserVo);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error("校验在途 异常 {}", e);
                return ResponseCode.FAILED.toResponse();
            }

            // 更新用户state
            userService.updateUserState(checkUserDto, checkUserVo.getUser_type());

            // 判断是否命中黑名单
            try {
                RestResult<Boolean>  restResult = blacklistCloudHystrixService.isBlacklistByWhiteCardMD5(checkUserDto.getPhone_id_number_md5());
                log.info("否命中黑名单{}", restResult);

                if (restResult == null || !restResult.isSuccess()) {
                    log.error("cloud接口异常 blacklistCloudHystrixService.isBlacklistByWhiteCardMD5");
                    return ResponseCode.FAILED.toResponse();
                }

                if (restResult != null && restResult.isSuccess() && restResult.getData() != null && restResult.getData().getBody() != null && restResult.getData().getBody()) {
                    // 命中黑名单
                    checkUserVo.setIf_can_loan("0");
                    // 不可借原因码，if_can_loan为0时,则该字段必须有值，值类型如下： 1=黑名单 2=在途订单 3=隔离期 4=非借点钱渠道老用户 5=借点钱渠道老用户
                    checkUserVo.setReason("1");
                    return ResponseCode.OK.toResponse(checkUserVo);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("判断是否命中黑名单处理失败 {}", e);
                return ResponseCode.FAILED.toResponse();
            }

            checkUserVo.setIf_can_loan("1");

            log.info("接收用户检测接口处理耗时：{}", new Date().getTime() - now.getTime());

            if (new Date().getTime() - now.getTime() >= 2000) {
                log.error("接收用户检测接口处理耗时超过2S了，请注意！");
            }

            return ResponseCode.OK.toResponse(checkUserVo);
        } catch (Exception e) {
            log.error("接收用户检测接口 异常 {}", e);
        }

        return ResponseCode.FAILED.toResponse();

    }

    /**
     * 接收推送基本信息接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收推送基本信息接口")
    @RequestMapping(value="/pushPhaseOne", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SleuthLoggerExclude(excludeOut = false, excludeInput = true)
    public Response<PushPhaseOneVo> pushPhaseOne(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        log.info("接收推送基本信息接口开始");
        Date now = new Date();
        PushPhaseOneVo pushPhaseOneVo = new PushPhaseOneVo();
        // 默认审批前需要绑卡
        pushPhaseOneVo.setBind_card_flag("1");
        String jdqOrderId = "";
        try {

            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.debug("接收推送基本信息 data {}", jdqInvokeChannelDto.getData());

            PushPhaseOneDto pushPhaseOneDto = JSONObject.parseObject(jdqInvokeChannelDto.getData(), PushPhaseOneDto.class);
            jdqOrderId = pushPhaseOneDto.getJdq_order_id();

            log.info("接收推送基本信息 data {}", pushPhaseOneDto);

            // 校验一推数据是否已经入库
            UserInfo userInfo = userInfoService.selectByJdqOrderId(jdqOrderId);
            if (userInfo != null) {
                pushPhaseOneVo.setOrder_id(userInfo.getLocalOrderId());
                return ResponseCode.OK.toResponse(pushPhaseOneVo, "进件成功");
            }

            // 防止重复请求
            String addUserInfoRedisKey = RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_REPEAT + jdqOrderId;
            if(!batchRedisTemplate.setNX(addUserInfoRedisKey, jdqOrderId, 30, TimeUnit.SECONDS)) {
                log.error("重复请求 redisKey:{}", addUserInfoRedisKey);
                return ResponseCode.FAILED.toResponse();
            }

            pushPhaseOneDto.setUserInfoId(UUIDUtils.getUUID());
            // 存入redis
            batchRedisTemplate.set(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_DTO + jdqOrderId, pushPhaseOneDto, 4, TimeUnit.DAYS);
            // 获取到摩羯运营商分析报文存入redis后，再存入mongo
            Map m = JSONObject.parseObject(jdqInvokeChannelDto.getData());
            if(m.get("moxie_telecom_report") != null) {
                batchRedisTemplate.set(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_MOXIE_REPORT_DTO + jdqOrderId, m.get("moxie_telecom_report").toString(),2, TimeUnit.DAYS);
            }
            // 无源比对结果数据
            if(m.get("face_result") != null) {
                pushPhaseOneDto.setFaceResult(m.get("face_result").toString());
            }
            // 有源比对结果数据
            if(m.get("face_result_active") != null) {
                pushPhaseOneDto.setFaceResultActive(m.get("face_result_active").toString());
            }

            Response<PushPhaseOneVo> pushPhaseOneVoRes = userInfoService.pushPhaseOne(pushPhaseOneDto);
            if (pushPhaseOneVoRes == null || ResponseCode.FAILED.getCode().equals(pushPhaseOneVoRes.getCode())) {
                log.error("接收推送基本信息处理失败");
                return ResponseCode.FAILED.toResponse();
            }

            // 调用一推信息生产者
            PushPhaseOneEvent pushPhaseOneEvent = new PushPhaseOneEvent();
            pushPhaseOneEvent.setJdqOrderId(jdqOrderId);
            mqSenderService.sendMq(RabbitMqEnum.PUSH_PHASE_ONE, pushPhaseOneEvent ,true);

            log.info("接收推送基本信息处理耗时：{}", new Date().getTime() - now.getTime());
            if (new Date().getTime() - now.getTime() >= 20000) {
                log.error("接收推送基本信息处理耗时超过20S了，请注意！");
            }
            return pushPhaseOneVoRes;

        } catch (Exception e) {
            log.error("接收推送基本信息接口 异常 {}", e);
        }

        batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_DTO + jdqOrderId);
        batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_ONE_MOXIE_REPORT_DTO + jdqOrderId);
        return ResponseCode.FAILED.toResponse();
    }

    /**
     * 接收推送补充信息接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收推送补充信息接口")
    @RequestMapping(value="/pushPhaseTwo", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SleuthLoggerExclude(excludeOut = false, excludeInput = true)
    public Response<PushPhaseTwoVo> pushPhaseTwo(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        String jdqOrderId = "";
        Date now = new Date();
        try {
            log.info("接收推送补充信息接口开始");

            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.debug("接收推送补充信息接口 data {}", jdqInvokeChannelDto.getData());

            PushPhaseTwoDto pushPhaseTwoDto = JSONObject.parseObject(jdqInvokeChannelDto.getData(), PushPhaseTwoDto.class);
            jdqOrderId = pushPhaseTwoDto.getJdq_order_id();

            log.info("接收推送补充信息接口 data {}", pushPhaseTwoDto);

            // 校验二推数据是否已经入库
            PushPhaseTwoVo pushPhaseTwoVo = new PushPhaseTwoVo();
            UserInfo userInfo = userInfoService.selectByJdqOrderId(jdqOrderId);
            if (userInfo != null) {
                pushPhaseTwoVo.setOrder_id(userInfo.getLocalOrderId());
                if(userInfo.getPushPhaseState() != null &&  5 == userInfo.getPushPhaseState().intValue()) {
                    return ResponseCode.OK.toResponse(pushPhaseTwoVo, "进件成功");
                }
            }

            // 防止重复请求
            String pushPhaseTwoRedisKey = RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_TWO_REPEAT + jdqOrderId;
            if(!batchRedisTemplate.setNX(pushPhaseTwoRedisKey, jdqOrderId, 30, TimeUnit.SECONDS)) {
                log.error("重复请求 redisKey:{}", pushPhaseTwoRedisKey);
                return ResponseCode.FAILED.toResponse();
            }

            // 存入userInfoId
            pushPhaseTwoDto.setUserInfoId(userInfo.getUserInfoId());
            // 存入redis
            batchRedisTemplate.set(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_TWO_DTO + jdqOrderId, pushPhaseTwoDto,4, TimeUnit.DAYS);

            // 调用二推信息生产者
            PushPhaseTwoEvent pushPhaseTwoEvent = new PushPhaseTwoEvent();
            pushPhaseTwoEvent.setJdqOrderId(jdqOrderId);
            mqSenderService.sendMq(RabbitMqEnum.PUSH_PHASE_TWO, pushPhaseTwoEvent ,true);

            pushPhaseTwoVo.setOrder_id(userInfo.getLocalOrderId());
            log.info("接收推送补充信息处理耗时：{}", new Date().getTime() - now.getTime());
            if (new Date().getTime() - now.getTime() >= 20000) {
                log.error("接收推送补充信息处理耗时超过20S了，请注意！");
            }
            return ResponseCode.OK.toResponse(pushPhaseTwoVo, "进件成功");

        } catch (Exception e) {
            log.error("接收推送补充信息接口 异常 {}", e);
        }

        batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_TWO_DTO + jdqOrderId);
        log.info("接收推送补充信息接口结束");
        return ResponseCode.FAILED.toResponse();
    }

}
