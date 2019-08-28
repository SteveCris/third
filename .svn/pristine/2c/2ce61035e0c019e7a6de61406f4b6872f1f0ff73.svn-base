package com.shangyong.thryt.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.BankBindEvent;
import com.shangyong.thcore.event.CancelEvent;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thryt.event.RytPushEvent;
import com.shangyong.thryt.service.CallBackService;
import com.shangyong.thryt.service.RytConsumerService;

@Component
public class ThrytListener {
	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger logger = LoggerFactory.getLogger(ThrytListener.class);

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	@Autowired
	private RytConsumerService rytConsumerService;
	
	@Autowired
	private CallBackService callBackService;

	@RabbitListener(queues = { "queue.ryt.push.time" })
	public void processRytPushTimeEvent(RytPushEvent rytPushEvent, Message message, Channel channel) {
		logger.info("1、资源计时处理事件，开始处理：{}", rytPushEvent);
		try {
			rabbitMQProcess.execute(retryTime -> rytConsumerService.processRytPushTime(rytPushEvent), message, channel,
					false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.ryt.push.fall" })
	public void processRytPushFallEvent(RytPushEvent rytPushEvent, Message message, Channel channel) {
		logger.info("2、用户基本数据落库事件，开始处理：{}", rytPushEvent);
		try {
			rabbitMQProcess.execute(retryTime -> rytConsumerService.processRytPushFall(rytPushEvent), message, channel,
					false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.ryt.push.audit" })
	public void processRytPushAuditEvent(RytPushEvent rytPushEvent, Message message, Channel channel) {
		logger.info("3、推送审核事件，开始处理：{}", rytPushEvent);
		try {
			rabbitMQProcess.execute(retryTime -> rytConsumerService.processRytPushAudit(rytPushEvent), message, channel,
					false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}
	
	
	@RabbitListener(queues = { "queue.ryt.audit.process" })
	public void processAuditEvent(AuditEvent auditEvent, Message message, Channel channel) {
		logger.info("收到审核事件，开始处理：{}", auditEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = auditEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return callBackService.processAuditSuccess(eventHeader, auditEvent.getEventUserInfo());
				} else {
					return callBackService.processAuditFailure(eventHeader, auditEvent.getEventFailureResult());
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}
	
	@RabbitListener(queues = { "queue.ryt.bankBind.process" })
	public void processBankBindEvent(BankBindEvent bankBindEvent, Message message, Channel channel) {
		logger.info("收到银行卡绑定事件，开始处理：{}", bankBindEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = bankBindEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return callBackService.processBankBindSuccess(eventHeader, bankBindEvent.getEventBankBind());
				} else {
					//绑卡失败不处理
					return true;
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}
	
	
	@RabbitListener(queues = { "queue.ryt.cancel.process" })
	public void processCancelEvent(CancelEvent cancelEvent, Message message, Channel channel) {
		logger.info("收到订单取消事件，开始处理：{}", cancelEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = cancelEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return callBackService.processCancelOrder(eventHeader);
				} else {
					//绑卡失败不处理
					return true;
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}
	

}
