package com.shangyong.interact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.common.entity.RestResult;
import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.dto.AuditCallBackDto;
import com.shangyong.interact.dto.LoanCallBackDto;
import com.shangyong.interact.dto.RepaymentCallBackDto;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;
import com.shangyong.interact.enums.CoreVoid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "0、回调相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/callback/open" + CoreContants.PATH_APPID)
public class CallBackController {

	@ApiOperation(value = "回调环节-010-认证审核推送回调")
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> create(@PathVariable(CoreContants.APPID) String appid,
			@RequestBody AuditCallBackDto auditCallBackDto) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "回调环节-020-账务系统放款回调接口")
	@RequestMapping(value = "/loan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> loanCallback(@PathVariable(CoreContants.APPID) String appid,
			@RequestBody LoanCallBackDto loanCallBackDto) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "回调环节-030-账务系统还款回调接口")
	@RequestMapping(value = "/repayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> repaymentCallback(@PathVariable(CoreContants.APPID) String appid,
			@RequestBody RepaymentCallBackDto repaymentCallBackDto) {
		return CoreResultEnum.SUCCESS.with();
	}

}
