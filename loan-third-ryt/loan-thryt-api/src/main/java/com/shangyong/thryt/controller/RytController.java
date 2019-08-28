package com.shangyong.thryt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thcore.dto.OrderBankH5Dto;
import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.enums.RytResult;
import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.ryt.utils.SignUtil;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.service.RytPushUserService;
import com.shangyong.thryt.service.RytUserService;
import com.shangyong.thryt.utils.JsonNodeUtil;
import com.shangyong.thryt.utils.RytUtil;
import com.shangyong.thryt.utils.UrlUtil;
import com.shangyong.thryt.vo.RytOrderSimpleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-融易推")
@RestController
@RequestMapping("/channel")
public class RytController {

	private Logger logger = LoggerFactory.getLogger(RytController.class);

	@Autowired
	private RytPushUserService rytPushUserService;

	@Autowired
	private RytUserService rytUserService;

	@Autowired
	private RytOrderService rytOrderService;

	@SleuthLoggerExclude(excludeInput = true, excludeOut = false)
	@ApiOperation(value = "融易推联登主入口")
	@ResponseBody
	@RequestMapping(value = "/dispatchChanel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RytResult<JsonNode> channelMain(@RequestBody ObjectNode node) {
		// 渠道转发
		String channel = node.get("channel").asText();
		logger.info("处理接口channel:{}", channel);

		if ("checkUser".equals(channel)) {
			return checkUser(node);
		} else if ("interfaceProtocol".equals(channel)) {
			return interfaceProtocol(node);
		} else if ("approval".equals(channel)) {
			return approval(node);
		} else if ("withdraw".equals(channel)) {
			return withdraw(node);
		} else if ("bindCardWeb".equals(channel)) {
			return bindCardWeb(node);
		} else {
			return RytResultEnum.NO_CHANNEL.with();
		}
	}

	@ApiOperation(value = "融易推-校验用户接口")
	@ResponseBody
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RytResult<JsonNode> checkUser(@RequestBody ObjectNode node) {
		ObjectNode data = SignUtil.checkAndGetData(node);

		ObjectNode result = rytUserService.checkUser(//
				data.get("mobile").asText(), //
				data.get("id_card").asText(), //
				data.get("user_name").asText(), //
				data.get("md5").asText());
		rytUserService.createCheckRecord(data, result);
		return RytResultEnum.SUCCESS.with(result);
	}

	@ApiOperation(value = "融易推-获取接口协议接口")
	@ResponseBody
	@RequestMapping(value = "/interfaceProtocol", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RytResult<JsonNode> interfaceProtocol(@RequestBody ObjectNode node) {
		if (SignUtil.checkSign(node)) {
			return RytResultEnum.SUCCESS.with(JsonNodeUtil.arrayData().add(JsonNodeUtil.data()//
					.put("protocol_name", RytUtil.getInterfaceProtocolName())//
					.put("link", RytUtil.getInterfaceProtocolUrl())));//
		} else {
			return RytResultEnum.SIGN_ERROR.with();
		}
	}

	@ApiOperation(value = "融易推-推送用户基本资料接口")
	@ResponseBody
	@RequestMapping(value = "/approval", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RytResult<JsonNode> approval(@RequestBody ObjectNode node) {
		ObjectNode data = SignUtil.checkAndGetData(node);
		// 处理成功
		logger.debug("accept data:{}", data);
		String orderNo = data.get("order_info").get("order_no").asText();
		logger.info("approval start orderNo:{}", orderNo);
		boolean isPass = rytPushUserService.accept(orderNo, data);
		logger.info("approval result:{}", isPass);
		if (isPass) {
			return RytResultEnum.SUCCESS.with();
		} else {
			return RytResultEnum.FAILURE.with();
		}

	}

	@ApiOperation(value = "融易推-点击提现接口")
	@ResponseBody
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RytResult<JsonNode> withdraw(@RequestBody ObjectNode node) {
		ObjectNode data = SignUtil.checkAndGetData(node);
		// 处理成功
		logger.info("withdraw data:{}", data);

		String orderNo = data.get("order_no").asText();
		String mobile = data.get("mobile").asText();

		RytOrderBo rytOrderBo = rytOrderService.getRytOrderBo(orderNo);
		String url;
		// 如果绑卡成功或者订单取消了，直接返回下载页面地址
		if (rytOrderBo.getStatus() == 60 || rytOrderBo.getStatus() == 1000) {
			url = RytUtil.getSuccessUrl();
		} else {
			url = getBankBindUrl(orderNo);
		}

		logger.info("withdraw  url:{}", url);

		return RytResultEnum.SUCCESS.with(JsonNodeUtil.data()//
				.put("order_no", orderNo)//
				.put("mobile", mobile)//
				.put("url", url));

	}

	@ApiOperation(value = "融易推-点击绑卡接口")
	@ResponseBody
	@RequestMapping(value = "/bindCardWeb", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RytResult<JsonNode> bindCardWeb(@RequestBody ObjectNode node) {
		ObjectNode data = SignUtil.checkAndGetData(node);
		// 处理成功
		logger.info("bindCardWeb data:{}", data);

		String orderNo = data.get("order_no").asText();
		String mobile = data.get("user_mobile").asText();

		String bankBindUrl = getBankBindUrl(orderNo);

		logger.info("bindCardWeb  url:{}", bankBindUrl);
		return RytResultEnum.SUCCESS.with(JsonNodeUtil.data()//
				.put("order_no", orderNo)//
				.put("mobile", mobile)//
				.put("url", bankBindUrl));

	}

	private String getBankBindUrl(String orderNo) {
		RytOrderSimpleVo rytOrderSimpleVo = rytOrderService.getRytOrderSimpleVo(orderNo);
		OrderBankH5Dto orderBankH5Dto = new OrderBankH5Dto();
		orderBankH5Dto.setSuccessReturnUrl(RytUtil.getSuccessUrl());
		orderBankH5Dto.setFailReturnUrl(RytUtil.getFailureUrl());
		orderBankH5Dto.setAppName(String.valueOf(RytUtil.getAppName()));

		return UrlUtil.getBankBindUrl(RytUtil.getAppid(), rytOrderSimpleVo.getOrderId(), orderBankH5Dto);
	}

}
