package com.shangyong.thorder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.common.entity.RestResult;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.dto.LoanCallBackDto;
import com.shangyong.thorder.dto.RepaymentCallBackDto;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.CallBackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-回调相关接口")
@RestController
@RequestMapping("/callback" + CoreContants.PATH_APPID)
public class OrderCallBackController {

	private Logger logger = LoggerFactory.getLogger(OrderCallBackController.class);

	@Autowired
	private CallBackService callBackService;

	@ApiOperation(value = "账务系统放款回调接口")
	@RequestMapping(value = "/open/loan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> loanCallback(@PathVariable("appid") String appid,
			@RequestBody LoanCallBackDto loanCallBackDto) {
		logger.info("appid:{};resultCallBackDto:{}", appid, loanCallBackDto);
		if (callBackService.processLoanCallBack(appid, loanCallBackDto)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}

	}

	@ApiOperation(value = "账务系统还款回调接口")
	@RequestMapping(value = "/open/repayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> repaymentCallback(@PathVariable("appid") String appid,
			@RequestBody RepaymentCallBackDto repaymentCallBackDto) {
		logger.info("appid:{};resultCallBackDto:{}", appid, repaymentCallBackDto);
		if (callBackService.processRepaymentCallback(appid, repaymentCallBackDto)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}
	}

}
