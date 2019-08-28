package com.shangyong.thryt.service.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.thryt.bo.ThirdRytResultBo;
import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.exception.CalfException;
import com.shangyong.thryt.ryt.utils.SignUtil;
import com.shangyong.thryt.service.client.RytClient;
import com.shangyong.thryt.utils.RytUtil;

@Service
public class RytClientImpl implements RytClient {

	private Logger logger = LoggerFactory.getLogger(RytClientImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean pushAuditResult(ObjectNode data) {
		return pushObjectData("approvalResult", data);
	}

	@Override
	public boolean pushBankBindResult(ObjectNode data) {
		return pushObjectData("bindCardDataResult", data);
	}

	@Override
	public boolean pushOrderStatusResult(ObjectNode data) {
		return pushObjectData("orderStatusResult", data);
	}

	private boolean pushObjectData(String channel, ObjectNode data) {
		ObjectNode objectRequest = SignUtil.buildSignRequest(data, channel);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		HttpEntity<ObjectNode> httpEntity = new HttpEntity<>(objectRequest, httpHeaders);
		String url = RytUtil.getRytCallbackUrl();
		logger.info("url:{},body:{}", url, data);

		ResponseEntity<ThirdRytResultBo> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				ThirdRytResultBo.class);
		logger.info("result:{}", result);
		if (result.getStatusCode() == HttpStatus.OK && result.getBody().isSuccess()) {
			return true;
		} else {
			throw new CalfException(RytResultEnum.THIRD_REMOTE_ERROR);
		}
	}

}
