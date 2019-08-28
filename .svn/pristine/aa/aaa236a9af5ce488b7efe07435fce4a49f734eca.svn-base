package com.shangyong.thjdq.service.impl;

import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thjdq.config.properties.AliyunOssProperties;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.UuidPrefix;
import com.shangyong.thjdq.dao.UserInfoMapper;
import com.shangyong.thjdq.dto.AuditResultNotifyDto;
import com.shangyong.thjdq.dto.AuditResultTDCarrierDto;
import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.enums.ActiveProfileEnum;
import com.shangyong.thjdq.enums.RabbitMqEnum;
import com.shangyong.thjdq.event.PushPhaseOtherInfoEvent;
import com.shangyong.thjdq.mq.MqSender;
import com.shangyong.thjdq.service.AuditCallbackService;
import com.shangyong.thjdq.service.UserInfoService;
import com.shangyong.thjdq.util.IdUtil;
import com.shangyong.thjdq.util.SpringContextUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by ybds on 2019-03-21.
 */
@Service
public class AuditCallbackServiceImpl implements AuditCallbackService {

    private static final Logger log = LoggerFactory.getLogger(RiskJdqServiceImpl.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private MqSender mqSender;
    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void riskAuditResultCallback(AuditResultNotifyDto auditResultNotifyDto) {

        UserInfo userInfo = userInfoService.selectLockByAppSerialNumber(auditResultNotifyDto.getSerialNumber());

        if (userInfo == null) {
            log.error("不存在此客户 userInfoId:{}", auditResultNotifyDto.getSerialNumber());
            return;
        }

        // 已经审核回调，不做处理
        if (userInfo.getPushRiskState().intValue() == 2 || userInfo.getPushRiskState().intValue() == 3) {
            log.info("审核回调已经接收 userInfoId:{},pushRiskState:{}", auditResultNotifyDto.getCustomerId(), userInfo.getPushRiskState());
            return;
        }

        RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(auditResultNotifyDto.getAppid(), userInfo.getJdqOrderId());
        if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess()) {
            log.error("获取订单失败 appid:{} jdqOrderId:{}",auditResultNotifyDto.getAppid(), userInfo.getJdqOrderId());
            throw new RuntimeException("获取订单信息失败 appid:" + auditResultNotifyDto.getAppid() + ",otherOrderId:" + userInfo.getJdqOrderId());
        }

        OrderLoanVo orderLoanVo = orderLoanVoRestResult.getData().getBody();

        log.info("订单{}",orderLoanVo);
        // 简单校验下订单状态 审核失败以上的状态无需操作

        if (orderLoanVo.getStatus() >= 30) {
            userInfo.setPushRiskState(orderLoanVo.getStatus()==30?2:3);
            userInfo.setPushOperatorState(orderLoanVo.getStatus()==30?2:3);
            userInfo.setModifyTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);
        } else {
            // 审核成功
            if ("1".equals(auditResultNotifyDto.getIsPass())) {
                // 推送审核成功
                if(userInfoService.auditSuccess(userInfo)) {
                    log.info("推送审核成功事件成功，修改本地订单 jdqOrderId:{}", userInfo.getJdqOrderId());
                    userInfo.setPushRiskState(3);
                    userInfo.setPushOperatorState(3);
                    userInfo.setModifyTime(new Date());
                    userInfoMapper.updateByPrimaryKey(userInfo);
                } else {
                    // 修改为初始化中，等待下次审核
                    log.error("推送审核成功事件失败，修改本地订单为初始化进行下一次继续风控审核 jdqOrderId:{}", userInfo.getJdqOrderId());
                    userInfo.setPushRiskState(0);
                    userInfo.setPushOperatorState(0);
                    userInfo.setModifyTime(new Date());
                    userInfoMapper.updateByPrimaryKey(userInfo);
                }
            } else {
                // 推送审核失败
                if(userInfoService.auditFailed(userInfo, "风控审核失败，流水单号：" +auditResultNotifyDto.getSerialNumber())) {
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
            }
        }

        if (ActiveProfileEnum.PRODUCT.name().equalsIgnoreCase(SpringContextUtils.getActiveProfile())) {
            // 校验是否需要上传身份证相关图片到阿里云
            String bucketName = aliyunOssProperties.getBucketName();
            if ((StringUtils.isNotEmpty(userInfo.getIdPositive()) && !userInfo.getIdPositive().contains(bucketName)) || (StringUtils.isNotEmpty(userInfo.getIdNegative()) && !userInfo.getIdNegative().contains(bucketName)) || (StringUtils.isNotEmpty(userInfo.getFace()) && !userInfo.getFace().contains(bucketName)) || (StringUtils.isNotEmpty(userInfo.getHandIdPhoto()) && !userInfo.getHandIdPhoto().contains(bucketName))) {
                // 调用用户其他相关信息生产者
                PushPhaseOtherInfoEvent pushPhasePicEvent = new PushPhaseOtherInfoEvent();
                pushPhasePicEvent.setJdqOrderId(userInfo.getJdqOrderId());
                mqSender.sendMq(RabbitMqEnum.PUSH_PHASE_OTHER, pushPhasePicEvent ,true);
            }
        }

        return;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void operatorCollectCallback(AuditResultTDCarrierDto auditResultTDCarrierDto) {

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(auditResultTDCarrierDto.getCustomerId());
        if (userInfo == null) {
            log.error("不存在此客户 userInfoId:{}", auditResultTDCarrierDto.getCustomerId());
            return;
        }

        RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(auditResultTDCarrierDto.getAppid(), userInfo.getJdqOrderId());
        if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess()) {
            log.error("获取订单失败 jdqOrderId:{}",userInfo.getJdqOrderId());
            return;
        }

        OrderLoanVo orderLoanVo = orderLoanVoRestResult.getData().getBody();
        log.info("订单{}",orderLoanVo);

        // 审核失败/订单取消
        if (orderLoanVo.getStatus() == 30 || orderLoanVo.getStatus() == 1000) {
            log.info("订单状态为【审核失败或者订单取消】，无需修改订单 jdqOrderId:{}", userInfo.getJdqOrderId());
            // 修改未审核失败
            userInfo.setPushOperatorState(2);
            userInfo.setModifyTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);
            return;
        }

        // 审核成功
        if ("CCOM1000".equals(auditResultTDCarrierDto.getRiskStepNo())) {
            userInfo.setPushOperatorState(3);
            userInfo.setModifyTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);
            return;
        } else {
            userInfo.setPushOperatorState(2);
            userInfo.setModifyTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);
            return;
        }

    }


}
