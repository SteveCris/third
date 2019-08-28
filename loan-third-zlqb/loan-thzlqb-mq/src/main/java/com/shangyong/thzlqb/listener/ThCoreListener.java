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
import com.shangyong.thcore.event.ActualLoanEvent;
import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.BankBindEvent;
import com.shangyong.thcore.event.CancelEvent;
import com.shangyong.thcore.event.OverdueEvent;
import com.shangyong.thcore.event.RepaymentEvent;
import com.shangyong.thcore.event.SignEvent;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thzlqb.listener.service.CoreConsumerService;

@Component
public class ThCoreListener {
	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger logger = LoggerFactory.getLogger(ThSelfListener.class);

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	@Autowired
	private CoreConsumerService coreConsumerService;

	@RabbitListener(queues = { "queue.zlqb.audit.process" })
	public void processAuditEvent(AuditEvent auditEvent, Message message, Channel channel) {
		logger.info("收到审核事件，开始处理：{}", auditEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = auditEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return coreConsumerService.processAuditSuccess(eventHeader, auditEvent.getEventUserInfo());
				} else {
					return coreConsumerService.processAuditFailure(eventHeader, auditEvent.getEventFailureResult());
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.bankBind.process" })
	public void processBankBindEvent(BankBindEvent bankBindEvent, Message message, Channel channel) {
		logger.info("收到银行卡绑定事件，开始处理：{}", bankBindEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = bankBindEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return coreConsumerService.processBankBindSuccess(eventHeader, bankBindEvent.getEventBankBind());
				} else {
					// 绑卡失败不处理
					return true;
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.sign.process" })
	public void processSignEvent(SignEvent signEvent, Message message, Channel channel) {
		logger.info("收到后置授信事件，开始处理：{}", signEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = signEvent.getEventHeader();

				if (eventHeader.isSuccess()) {
					return coreConsumerService.processSignSuccess(eventHeader, signEvent.getEventSign());
				} else {
					return coreConsumerService.processSignFailure(eventHeader, signEvent.getEventFailureResult());
				}

			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.actualLoan.process" })
	public void processActualLoanEvent(ActualLoanEvent actualLoanEvent, Message message, Channel channel) {
		logger.info("收到放款事件，开始处理：{}", actualLoanEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = actualLoanEvent.getEventHeader();

				if (eventHeader.isSuccess()) {
					return coreConsumerService.processActualLoanSuccess(eventHeader,
							actualLoanEvent.getEventActualLoan());
				} else {
					return coreConsumerService.processActualLoanFailure(eventHeader,
							actualLoanEvent.getEventFailureResult());
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.repayment.process" })
	public void processRepaymentEvent(RepaymentEvent repaymentEvent, Message message, Channel channel) {
		logger.info("收到还款事件，开始处理：{}", repaymentEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = repaymentEvent.getEventHeader();

				if (eventHeader.isSuccess()) {
					return coreConsumerService.processRepaymentSuccess(eventHeader, repaymentEvent.getEventRepayment());
				} else {
					return coreConsumerService.processRepaymentFailure(eventHeader,
							repaymentEvent.getEventFailureResult());
				}

			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.overdue.process" })
	public void processOverdueEvent(OverdueEvent overdueEvent, Message message, Channel channel) {
		logger.info("收到逾期事件，开始处理：{}", overdueEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = overdueEvent.getEventHeader();
				return coreConsumerService.processOverdue(eventHeader.getAppid(), eventHeader.getOrderId());
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}

	@RabbitListener(queues = { "queue.zlqb.cancel.process" })
	public void processCancelEvent(CancelEvent cancelEvent, Message message, Channel channel) {
		logger.info("收到订单取消事件，开始处理：{}", cancelEvent);
		try {
			rabbitMQProcess.execute(retryTime -> {
				EventHeader eventHeader = cancelEvent.getEventHeader();
				if (eventHeader.isSuccess()) {
					return coreConsumerService.processCancel(eventHeader.getAppid(), eventHeader.getOrderId(),
							eventHeader.getRemark());
				} else {
					// 绑卡失败不处理
					return true;
				}
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}
	}
}
