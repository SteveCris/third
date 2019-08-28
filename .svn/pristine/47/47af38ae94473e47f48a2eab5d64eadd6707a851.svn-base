package com.shangyong.thjdq.controller;

import com.shangyong.backend.dto.AddressBookDto;
import com.shangyong.backend.fegin.UploadCloudHystrixService;
import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.dao.UserAddressBookMapper;
import com.shangyong.thjdq.dao.UserInfoMapper;
import com.shangyong.thjdq.dto.RiskAddressBookDto;
import com.shangyong.thjdq.dto.RiskAddressBookJsonInfoDto;
import com.shangyong.thjdq.dto.RiskAddressBookJsonInfoListDto;
import com.shangyong.thjdq.entity.UserAddressBook;
import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;
import com.shangyong.thjdq.service.*;
import com.shangyong.thjdq.util.IpBlackListUtil;
import com.shangyong.thjdq.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 * Created by ybds on 2019-03-19.
 */

@Api(tags = "zbb-郑斌斌-定时任务controller")
@RestController
public class JobController {

    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    /**
     * 时间大小由小到大排列，从秒开始，顺序为 秒，分，时，天，月，年    *为任意 ？为无限制。 
     * 具体如下： 
     * "0/10 * * * * ?" 每10秒触发 
     * "0 0 12 * * ?" 每天中午12点触发 
     * "0 15 10 ? * *" 每天上午10:15触发 
     * "0 15 10 * * ?" 每天上午10:15触发 
     * "0 15 10 * * ? *" 每天上午10:15触发 
     * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
     * "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
     * "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
     * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
     * "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
     * "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
     * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
     * "0 15 10 15 * ?" 每月15日上午10:15触发 
     * "0 15 10 L * ?" 每月最后一日的上午10:15触发 
     * "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
     * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
     * "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发 
     */

    @Autowired
    private BatchRedisTemplate batchRedisTemplate;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RiskJdqService riskJdqService;
    @Autowired
    private JdqCreateOrderPushEventService jdqCreateOrderPushEventService;
    @Autowired
    private AsyncCommonService asyncCommonService;
    @Autowired
    private UserAddressBookMapper userAddressBookMapper;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private UploadCloudHystrixService uploadCloudHystrixService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 每10秒处理一次
     * 自动推送用户信息给风控系统
     *
     * @return
     */
    @SleuthLoggerExclude(excludeOut = true, excludeInput = true)
    @ApiOperation("自动推送用户信息给风控系统")
//    @Scheduled(cron = "0/10 * * * * ?")
    @RequestMapping(value = "/job/pushJdqInfoToRisk", method = RequestMethod.GET)
    public Response pushJdqInfoToRisk() {

//        log.info("自动推送用户信息给风控系统任务开始");
        long now = System.currentTimeMillis();

        // 防止重复请求
        String pushRiskRepeatKey = RedisKeyCoreConstant.JDQ_JOB.PUSH_RISK_REPEAT;
        if (!batchRedisTemplate.setNX(pushRiskRepeatKey, "1", 2, TimeUnit.MINUTES)) {
            log.error("重复请求");
            return ResponseCode.REPEAT_REQUEST.toResponse();
        }

        try {
            int totalSize = 0;
            int successCount = 0;

            // 查询 pushPhaseState=5 (5:二推成功) and pushOperatorState=0(0:初始化) and pushRiskState=0(0:初始化)
            List<UserInfo> userInfoList = userInfoService.selectUnRiskUserInfo();
            if (userInfoList != null && userInfoList.size() > 0) {
                totalSize = userInfoList.size();
                for (int i = 0; i < userInfoList.size(); i++) {
                    if (riskJdqService.jdqPushUserInfoToRisk(userInfoList.get(i))) {
                        successCount++;
                    }
                }
            }

            if (totalSize > 0) {
                log.info("推送风控共{}条记录，成功{},失败{},耗时{}ms", totalSize, successCount, totalSize - successCount, System.currentTimeMillis() - now);
            }

            batchRedisTemplate.delete(pushRiskRepeatKey);

            return ResponseCode.OK.toResponse();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("自动推送用户信息给风控系统 异常 {}", e);
        }

        batchRedisTemplate.delete(pushRiskRepeatKey);
        return ResponseCode.FAILED.toResponse();
    }


    /**
     * 每隔5分钟执行一次
     * 查询推送中风控系统的所有用户
     *
     * @return
     */
    @SleuthLoggerExclude(excludeOut = true, excludeInput = true)
    @ApiOperation("查询推送未回调风控的所有用户")
//    @Scheduled(cron = "0 0/5 * * * ?")
    @RequestMapping(value = "/job/checkIfAuditCallBack", method = RequestMethod.GET)
    public Response checkIfAuditCallBack() {

        // 防止重复请求
        String redisKey = RedisKeyCoreConstant.JDQ_JOB.PUSH_NOT_CALL_BACK_RISK_REPEAT;
        if (!batchRedisTemplate.setNX(redisKey, "1", 10, TimeUnit.MINUTES)) {
            log.error("重复请求");
            return ResponseCode.REPEAT_REQUEST.toResponse();
        }

        try {

            // 查询 pushPhaseState=5 (5:二推成功) and pushOperatorState=1(1:推送中) and pushRiskState=1(1:推送中)
            List<UserInfo> userInfoList = userInfoService.selectNotCallBackRiskUserInfo();
            if (userInfoList != null && userInfoList.size() > 0) {
                userInfoList.forEach(u -> {
                    log.error("推送风控超过30分钟未回调[] jdqOrderId:{} customerId:{}", LocalDateUtil.dateToString(u.getModifyTime(), "yyyy-MM-dd HH:mm:ss"), u.getJdqOrderId(), u.getCustomerId());

                    // TODO 以下代码是发送钉钉消息，只需增加发送钉钉消息代码并取消注释即可
//                    long times = batchRedisTemplate.increment(
//                            RedisKeyCoreConstant.JDQ_JOB.PUSH_NOT_CALL_BACK_RISK_REPEAT + u.getJdqOrderId(), 1, 1, TimeUnit.HOURS);
//                    if (times > 1) {
//                        log.info("推送风控超过30分钟未回调，钉钉提醒超出1次，不再提醒 {}", u.getJdqOrderId());
//                        return;
//                    }
                    // 发送钉钉消息

                });
            }

            batchRedisTemplate.delete(redisKey);
            return ResponseCode.OK.toResponse();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询推送未回调风控的所有用户 异常 {}", e);
        }

        batchRedisTemplate.delete(redisKey);
        return ResponseCode.FAILED.toResponse();
    }

    /**
     * 手动推送借点钱订单状态
     *
     * @param jdqOrderId
     * @return
     */
    @ApiOperation("手动推送借点钱订单状态")
    @RequestMapping(value = "/job/pushJdqOrderState", method = RequestMethod.GET)
    public Response pushJdqOrderState(@RequestParam("jdqOrderId") String jdqOrderId) {
        if (StringUtils.isNotEmpty(jdqOrderId)) {
            if (!jdqCreateOrderPushEventService.createOrderPushEvent(jdqOrderId, null, null)) {
                return ResponseCode.FAILED.toResponse();
            }
        }
        return ResponseCode.OK.toResponse();
    }

    /**
     * 手动一推信息入库
     *
     * @param jdqOrderId
     * @return
     */
    @ApiOperation("手动一推信息入库")
    @RequestMapping(value = "/job/pushPhaseOneData", method = RequestMethod.GET)
    public Response pushPhaseOneData(@RequestParam("jdqOrderId") String jdqOrderId) {

        if (StringUtils.isEmpty(jdqOrderId)) {
            return ResponseCode.VALIDATOR_ERROR.toResponse();
        }

        // 防止重复请求
        String pushPhaseOneRedisKey = RedisKeyCoreConstant.JDQ_JOB.PUSH_PHASE_ONE_DATA + jdqOrderId;
        if (!batchRedisTemplate.setNX(pushPhaseOneRedisKey, jdqOrderId, 30, TimeUnit.SECONDS)) {
            log.error("重复请求 redisKey:{}", pushPhaseOneRedisKey);
            return ResponseCode.REPEAT_REQUEST.toResponse();
        }

        String[] arrJdqOrderId = new String[]{jdqOrderId};
        if (jdqOrderId.contains(",")) {
            arrJdqOrderId = jdqOrderId.split(",");
        }

        for (int i = 0; i < arrJdqOrderId.length; i++) {
            PushPhaseOneEvent pushPhaseOneEvent = new PushPhaseOneEvent();
            pushPhaseOneEvent.setJdqOrderId(arrJdqOrderId[i]);
            asyncCommonService.pushPhaseOneDataAsync(pushPhaseOneEvent);
        }

        return ResponseCode.OK.toResponse();
    }

    /**
     * 手动二推信息入库
     *
     * @param jdqOrderId
     * @return
     */
    @ApiOperation("手动二推信息入库")
    @RequestMapping(value = "/job/pushPhaseTwoData", method = RequestMethod.GET)
    public Response pushPhaseTwoData(@RequestParam("jdqOrderId") String jdqOrderId) {

        if (StringUtils.isEmpty(jdqOrderId)) {
            return ResponseCode.VALIDATOR_ERROR.toResponse();
        }

        // 防止重复请求
        String pushPhaseTwoRedisKey = RedisKeyCoreConstant.JDQ_JOB.PUSH_PHASE_TWO_DATA + jdqOrderId;
        if (!batchRedisTemplate.setNX(pushPhaseTwoRedisKey, jdqOrderId, 30, TimeUnit.SECONDS)) {
            log.error("重复请求 redisKey:{}", pushPhaseTwoRedisKey);
            return ResponseCode.REPEAT_REQUEST.toResponse();
        }

        // 根据逗号分割订单号
        String[] arrJdqOrderId = new String[]{jdqOrderId};
        if (jdqOrderId.contains(",")) {
            arrJdqOrderId = jdqOrderId.split(",");
        }

        for (int i = 0; i < arrJdqOrderId.length; i++) {
            PushPhaseTwoEvent pushPhaseTwoEvent = new PushPhaseTwoEvent();
            pushPhaseTwoEvent.setJdqOrderId(arrJdqOrderId[i]);
            asyncCommonService.pushPhaseTwoDataAsync(pushPhaseTwoEvent);
        }

        return ResponseCode.OK.toResponse();
    }

    /**
     * 上传通讯录
     *
     * @param jdqOrderId
     * @param customerId
     * @return
     */
    @ApiOperation("上传通讯录")
    @RequestMapping(value = "/job/pushUserAddressBooker", method = RequestMethod.GET)
    public Response pushUserAddressBooker(@RequestParam("jdqOrderId") String jdqOrderId, @RequestParam("customerId") String customerId) {

        if (StringUtils.isEmpty(jdqOrderId)) {
            return ResponseCode.OK.toResponse("jdqOrderId不能为空");
        }

        if (StringUtils.isEmpty(customerId)) {
            return ResponseCode.OK.toResponse("customerId不能为空");
        }

        String[] arrJdqOrderId = new String[]{jdqOrderId};
        if (jdqOrderId.contains(",")) {
            arrJdqOrderId = jdqOrderId.split(",");
        }

        String[] arrCustomerId = new String[]{customerId};
        if (customerId.contains(",")) {
            arrCustomerId = customerId.split(",");
        }

        if (arrJdqOrderId.length != arrCustomerId.length) {
            return ResponseCode.OK.toResponse("jdqOrderId和customerId长度不同");
        }

        for (int i = 0; i < arrJdqOrderId.length; i++) {

            log.info("上传开始 {}", arrJdqOrderId[i]);
            UserInfo userInfo = userInfoService.selectByJdqOrderId(arrJdqOrderId[i]);
            if (userInfo == null) {
                return ResponseCode.OK.toResponse("不存在此用户信息");
            }

            RiskAddressBookDto riskAddressBookDto = new RiskAddressBookDto();
            List<UserAddressBook> userAddressBookList = userAddressBookMapper.selectByUserInfoId(userInfo.getUserInfoId());
            if (userAddressBookList == null || userAddressBookList.size() == 0) {
                return null;
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
            riskAddressBookDto.setAppName(jdqCommonProperties.getRiskAppName());
            // 客户ID
            riskAddressBookDto.setCustomerId(arrCustomerId[i]);

            AddressBookDto addressBookDto = new AddressBookDto();
            BeanUtils.copyProperties(riskAddressBookDto, addressBookDto);
            RestResult<String> restResult = uploadCloudHystrixService.uploadAddressBook(addressBookDto);
            if (restResult == null || !restResult.isSuccess()) {
                log.error("上传通讯录失败 {}", restResult);
            }
            log.info("通讯录id{}", restResult.getData().getBody());
        }
        return ResponseCode.OK.toResponse();
    }

    /**
     * 根据身份证删除相关redisKey
     *
     * @param idNumber
     * @return
     */
    @ApiOperation("根据身份证删除相关redisKey")
    @RequestMapping(value = "/job/deleteRedisByIdNumber", method = RequestMethod.GET)
    public Response deleteRedisByIdNumber(@RequestParam("idNumber") String idNumber) {

        if (StringUtils.isEmpty(idNumber)) {
            return ResponseCode.OK.toResponse("参数错误");
        }

        if (!jdqCreateOrderPushEventService.deleteOrderFinishRedisKeyByIdNumber(idNumber)) {
            return ResponseCode.FAILED.toResponse("删除redisKey失败");
        }
        return ResponseCode.OK.toResponse();
    }


    /**
     * 推送到审核失败状态
     *
     * @return
     */
    @ApiOperation("推送到审核失败状态")
    @RequestMapping(value = "/job/pushRiskFail", method = RequestMethod.GET)
    public Response pushRiskFail(@RequestParam("jdqOrderId") String jdqOrderId,@RequestParam("msg") String msg) {

        if (StringUtils.isEmpty(jdqOrderId)) {
            return ResponseCode.OK.toResponse("参数错误");
        }

        UserInfo userInfo = userInfoMapper.selectByJdqOrderId(jdqOrderId);
        if (userInfo == null) {
            return ResponseCode.OK.toResponse("不存在此订单");
        }

        // 二推数据未入库，不允许发送给风控
        if (userInfo.getPushPhaseState().intValue() != 5) {
            log.error("二推数据未入库 {}", userInfo);
            return ResponseCode.OK.toResponse("订单状态不对");
        }

        // 运营商数据未推送成功，不允许发送给风控
        if (userInfo.getPushOperatorState().intValue() != 1 && userInfo.getPushOperatorState().intValue() != 4) {
            log.error("运营商数据未审核成功,不允许发送给风控 {}", userInfo);
            return ResponseCode.OK.toResponse("订单状态不对");
        }

        // 非初始化，不允许发送给风控
        if (userInfo.getPushRiskState().intValue() != 1 && userInfo.getPushRiskState().intValue() != 4) {
            log.error("已经推送给风控系统,不允许发送给风控 {}", userInfo);
            return ResponseCode.OK.toResponse("订单状态不对");
        }

        // 推送审核失败
        if(userInfoService.auditFailed(userInfo, msg)) {
            log.info("推送审核失败事件，修改本地订单为审核失败 jdqOrderId:{}", userInfo.getJdqOrderId());
            userInfo.setPushRiskState(2);
            userInfo.setPushOperatorState(2);
            userInfo.setModifyTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);
        } else {
            // 修改为初始化中，等待下次审核
            log.error("推送审核失败事件失败，修改本地订单为初始化进行下一次继续风控审核 jdqOrderId:{}", userInfo.getJdqOrderId());
            userInfo.setPushRiskState(0);
            userInfo.setPushOperatorState(0);
            userInfo.setModifyTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);
        }

        return ResponseCode.OK.toResponse();
    }


    /**
     * 清空借点钱访问白名单ip
     *
     * @return
     */
    @ApiOperation("清空借点钱访问白名单ip")
    @RequestMapping(value = "/job/clearBlackListIp", method = RequestMethod.GET)
    public Response clearBlackListIp() {
        IpBlackListUtil.getInstance().setIp(null);
        batchRedisTemplate.delete(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST);
        return ResponseCode.OK.toResponse();
    }

    /**
     * 添加借点钱访问白名单ip
     *
     * @param ip
     * @return
     */
    @ApiOperation("添加借点钱访问白名单ip")
    @RequestMapping(value = "/job/addBlackListIp", method = RequestMethod.GET)
    public Response addBlackListIp(@RequestParam("ip") String ip) {

        if (StringUtils.isEmpty(ip)) {
            return ResponseCode.OK.toResponse("参数错误");
        }

        if (ip.equals("**")) {
            IpBlackListUtil.getInstance().setIp("**");
            batchRedisTemplate.set(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST, "**");
            return ResponseCode.OK.toResponse();
        }

        String currentIp = batchRedisTemplate.get(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST) == null ? "" : batchRedisTemplate.get(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST);
        if (currentIp.contains("," + ip + ",")) {
            return ResponseCode.OK.toResponse(currentIp);
        }

        currentIp = currentIp + (currentIp.endsWith(",") ? "" : ",") + ip + ",";
        IpBlackListUtil.getInstance().setIp(currentIp);
        batchRedisTemplate.set(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST, currentIp);

        log.info("ip白名单过期时间: {}", batchRedisTemplate.ttl(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST).toString());
        return ResponseCode.OK.toResponse(currentIp);
    }

}
