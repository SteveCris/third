package com.shangyong.interact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.interact.bo.InteractOrderBo;
import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.dto.InteractOrderDto;
import com.shangyong.interact.enums.CoreVoid;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "2、订单相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/order" + CoreContants.PATH_APPID)
public class OrderController {

	@ApiOperation(value = "用户信息推送环节-200-接受第三方推单后，创建订单")
	@ResponseBody
	@RequestMapping(value = "/push/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<InteractOrderBo> create(@PathVariable(CoreContants.APPID) String appid,
			@RequestParam("appName") int appName, @RequestBody InteractOrderDto interactOrderDto) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "用户信息推送环节-210-第三方推单数据结束后，推送订单至待审核")
	@ResponseBody
	@RequestMapping(value = "/push/toWaitAudit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> orderToWaitAudit(@PathVariable(CoreContants.APPID) String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();
	}

	
	
}
