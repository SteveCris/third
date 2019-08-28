package com.shangyong.thjdq.listener;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thcore.event.*;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thjdq.service.JdqCreateOrderPushEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 借点钱订单状态推送监听模块
 * @author zbb
 * @date 2019年3月14日
 */
@Component
public class OrderChangeListener {

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger logger = LoggerFactory.getLogger(OrderChangeListener.class);

	@Autowired
	private JdqCreateOrderPushEventService jdqCreateOrderPushEventService;


	/**
	 * 审核事件
	 *
	 * @param auditEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.audit.process" })
	public void processAuditEvent(AuditEvent auditEvent, Message message, Channel channel) {
		logger.info("收到审核事件，开始处理：{}", auditEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, auditEvent.getEventHeader().getOrderId());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 绑定银行卡事件
	 *
	 * @param bankBindEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.bankBind.process" })
	public void processBankBindEvent(BankBindEvent bankBindEvent, Message message, Channel channel) {
		logger.info("收到银行卡绑定事件，开始处理：{}", bankBindEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, bankBindEvent.getEventHeader().getOrderId());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 后置授信事件
	 *
	 * @param signEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.sign.process" })
	public void processSignEvent(SignEvent signEvent, Message message, Channel channel) {
		logger.info("收到后置授信事件，开始处理：{}", signEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, signEvent.getEventHeader().getOrderId());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 借款事件（放款事件）
	 *
	 * @param actualLoanEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.actualLoan.process" })
	public void processBorrowEvent(ActualLoanEvent actualLoanEvent, Message message, Channel channel) {
		logger.info("收到放款事件，开始处理：{}", actualLoanEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, actualLoanEvent.getEventHeader().getOrderId());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 还款事件
	 *
	 * @param repaymentEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.repayment.process" })
	public void processRepaymentEvent(RepaymentEvent repaymentEvent, Message message, Channel channel) {
		logger.info("收到还款事件，开始处理：{}", repaymentEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, repaymentEvent.getEventHeader().getOrderId(), repaymentEvent.getEventHeader().isSuccess());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 逾期事件
	 *
	 * @param overdueEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.overdue.process" })
	public void processOverdueEvent(OverdueEvent overdueEvent, Message message, Channel channel) {
		logger.info("收到逾期事件，开始处理：{}", overdueEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, overdueEvent.getEventHeader().getOrderId());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 取消订单事件
	 * @param cancelEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.jdq.cancel.process" })
	public void processCancelEvent(CancelEvent cancelEvent, Message message, Channel channel) {
		logger.info("收到订单取消事件，开始处理：{}", cancelEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqCreateOrderPushEventService.createOrderPushEvent(null, null, cancelEvent.getEventHeader().getOrderId(), true);
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

}
