package com.shangyong.thorder.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.thorder.bo.EquityCardBo;
import com.shangyong.thorder.entity.OrderEquity;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.service.EquityCardService;
import com.shangyong.thorder.service.OrderEquityService;
import com.shangyong.thorder.utils.ConfigUtil;
import com.shangyong.thorder.utils.JacksonUtil;

@Service
public class EquityCardServiceImpl implements EquityCardService {

	private Logger logger = LoggerFactory.getLogger(EquityCardServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderEquityService orderEquityService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrderEquity getOrderEquity(String appid, String orderId) {
		OrderEquity orderEquity = orderEquityService.getOrderEquity(appid, orderId);
		if (orderEquity != null && orderEquity.getState().intValue() == 10) {
			return orderEquity;
		}

		orderEquity = orderEquityService.insertOrIgnore(appid, orderId);
		if (orderEquity == null) {
			throw new CalfException(CoreResultEnum.CLICK_REPEAT);
		}

		EquityCardBo equityCardBo = getEquityCardLocal(appid, orderId);
		orderEquity.setCardno(equityCardBo.getCardno());
		orderEquity.setPass(equityCardBo.getPass());
		orderEquity.setPgname(equityCardBo.getPgname());
		orderEquity.setPrice(new BigDecimal(equityCardBo.getPrice()));
		orderEquity.setState(10);
		orderEquityService.updateOrderEquity(orderEquity);
		return orderEquity;
	}

	private EquityCardBo getEquityCardLocal(String appid, String orderId) {
		String url = ConfigUtil.getEquityUrl();
		String signkey = ConfigUtil.getSignkey();
		String serviceid = ConfigUtil.getServiceid();
		String cpno = ConfigUtil.getCpno();
		String pgno = ConfigUtil.getPgno();

		String applytime = LocalDateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
		String sign = DigestUtils.md5Hex(signkey + applytime + DigestUtils.md5Hex(serviceid + cpno + pgno + signkey));

		url = new StringBuilder(url)//
				.append("?serviceid=").append(serviceid)//
				.append("&cpno=").append(cpno)//
				.append("&pgno=").append(pgno)//
				.append("&applytime=").append(applytime)//
				.append("&sign=").append(sign)//
				.toString();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		logger.info("appid:{},orderId:{},返回结果{}", appid, orderId, response);
		String result = response.getBody();
		EquityCardBo equityCardBo = JacksonUtil.parse(result, EquityCardBo.class);

		if (response.getStatusCodeValue() == 200 && "10000".equals(equityCardBo.getRuncode())) {
			return equityCardBo;
		} else {
			logger.error("获取卡号密码失败{}", response);
			throw new CalfException(CoreResultEnum.REMOTE_ERROR);
		}
	}

}
