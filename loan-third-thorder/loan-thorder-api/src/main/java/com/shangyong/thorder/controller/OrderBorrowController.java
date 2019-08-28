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

import com.shangyong.common.entity.RestResult;
import com.shangyong.thcore.dto.OrderBorrowH5Dto;
import com.shangyong.thcore.vo.OrderBorrowH5Vo;
import com.shangyong.thcore.vo.OrderBorrowVo;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.enums.CoreResult;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.OrderBorrowService;
import com.shangyong.thorder.utils.AssertUtils;
import com.shangyong.thorder.utils.UrlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-订单借款相关接口")
@RestController
@RequestMapping("/orderBorrow" + CoreContants.PATH_APPID)
public class OrderBorrowController {

	@Autowired
	private OrderBorrowService orderBorrowService;

	@ApiOperation(value = "查询订单借款确认信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderBorrowVo> orderBorrowSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderBorrowService.getOrderBorrowVo(appid, orderId));
	}

	@ApiOperation(value = "获取前置授信 H5链接相关信息")
	@ResponseBody
	@RequestMapping(value = "/sureorderH5Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBorrowH5Vo> sureorderH5Search(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody OrderBorrowH5Dto orderBorrowH5Dto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		
		String borrowUrl = UrlUtil.getSureorderUrl(appid, orderId, orderBorrowH5Dto);
		OrderBorrowH5Vo orderBorrowH5Vo = new OrderBorrowH5Vo();
		orderBorrowH5Vo.setH5Url(borrowUrl);
		return CoreResultEnum.SUCCESS.with(orderBorrowH5Vo);
	}

	@ApiOperation(value = "获取后置授信 H5链接相关信息")
	@ResponseBody
	@RequestMapping(value = "/withdrawH5Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBorrowH5Vo> withdrawH5Search(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody OrderBorrowH5Dto orderBorrowH5Dto) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		
		String borrowUrl = UrlUtil.getWithdrawUrl(appid, orderId, orderBorrowH5Dto);
		OrderBorrowH5Vo orderBorrowH5Vo = new OrderBorrowH5Vo();
		orderBorrowH5Vo.setH5Url(borrowUrl);
		return CoreResultEnum.SUCCESS.with(orderBorrowH5Vo);
	}

}
