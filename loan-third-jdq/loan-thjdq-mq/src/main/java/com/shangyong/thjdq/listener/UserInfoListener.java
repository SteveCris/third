package com.shangyong.thjdq.listener;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseOtherInfoEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;
import com.shangyong.thjdq.event.PushRiskEvent;
import com.shangyong.thjdq.service.RiskJdqService;
import com.shangyong.thjdq.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户监听模块
 *
 * @author zbb
 * @date 2019年3月14日
 */
@Component
public class UserInfoListener {

    @Autowired
    private RabbitMQProcess rabbitMQProcess;

    private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

    private Logger log = LoggerFactory.getLogger(UserInfoListener.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RiskJdqService riskJdqService;

    /**
     * 用户一推基本信息入库
     *
     * @param pushPhaseOneEvent
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {"queue.event.jdq.one.push"}, containerFactory = "rabbitTaskContainerFactory")
    public void processPushPhaseOneEvent(PushPhaseOneEvent pushPhaseOneEvent, Message message, Channel channel) {
        log.info("收到一推基本信息入库事件，开始处理：{}", pushPhaseOneEvent);
        try {
            rabbitMQProcess.execute(retryTime -> {
                return userInfoService.pushPhaseOneDataAsync(pushPhaseOneEvent);
            }, message, channel, false);
        } catch (Exception e) {
            log.error("一推基本信息入库事件 异常 {} {}", pushPhaseOneEvent, e);
            throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
        }

    }

    /**
     * 用户二推基本信息入库
     *
     * @param pushPhaseTwoEvent
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {"queue.event.jdq.two.push"}, containerFactory = "rabbitTask2ContainerFactory")
    public void processPushPhaseTwoEvent(PushPhaseTwoEvent pushPhaseTwoEvent, Message message, Channel channel) {
        log.info("收到二推补充信息入库事件，开始处理：{}", pushPhaseTwoEvent);
        try {
            rabbitMQProcess.execute(retryTime -> {
                return userInfoService.pushPhaseTwoDataAsync(pushPhaseTwoEvent);
            }, message, channel, false);
        } catch (Exception e) {
            log.error("二推补充信息入库事件 异常 {} {}", pushPhaseTwoEvent, e);
            throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
        }

    }

    /**
     * 用户补充信息入库
     *
     * @param pushPhaseOtherInfoEvent
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {"queue.event.jdq.other.push"})
    public void processPushOtherEvent(PushPhaseOtherInfoEvent pushPhaseOtherInfoEvent, Message message, Channel channel) {
        log.info("收到用户补充信息入库事件，开始处理：{}", pushPhaseOtherInfoEvent);
        try {
            rabbitMQProcess.execute(retryTime -> {
                return userInfoService.pushPhaseOtherInfoAsync(pushPhaseOtherInfoEvent);
            }, message, channel, true);
        } catch (Exception e) {
            log.error("用户补充信息入库事件 异常 {} {}", pushPhaseOtherInfoEvent, e);
            throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
        }

    }

    /**
     * 风控推送
     * @param pushRiskEvent
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {"queue.event.jdq.risk.push"})
    public void processPushRiskEvent(PushRiskEvent pushRiskEvent, Message message, Channel channel) {
        log.info("收到风控推送事件，开始处理：{}", pushRiskEvent);
        try {
            rabbitMQProcess.execute(retryTime -> {
                return riskJdqService.jdqPushUserInfoToRiskEvent(pushRiskEvent);
            }, message, channel, true);
        } catch (Exception e) {
            log.error("风控推送事件 异常 {} {}", pushRiskEvent, e);
            throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
        }

    }

}
