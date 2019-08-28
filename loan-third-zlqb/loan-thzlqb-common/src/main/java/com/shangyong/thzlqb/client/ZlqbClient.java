package com.shangyong.thzlqb.client;

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
import com.shangyong.loan.annotation.ComponentLogger;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.thzlqb.enums.ZlqbResultVoid;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

@ComponentLogger
@Component
public class ZlqbClient {

	private Logger logger = LoggerFactory.getLogger(ZlqbClient.class);

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 推送订单状态对象
	 * 
	 * @param request
	 * 
	 * @return
	 */
	public boolean pushObjectData(ObjectNode request) {
		ObjectNode objectRequest = ZlqbUtil.buildRequest(request);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		HttpEntity<ObjectNode> httpEntity = new HttpEntity<>(objectRequest, httpHeaders);
		String url = ZlqbUtil.getCallbackUrl();
		logger.info("url:{},body:{}", url, request);

		ResponseEntity<ZlqbResultVoid> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				ZlqbResultVoid.class);
		logger.info("result:{}", result);
		if (result.getStatusCode() == HttpStatus.OK && result.getBody().isSuccess()) {
			return true;
		} else {
			throw new CalfException("推送至第三方订单状态对象失败!!!");
		}
	}



	public boolean cancelBankInfo(ObjectNode node) {
		ObjectNode objectRequest = ZlqbUtil.buildRequest(node);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		HttpEntity<ObjectNode> httpEntity = new HttpEntity<>(objectRequest, httpHeaders);
		//取消绑卡接口
		String url = ZlqbUtil.getCancelBankUrl();

		ResponseEntity<Void> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				Void.class);
		logger.info("result:{}", result);

		if (result.getStatusCode() == HttpStatus.OK) {
			return true;
		} else {
			throw new CalfException("推送至第三方取消绑卡信息失败!!!");
		}
	}
}
