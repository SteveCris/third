package com.shangyong.interact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;
import com.shangyong.interact.enums.CoreVoid;
import com.shangyong.interact.vo.OrderRepaymentPlanVo;
import com.shangyong.interact.vo.RepaymentShowVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "6、H5页面-订单还款相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/H5/repayment" + CoreContants.PATH_APPID)
public class OrderRepaymentH5Controller {

	@ApiOperation(value = "还款环节-620-获取还款计划（包含成功还款情况）")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderRepaymentPlanVo> orderRepaymentSearch(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "还款环节-630-一键还款")
	@ResponseBody
	@RequestMapping(value = "/oneKey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> oneKeyRepayment(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "还款环节-631-快捷支付还款")
	@ResponseBody
	@RequestMapping(value = "/quick", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<RepaymentShowVo> quickRepayment(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {

		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "还款环节-632-微信还款")
	@ResponseBody
	@RequestMapping(value = "/wechat", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<RepaymentShowVo> weChatRepayment(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {

		return CoreResultEnum.SUCCESS.with();
	}

}
