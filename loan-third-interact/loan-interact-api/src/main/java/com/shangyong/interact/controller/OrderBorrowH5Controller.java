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
import com.shangyong.interact.vo.OrderBorrowTrialVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "5、H5页面-订单借款相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/H5/borrow" + CoreContants.PATH_APPID)
public class OrderBorrowH5Controller {

	@ApiOperation(value = "借款环节H5-510-获取借款试算数据")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBorrowTrialVo> search(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "借款环节H5-520-同意借款")
	@ResponseBody
	@RequestMapping(value = "/agree", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> agree(@PathVariable("appid") String appid, @RequestParam("appName") int appName,
			@RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();

	}

}
