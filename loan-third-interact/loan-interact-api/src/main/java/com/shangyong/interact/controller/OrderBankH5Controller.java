package com.shangyong.interact.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.dto.BankVerifyCodeDto;
import com.shangyong.interact.dto.OrderBankDto;
import com.shangyong.interact.enums.CoreVoid;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;
import com.shangyong.interact.utils.AssertUtils;
import com.shangyong.interact.vo.BankConfigVo;
import com.shangyong.interact.vo.BankMatchVo;
import com.shangyong.interact.vo.BankVerifyCodeVo;
import com.shangyong.interact.vo.OrderBankVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "3、H5页面-订单绑卡相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/H5/bankBind" + CoreContants.PATH_APPID)
public class OrderBankH5Controller {

	@ApiOperation(value = "绑卡环节H5-310-静态-获取银行卡列表")
	@ResponseBody
	@RequestMapping(value = "/bankStatic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<List<BankConfigVo>> bankStatic(@PathVariable(CoreContants.APPID) String appid,
			@RequestParam("appName") int appName) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "绑卡环节H5-320-静态-按照银行卡前缀获取银行信息")
	@ResponseBody
	@RequestMapping(value = "/matchStatic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<List<BankMatchVo>> matchStatic(@PathVariable(CoreContants.APPID) String appid,
			@RequestParam("appName") int appName, @RequestParam("cardNoPrefix") String cardNoPrefix) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "绑卡环节H5-330-获取订单绑卡信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBankVo> search(@PathVariable(CoreContants.APPID) String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "绑卡环节H5-340-获取验证码")
	@ResponseBody
	@RequestMapping(value = "/verifyCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<BankVerifyCodeVo> verifyCode(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId,
			@RequestBody BankVerifyCodeDto bankVerifyCodeDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "绑卡环节H5-350-绑卡操作")
	@ResponseBody
	@RequestMapping(value = "/bind", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> bind(@PathVariable("appid") String appid, @RequestParam("appName") int appName,
			@RequestParam("orderId") String orderId, @RequestBody OrderBankDto orderBankDto) {

		return CoreResultEnum.SUCCESS.with();
	}

}
