package com.shangyong.thorder.sender.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shangyong.rest.feign.MqCloudHystrixService;
import com.shangyong.rest.mq.dto.MqDto;
import com.shangyong.rest.mq.vo.MqResult;
import com.shangyong.thorder.utils.JacksonUtil;

@Component
public class Sender {

	private static Logger logger = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private MqCloudHystrixService mqCloudHystrixService;

	public boolean sendMq(String virtualHost, String exchange, String routingKey, Object body, boolean isConfirm) {
		String bodyStr = JacksonUtil.parseToJsonString(body);
		logger.info("sendMq,virtualHost:{},exchange:{},routingKey:{},body:{},isConfirm:{}", virtualHost,exchange,routingKey, bodyStr, isConfirm);
		MqDto mqDto=new MqDto();
		mqDto.setVirtualHost(virtualHost);
		mqDto.setExchange(exchange);
		mqDto.setRoutingKey(routingKey);
		mqDto.setMsg(bodyStr);
		MqResult mqResult;
		if (isConfirm) {
			mqResult = mqCloudHystrixService.sendAndConfirm(mqDto);
		} else {
			mqResult = mqCloudHystrixService.send(mqDto);
		}

		if (mqResult == null) {
			logger.error("send服务器 发生熔断了，请尽快查看!!!");
			return false;
		}

		try {
			logger.info("respone:{}", mqResult);
			return mqResult.isSuccess();
		} catch (Exception e) {
			logger.error("发送失败：virtualHost：{},exchange：{},routingKey：{},msg：{}", virtualHost, exchange, routingKey,
					bodyStr, e);
			return false;
		}
	}

}
