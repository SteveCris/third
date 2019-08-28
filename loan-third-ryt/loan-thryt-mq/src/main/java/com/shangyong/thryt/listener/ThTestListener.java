package com.shangyong.thryt.listener;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.shangyong.loan.autoconfigure.RabbitMQProcess;
import com.shangyong.thryt.event.RytPushEvent;

@Component
public class ThTestListener {

	@Autowired
	private RabbitMQProcess rabbitMQProcess;

	@RabbitListener(queues = { "queue.test" })
	public void processRytPushTimeEvent(RytPushEvent rytPushEvent, Message message, Channel channel) {

		try {
			rabbitMQProcess.execute(retryTime -> {
				return false;
			}, message, channel, true);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException("不可测异常", e);
		}
	}

}
