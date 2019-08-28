package com.shangyong.thorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.thcore.dto.BankVerifyCodeDto;
import com.shangyong.thcore.dto.OrderBankDto;
import com.shangyong.thcore.dto.OrderBankH5Dto;
import com.shangyong.thcore.vo.BankVerifyCodeVo;
import com.shangyong.thcore.vo.OrderBankH5Vo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.contants.RegexpEnums;
import com.shangyong.thorder.enums.CoreResult;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.OrderBankService;
import com.shangyong.thorder.service.OrderService;
import com.shangyong.thorder.utils.AssertUtils;
import com.shangyong.thorder.utils.UrlUtil;
import com.shangyong.thorder.vo.OrderBindRuleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-订单银行卡绑定相关接口")
@RestController
@RequestMapping("/orderBank" + CoreContants.PATH_APPID)
public class OrderBankController {

	@Autowired
	private OrderBankService orderBankService;

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "获取绑卡H5链接相关信息")
	@ResponseBody
	@RequestMapping(value = "/bindH5Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBankH5Vo> bindH5Search(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody OrderBankH5Dto orderBankH5Dto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		String bindH5Url = UrlUtil.getBankBindUrl(appid, orderId, orderBankH5Dto);
		OrderBankH5Vo orderBankH5Vo = new OrderBankH5Vo();
		orderBankH5Vo.setStatus(orderBankService.checkOrderBankBind(appid, orderId) ? 1 : 2);
		orderBankH5Vo.setH5Url(bindH5Url);

		return CoreResultEnum.SUCCESS.with(orderBankH5Vo);
	}

	@ApiOperation(value = "查询绑卡规则信息")
	@ResponseBody
	@RequestMapping(value = "/rule/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBindRuleVo> ruleSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderBankService.getOrderBindRuleVo(appid, orderId));
	}

	@ApiOperation(value = "查询订单绑定银行卡信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBankVo> orderBankSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderBankService.getOrderBankVo(appid, orderId));
	}

	@ApiOperation(value = "是否可以直接绑定")
	@ResponseBody
	@RequestMapping(value = "/canBind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBindRuleVo> idCardCheck(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, //
			@RequestParam("appName") String appName, //
			@RequestBody BankVerifyCodeDto bankVerifyCodeDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(appName, "appName 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		orderService.checkStatus(appid, orderId, 40, "canBind:");

		
		OrderBindRuleVo orderBindRuleVo = orderBankService.getOrderBindRuleVo(appid, orderId);
		// 中金
		if ("4".equals(orderBindRuleVo.getRule())) {
			if(orderBankService.canBind(appid, orderId, orderBindRuleVo.getRule(),
					appName, bankVerifyCodeDto.getBankCardNo())){
				return CoreResultEnum.SUCCESS.with();
			}else {
				return CoreResultEnum.NOT_EXIST_BIND.with();
			}
		}

		return CoreResultEnum.FAILURE.with();
	}

	@ApiOperation(value = "获取验证码")
	@ResponseBody
	@RequestMapping(value = "/new/verifyCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<BankVerifyCodeVo> newVerifyCode(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, //
			@RequestParam("appName") String appName, //
			@RequestBody BankVerifyCodeDto bankVerifyCodeDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(appName, "appName 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		if (!RegexpEnums.MOBILE.match(bankVerifyCodeDto.getReservedMobile())) {
			return CoreResultEnum.MOBILE_ERROR.with();
		}

		orderService.checkStatus(appid, orderId, 40, "newVCode:");
		return CoreResultEnum.SUCCESS
				.with(orderBankService.getNewBankVerifyVo(appid, appName, orderId, bankVerifyCodeDto))
				.withMessage("验证码已发送至您的预留手机号");
	}

	@ApiOperation(value = "订单银行卡绑定接口（推送订单至待审核）")
	@ResponseBody
	@RequestMapping(value = "/new/bind", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> newBind(@PathVariable("appid") String appid, //
			@RequestParam("orderId") String orderId, //
			@RequestParam("appName") String appName, //
			@RequestBody OrderBankDto orderBankDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(appName, "appName 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		orderService.checkStatus(appid, orderId, 40, "newBind:");

		if (orderBankService.bindNewBankCard(appid, orderId, appName, orderBankDto)) {
			return CoreResultEnum.SUCCESS.<Void>with().withMessage("绑卡成功!");
		} else {
			return CoreResultEnum.FAILURE.<Void>with().withMessage("绑卡失败!");
		}
	}

	// ***************************分割线****************************

	@Deprecated
	@ApiOperation(value = "获取验证码")
	@ResponseBody
	@RequestMapping(value = "/verifyCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<BankVerifyCodeVo> verifyCode(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody BankVerifyCodeDto bankVerifyCodeDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		if (!RegexpEnums.MOBILE.match(bankVerifyCodeDto.getReservedMobile())) {
			return CoreResultEnum.MOBILE_ERROR.with();
		}

		orderService.checkStatus(appid, orderId, 40, null);

		return CoreResultEnum.SUCCESS.with(orderBankService.getBankVerifyVo(appid, orderId, bankVerifyCodeDto))
				.withMessage("验证码已发送至您的预留手机号");
	}

	@Deprecated
	@ApiOperation(value = "订单银行卡绑定接口（推送订单至待审核）")
	@ResponseBody
	@RequestMapping(value = "/bind", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> bind(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId,
			@RequestBody OrderBankDto orderBankDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		orderService.checkStatus(appid, orderId, 40, null);

		if (orderBankService.bindBankCard(appid, orderId, orderBankDto)) {
			return CoreResultEnum.SUCCESS.<Void>with().withMessage("绑卡成功!");
		} else {
			return CoreResultEnum.FAILURE.<Void>with().withMessage("绑卡失败!");
		}
	}

}
