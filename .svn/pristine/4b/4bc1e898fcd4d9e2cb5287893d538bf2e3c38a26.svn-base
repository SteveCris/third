package com.shangyong.thorder.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.thcore.vo.OrderRuleVo;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.contants.RedisPrefix;
import com.shangyong.thorder.entity.OrderEquity;
import com.shangyong.thorder.enums.CoreResult;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.service.EquityCardService;
import com.shangyong.thorder.service.OrderCreditService;
import com.shangyong.thorder.service.OrderService;
import com.shangyong.thorder.utils.JsonNodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "cjj-蔡俊俊-获取权益卡相关接口")
@RestController
@RequestMapping("/equity" + CoreContants.PATH_APPID)
public class EquityCardController {

	@Autowired
	private EquityCardService equityCardService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BatchRedisTemplate batchRedisTemplate;

	@Autowired
	private OrderCreditService orderCreditService;

	@ApiOperation(value = "查询权益卡信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<ObjectNode> orderSearch(@ApiIgnore @RequestHeader("XClientIp") String clientIp,
			@PathVariable("appid") String appid, @RequestParam("orderId") String orderId) {

		String riskCountKey = RedisPrefix.RISK_CLICK + "equity:" + clientIp;

		if (batchRedisTemplate.isRepeatClick(riskCountKey, 3)) {
			return CoreResultEnum.CLICK_REPEAT.with();
		} else {

			long count = batchRedisTemplate.increment(riskCountKey, 1, 1, TimeUnit.DAYS);
			if (count > 3) {
				return CoreResultEnum.RISK_CLICK.with();
			}

			try {
				orderService.checkStatus(appid, orderId, 110, null);
				batchRedisTemplate.increment(riskCountKey, -1, 1, TimeUnit.DAYS);
			} catch (CalfException e) {
				return e.getCoreResultEnum().with();
			}

		}

		OrderRuleVo orderRuleVo = orderCreditService.getOrderRuleVoHasCredit(appid, orderId);
		if (!orderRuleVo.getSceneRule().startsWith("4,")) {
			return CoreResultEnum.ERROR.with();
		}

		OrderEquity orderEquity = equityCardService.getOrderEquity(appid, orderId);

		return CoreResultEnum.SUCCESS.with(JsonNodeUtil.data()//
				.put("cardNo", orderEquity.getCardno())//
				.put("passWord", orderEquity.getPass())//
				.put("conversionUrl", "http://www.118card.com")//
		);

	}
}
