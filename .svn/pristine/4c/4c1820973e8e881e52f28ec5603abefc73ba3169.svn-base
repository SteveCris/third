package com.shangyong.thjdq.listener;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thjdq.event.PushJdqOrderEvent;
import com.shangyong.thjdq.service.JdqOrderPushService;
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
public class PushOrderListener {

	@Autowired
	private RabbitMQProcess rabbitMQProcess;
	@Autowired
	private JdqOrderPushService jdqOrderPushService;

	private static final String ERROR_MESSAGE = "遇到不可预测错误，直接进入死信队列";

	private Logger log = LoggerFactory.getLogger(PushOrderListener.class);

	/**
	 * 借点钱订单状态推送
	 * @param pushJdqOrderEvent
	 * @param message
	 * @param channel
	 */
	@RabbitListener(queues = {"queue.event.order.push"}, containerFactory = "rabbitTask2ContainerFactory")
	public void processPushPhaseOneEvent(PushJdqOrderEvent pushJdqOrderEvent, Message message, Channel channel) {
		log.info("收到借点钱订单状态推送事件，开始处理：{}", pushJdqOrderEvent);
		int status = pushJdqOrderEvent.getStatus();
		try {
			rabbitMQProcess.execute(retryTime -> {
				return jdqOrderPushService.pushOrderStateByStatus(pushJdqOrderEvent.getJdqOrderId());
			}, message, channel, false);
		} catch (Exception e) {
			log.error("借点钱订单状态推送事件 异常 {} {}", pushJdqOrderEvent, e);
			throw new AmqpRejectAndDontRequeueException(ERROR_MESSAGE, e);
		}

	}

}
