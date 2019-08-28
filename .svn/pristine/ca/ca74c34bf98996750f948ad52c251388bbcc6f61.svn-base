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

import com.shangyong.thcore.dto.OrderLoanDto;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.enums.CoreResult;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.OrderService;
import com.shangyong.thorder.utils.AssertUtils;
import com.shangyong.thorder.utils.CheckUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-订单相关接口")
@RestController
@RequestMapping("/order" + CoreContants.PATH_APPID)
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "创建订单")
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderLoanVo> orderCreate(@PathVariable("appid") String appid,
			@RequestBody OrderLoanDto orderLoanDto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		return CoreResultEnum.SUCCESS.with(orderService.createOrder(appid, orderLoanDto));
	}

	@ApiOperation(value = "查询订单")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderLoanVo> orderSearch(@PathVariable("appid") String appid,
			@RequestParam("otherOrderId") String otherOrderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(otherOrderId, "otherOrderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderService.getOrderLoanVo(appid, otherOrderId));
	}

	@ApiOperation(value = "校验是否有在途订单")
	@ResponseBody
	@RequestMapping(value = "/onWay/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> orderOnWayCheck(@PathVariable("appid") String appid,
			@RequestParam("identityNo") String identityNo) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(identityNo, "identityNo 不能为空");

		if (orderService.checkOnWayOrder(appid, identityNo)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}
	}

	@ApiOperation(value = "推送订单至待审核")
	@ResponseBody
	@RequestMapping(value = "/toWaitAudit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> orderToWaitAudit(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		if (orderService.toWaitAudit(appid, orderId)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}
	}

	@ApiOperation(value = "取消订单")
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> orderCancel(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId,
			@RequestParam("token") String token) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		if (!CheckUtil.checkToken(token)) {
			return CoreResultEnum.AUTH_ERROR.with();
		}

		if (orderService.cancelOrder(appid, orderId)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}
	}

}
