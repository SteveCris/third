package com.shangyong.thorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.common.entity.RestResult;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.OrderInsuranceService;
import com.shangyong.thorder.utils.AssertUtils;
import com.shangyong.thorder.vo.OrderInsuranceVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-订单保单查询相关接口")
@RestController
@RequestMapping("/orderInsurance" + CoreContants.PATH_APPID)
public class OrderInsuranceController {
	
	@Autowired
	private OrderInsuranceService orderInsuranceService;

	@ApiOperation(value = "查询保单信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderInsuranceVo> orderBorrowSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderInsuranceService.getOrderInsuranceVo(appid, orderId));
	}
}
