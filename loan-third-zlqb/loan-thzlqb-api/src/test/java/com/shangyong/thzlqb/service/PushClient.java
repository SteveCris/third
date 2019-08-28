package com.shangyong.thzlqb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.JsonNodeUtil;

@Component
public class PushClient {

	private Logger logger = LoggerFactory.getLogger(PushClient.class);

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 推送订单状态对象
	 * 
	 * @param pushData
	 * @param url
	 * 
	 * @return
	 */
	public boolean pushData(ObjectNode pushData, String url) {
		
		ObjectNode push= JsonNodeUtil.data().put("sign", "").put("bizParams", pushData.toString());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		HttpEntity<ObjectNode> httpEntity = new HttpEntity<>(push, httpHeaders);
		logger.info("url:{}", url);

//		return true;
		ResponseEntity<PushResult> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PushResult.class);
		logger.info("result:{}", result);
		if (result.getStatusCode() == HttpStatus.OK && result.getBody().isSuccess()) {
			return true;
		} else {
			throw new CalfException("推送至第三方订单状态对象失败!!!");
		}
	}
}
