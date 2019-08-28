package com.shangyong.thorder.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thcore.event.ActualLoanEvent;
import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.BankBindEvent;
import com.shangyong.thcore.event.CancelEvent;
import com.shangyong.thcore.event.OverdueEvent;
import com.shangyong.thcore.event.PreCreditExpireEvent;
import com.shangyong.thcore.event.PreSignEvent;
import com.shangyong.thcore.event.RepaymentEvent;
import com.shangyong.thcore.event.SignEvent;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thorder.service.OrderEventService;

/**
 * 订单监听模块
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
@Component
public class ThorderListener {

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger logger = LoggerFactory.getLogger(ThorderListener.class);

	@Autowired
	private OrderEventService orderEventService;

	/**
	 * 审核事件
	 * 
	 * @param auditEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.audit.new.process" })
	public void processAuditEvent(AuditEvent auditEvent, Message message, Channel channel) {
		logger.info("收到审核事件，开始处理：{}", auditEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = auditEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return orderEventService.processAuditSuccess(eventHeader, auditEvent.getEventUserInfo());
				} else {
					return orderEventService.processAuditFailure(eventHeader, auditEvent.getEventFailureResult());
				}
			}, message, channel, false);
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
	@RabbitListener(queues = { "queue.event.bankBind.process" })
	public void processBankBindEvent(BankBindEvent bankBindEvent, Message message, Channel channel) {
		logger.info("收到银行卡绑定事件，开始处理：{}", bankBindEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = bankBindEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return orderEventService.processBankBindSuccess(eventHeader, bankBindEvent.getEventBankBind());
				} else {
					return orderEventService.processBankBindFailure(eventHeader, bankBindEvent.getEventFailureResult());
				}
			}, message, channel, false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 前置签约事件
	 * 
	 * @param signEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.preSign.process" })
	public void processPreSignEvent(PreSignEvent preSignEvent, Message message, Channel channel) {
		logger.info("收到前置签约事件，开始处理：{}", preSignEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = preSignEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return orderEventService.processPreSignSuccess(eventHeader, preSignEvent.getEventPreSign());
				} else {
					return orderEventService.processPreSignFailure(eventHeader, preSignEvent.getEventFailureResult());
				}

			}, message, channel, false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 处理授信订单24小时过期事件
	 * 
	 * @param contractExpire
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.preSign.expire" })
	public void processPreCreditExpireEvent(PreCreditExpireEvent preCreditExpireEvent, Message message,
			Channel channel) {
		logger.info("收到前置授信过期事件，开始处理：{}", preCreditExpireEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = preCreditExpireEvent.getEventHeader();
				return orderEventService.processPreCreditExpireEvent(eventHeader);
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 后置授信
	 * 
	 * @param signEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.sign.process" })
	public void processSignEvent(SignEvent signEvent, Message message, Channel channel) {
		logger.info("收到后置授信事件，开始处理：{}", signEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = signEvent.getEventHeader();

				if (eventHeader.isSuccess()) {
					return orderEventService.processSignSuccess(eventHeader, signEvent.getEventSign());
				} else {
					return orderEventService.processSignFailure(eventHeader, signEvent.getEventFailureResult());
				}

			}, message, channel, false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	/**
	 * 放款事件
	 * 
	 * @param actualLoanEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = { "queue.event.actualLoan.process" })
	public void processActualLoanEvent(ActualLoanEvent actualLoanEvent, Message message, Channel channel) {
		logger.info("收到放款事件，开始处理：{}", actualLoanEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = actualLoanEvent.getEventHeader();

				if (eventHeader.isSuccess()) {
					return orderEventService.processActualLoanSuccess(eventHeader,
							actualLoanEvent.getEventActualLoan());
				} else {
					return orderEventService.processActualLoanFailure(eventHeader,
							actualLoanEvent.getEventFailureResult());
				}
			}, message, channel, false);
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
	@RabbitListener(queues = { "queue.event.repayment.process" })
	public void processRepaymentEvent(RepaymentEvent repaymentEvent, Message message, Channel channel) {
		logger.info("收到还款事件，开始处理：{}", repaymentEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = repaymentEvent.getEventHeader();

				if (eventHeader.isSuccess()) {
					return orderEventService.processRepaymentSuccess(eventHeader, repaymentEvent.getEventRepayment());
				} else {
					return orderEventService.processRepaymentFailure(eventHeader,
							repaymentEvent.getEventFailureResult());
				}

			}, message, channel, false);
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
	@RabbitListener(queues = { "queue.event.overdue.process" })
	public void processOverdueEvent(OverdueEvent overdueEvent, Message message, Channel channel) {
		logger.info("收到逾期事件，开始处理：{}", overdueEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = overdueEvent.getEventHeader();
				return orderEventService.processOverdue(eventHeader.getAppid(), eventHeader.getOrderId());
			}, message, channel, false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	
	@RabbitListener(queues = { "queue.event.cancel.process" })
	public void processCancelEvent(CancelEvent cancelEvent, Message message, Channel channel) {
		logger.info("收到订单取消事件，开始处理：{}", cancelEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = cancelEvent.getEventHeader();
				return orderEventService.processCancel(eventHeader.getAppid(), eventHeader.getOrderId(), eventHeader.getRemark());
			}, message, channel, false);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	
}
