package com.shangyong.thjdq.service.impl;

import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.dao.UserInfoMapper;
import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.enums.RabbitMqEnum;
import com.shangyong.thjdq.event.PushJdqOrderEvent;
import com.shangyong.thjdq.mq.MqSender;
import com.shangyong.thjdq.service.JdqCreateOrderPushEventService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ybds on 2019-03-24.
 */
@Service
public class JdqCreateOrderPushEventServiceImpl implements JdqCreateOrderPushEventService {

    private static final Logger log = LoggerFactory.getLogger(JdqCreateOrderPushEventServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private MqSender mqSenderService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private BatchRedisTemplate batchRedisTemplate;

    @Override
    public boolean createOrderPushEvent(String jdqOrderId, String userInfoId, String localOrderId) {
        return createOrderPushEvent(jdqOrderId, userInfoId, localOrderId, false);
    }

    @Override
    public boolean createOrderPushEvent(String jdqOrderId, String userInfoId, String localOrderId, boolean ifOrderFinish) {
        log.info("推送订单状态事件 {} {} {}", jdqOrderId, userInfoId, localOrderId);
        String appId = jdqCommonProperties.getRiskAppId();
        // 推送订单状态事件
        try {

            UserInfo userInfo = null;
            if (StringUtils.isEmpty(jdqOrderId)) {
                if (StringUtils.isNotEmpty(userInfoId)) {
                    userInfo = userInfoMapper.selectByPrimaryKey(userInfoId);
                    if (userInfo != null && StringUtils.isNotEmpty(userInfo.getJdqOrderId())) {
                        jdqOrderId = userInfo.getJdqOrderId();
                    }
                }
            }

            if (StringUtils.isEmpty(jdqOrderId)) {
                if (StringUtils.isNotEmpty(localOrderId)) {
                    userInfo = userInfoMapper.selectByLocalOrderId(localOrderId);
                    if (userInfo != null && StringUtils.isNotEmpty(userInfo.getJdqOrderId())) {
                        jdqOrderId = userInfo.getJdqOrderId();
                    }
                }
            }

            if (StringUtils.isEmpty(jdqOrderId)) {
                log.error("借点钱jdqOrderId不存在");
                return true;
            }

            // 订单完结后删除redisKey
            if (ifOrderFinish) {
                if (userInfo == null) {
                    userInfo = userInfoMapper.selectByJdqOrderId(jdqOrderId);
                }
                deleteOrderFinishRedisKeyByIdNumber(userInfo.getIdNumber());
            }

            PushJdqOrderEvent pushJdqOrderEvent = new PushJdqOrderEvent();
            pushJdqOrderEvent.setJdqOrderId(jdqOrderId);
            log.info("推送订单状态事件 {} ", pushJdqOrderEvent);
            return mqSenderService.sendMq(RabbitMqEnum.ORDER_PUSH, pushJdqOrderEvent, true);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("推送订单状态事件失败 appId:{} otherOrderId{} {}", appId, jdqOrderId, e);
        }

        return false;
    }

    @Override
    public boolean deleteOrderFinishRedisKeyByIdNumber(String idNumber) {

         try {
             log.info("删除redisKey:{}", RedisKeyCoreConstant.JDQ_JOB.PUSH_RISK_IN_NUMBER_REPEAT + idNumber);
             batchRedisTemplate.delete(RedisKeyCoreConstant.JDQ_JOB.PUSH_RISK_IN_NUMBER_REPEAT + idNumber);

             return true;
         } catch (Exception e) {
             e.printStackTrace();
             log.error("删除redisKey失败:{}", RedisKeyCoreConstant.JDQ_JOB.PUSH_RISK_IN_NUMBER_REPEAT + idNumber, e);
         }

         return false;

    }
}
