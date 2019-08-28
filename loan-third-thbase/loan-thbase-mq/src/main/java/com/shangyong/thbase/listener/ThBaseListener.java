package com.shangyong.thbase.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thbase.service.BaseUserService;
import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 订单监听模块
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
@Component
public class ThBaseListener {

	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger logger = LoggerFactory.getLogger(ThBaseListener.class);

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	@Autowired
	private BaseUserService baseUserService;

	@RabbitListener(queues = { "queue.base.audit.process" })
	public void processAuditEvent(AuditEvent auditEvent, Message message, Channel channel) {
		logger.info("收到审核事件，开始处理：{}", auditEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = auditEvent.getEventHeader();
				if(eventHeader.isSuccess()) {
					return baseUserService.processAuditEvent(auditEvent.getEventHeader(),auditEvent.getEventUserInfo());
				}else {
					// 处理隔离用户处理
					return baseUserService.processFailureAuditEvent(auditEvent.getEventHeader(), auditEvent.getEventUserInfo(), auditEvent.getEventFailureResult());
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

}
