package com.shangyong.thzlqb.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thzlqb.event.PushEvent;
import com.shangyong.thzlqb.listener.service.SelfConsumerService;

@Component
public class ThSelfListener {
	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger logger = LoggerFactory.getLogger(ThSelfListener.class);

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	@Autowired
	private SelfConsumerService selfConsumerService;
	
	@RabbitListener(queues = { "queue.zlqb.push.time" })
	public void processPushTimeEvent(PushEvent pushEvent, Message message, Channel channel) {
		logger.info("1、资源计时处理事件，开始处理：{}", pushEvent);
		try {
			rabbitMQProcess.execute(retryTime -> selfConsumerService.processPushTime(pushEvent), message, channel,
					false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.push.fall" })
	public void processPushFallEvent(PushEvent pushEvent, Message message, Channel channel) {
		logger.info("2、基本数据落库事件，开始处理：{}", pushEvent);
		try {
			rabbitMQProcess.execute(retryTime -> selfConsumerService.processPushFall(pushEvent), message, channel,
					false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.push.audit" })
	public void processPushAuditEvent(PushEvent pushEvent, Message message, Channel channel) {
		logger.info("3、推送审核事件，开始处理：{}", pushEvent);
		try {
			rabbitMQProcess.execute(retryTime -> selfConsumerService.processPushAudit(pushEvent), message, channel,
					false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}
	
	
	
	

}
