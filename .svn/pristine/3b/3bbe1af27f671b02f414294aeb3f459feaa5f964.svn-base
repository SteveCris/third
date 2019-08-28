package com.shangyong.thzlqb.controller;

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
import com.shangyong.loan.ext.util.JsonNodeUtil;
import com.shangyong.thzlqb.enums.ZlqbResult;
import com.shangyong.thzlqb.enums.ZlqbResultEnum;
import com.shangyong.thzlqb.service.ZlqbContractService;
import com.shangyong.thzlqb.service.ZlqbRepaymentService;
import com.shangyong.thzlqb.service.ZlqbUserService;
import com.shangyong.thzlqb.service.ZlqbOrderService;
import com.shangyong.thzlqb.service.ZlqbOrderStatusService;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-助力钱包")
@RestController
@RequestMapping("/rongl/zlqb")
public class ZlqbController {

	@Autowired
	private ZlqbOrderService zlqbOrderService;

	@Autowired
	private ZlqbContractService zlqbContractService;

	@Autowired
	private ZlqbRepaymentService zlqbRepaymentService;

	@Autowired
	private ZlqbOrderStatusService zlqbOrderStatusService;

	@Autowired
	private ZlqbUserService zlqbUserService;
	
	@ApiOperation(value = "准入校验")
	@ResponseBody
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> checkUser(@RequestBody ObjectNode node) {
		return ZlqbResultEnum.SUCCESS.with(zlqbUserService.processCheckUser(ZlqbUtil.checkAndGetRequest(node)));
	}

	@SleuthLoggerExclude(excludeInput = true, excludeOut = false)
	@ApiOperation(value = "进件")
	@ResponseBody
	@RequestMapping(value = "/pushOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> pushOrder(@RequestBody ObjectNode node) {
		boolean flag = zlqbOrderService.processPushOrder(ZlqbUtil.checkAndGetRequest(node));
		ObjectNode response;
		if (flag) {
			response = JsonNodeUtil.data().put("push_status", "301").put("push_msg", "进件成功");
		} else {
			response = JsonNodeUtil.data().put("push_status", "302").put("push_msg", "进件失败");
		}
		return ZlqbResultEnum.SUCCESS.with(response);
	}

	@ApiOperation(value = "获取签约信息")
	@ResponseBody
	@RequestMapping(value = "/getSignInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> getSignInfo(@RequestBody ObjectNode node) {
		return ZlqbResultEnum.SUCCESS.with(zlqbContractService.getSignInfo(ZlqbUtil.checkAndGetRequest(node)));
	}

	@ApiOperation(value = "签约")
	@ResponseBody
	@RequestMapping(value = "/getContract", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> getContract(@RequestBody ObjectNode node) {
		int flag = zlqbContractService.signContract(ZlqbUtil.checkAndGetRequest(node));
		ObjectNode response;
		if (flag==1) {
			response = JsonNodeUtil.data().put("sign_status", "701").put("sign_msg", "签约成功");
			return ZlqbResultEnum.SUCCESS.with(response);
		}
		if(flag==2) {
			response = JsonNodeUtil.data().put("sign_status", "702").put("sign_msg", "签约失败");
			return ZlqbResultEnum.SUCCESS.with(response);
		}
		response = JsonNodeUtil.data().put("sign_status", "702").put("sign_msg", "银行卡信息已经失效，请更换绑卡");
		return ZlqbResultEnum.SUCCESS.with(response);
	}

	@ApiOperation(value = "开户")
	@ResponseBody
	@RequestMapping(value = "/openAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> openAccount(@RequestBody ObjectNode node) {
		return ZlqbResultEnum.SUCCESS.with(zlqbContractService.getCreditH5(ZlqbUtil.checkAndGetRequest(node)));
	}
	@ApiOperation(value = "提现")
	@ResponseBody
	@RequestMapping(value = "/withDraw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> withDraw(@RequestBody ObjectNode node) {
		return ZlqbResultEnum.SUCCESS.with(zlqbContractService.getCreditH5(ZlqbUtil.checkAndGetRequest(node)));
	}


	@ApiOperation(value = "主动还款H5")
	@ResponseBody
	@RequestMapping(value = "/activeRepayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> activeRepayment(@RequestBody ObjectNode node) {
		return ZlqbResultEnum.SUCCESS.with(zlqbRepaymentService.getRepaymentH5(ZlqbUtil.checkAndGetRequest(node)));
	}

	@ApiOperation(value = "主动拉取订单信息")
	@ResponseBody
	@RequestMapping(value = "/getOrderStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZlqbResult<JsonNode> getOrderStatus(@RequestBody ObjectNode node) {
		return ZlqbResultEnum.SUCCESS.with(zlqbOrderStatusService.pullOrder(ZlqbUtil.checkAndGetRequest(node)));
	}

}
