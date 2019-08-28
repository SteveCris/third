package com.shangyong.thorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.thcore.vo.OrderUserVo;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.enums.CoreResult;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.OrderUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-订单用户相关接口")
@RestController
@RequestMapping("/orderUser" + CoreContants.PATH_APPID)
public class OrderUserController {

	@Autowired
	private OrderUserService orderUserService;

	@ApiOperation(value = "查询订单用户信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderUserVo> orderUserSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with(orderUserService.getOrderUserVo(appid, orderId));
	}

	@ApiOperation(value = "校验用户是不是老用户")
	@ResponseBody
	@RequestMapping(value = "/checkOlder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> checkOlder(@PathVariable("appid") String appid,
			@RequestParam("identityNo") String identityNo) {
		if (orderUserService.checkOlder(appid, identityNo)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}

	}

}
