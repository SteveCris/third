package com.shangyong.thorder.controller;

import java.math.BigDecimal;

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
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.thcore.dto.OrderPreCreditDto;
import com.shangyong.thcore.vo.OrderRuleVo;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.enums.CoreResult;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.OrderCreditService;
import com.shangyong.thorder.service.OrderService;
import com.shangyong.thorder.utils.AssertUtils;
import com.shangyong.thorder.vo.OrderBussinessPreCreditVo;
import com.shangyong.thorder.vo.OrderSceneCommonVo;
import com.shangyong.thorder.vo.OrderSceneVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-订单授信相关接口")
@RestController
@RequestMapping("/orderCredit" + CoreContants.PATH_APPID)
public class OrderCreditController {

	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;

	@Autowired
	private OrderCreditService orderCreditService;

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "查询订单授信规则")
	@ResponseBody
	@RequestMapping(value = "/rule/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderRuleVo> orderRuleSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId,
			@RequestParam(name = "creditLine", required = false) BigDecimal creditLine) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		return CoreResultEnum.SUCCESS.with(orderCreditService.getOrderRuleVo(appid, orderId, creditLine));
	}

	@SleuthLoggerExclude(excludeInput=false,excludeOut = true)
	@ApiOperation(value = "前置授信填充数据（默认保险）")
	@ResponseBody
	@RequestMapping(value = "/scene/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderSceneVo> preCreditSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestParam("sceneId") int sceneId,
			@RequestParam("creditLine") String creditLine) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS
				.with(orderCreditService.getOrderSceneVo(appid, sceneId, new BigDecimal(creditLine)));
	}
	
	
	@SleuthLoggerExclude(excludeInput=false,excludeOut = true)
	@ApiOperation(value = "前置授信填充数据（通用场景）")
	@ResponseBody
	@RequestMapping(value = "/sceneCommon/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderSceneCommonVo> preCreditCommonSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestParam("sceneId") int sceneId,
			@RequestParam("creditLine") String creditLine) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS
				.with(orderCreditService.getOrderSceneCommonVo(appid, sceneId, new BigDecimal(creditLine)));
	}
	

	@ApiOperation(value = "前置授信")
	@ResponseBody
	@RequestMapping(value = "/preCredit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> preCredit(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId,
			@RequestBody OrderPreCreditDto orderPreCreditDto) {

		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		AssertUtils.notNullOrBlank(orderPreCreditDto.getProductCode(), "productCode 不能为空");
		orderService.checkStatus(appid, orderId, 60, null);

		// 获取默认产品
		RestResult<ProductConfigVo> restResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (restResult == null || !restResult.isSuccess()) {
			return CoreResultEnum.REMOTE_ERROR.with();
		}

		BigDecimal creditLine = restResult.getData().getBody().getPrice();

		if (orderCreditService.preCredit(appid, creditLine, orderId, orderPreCreditDto)) {
			return CoreResultEnum.SUCCESS.<Void>with().withMessage("支付成功!");
		} else {
			return CoreResultEnum.FAILURE.<Void>with().withMessage("支付失败!");
		}

	}

	@ApiOperation(value = "后置授信填充数据")
	@ResponseBody
	@RequestMapping(value = "/credit/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBussinessPreCreditVo> creditSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderCreditService.getOrderBussinessPreCreditVo(appid, orderId));
	}

	@ApiOperation(value = "后置授信")
	@ResponseBody
	@RequestMapping(value = "/credit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> credit(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId) {

		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");

		orderService.checkStatus(appid, orderId, 70, null);

		if (orderCreditService.credit(appid, orderId)) {
			return CoreResultEnum.SUCCESS.<Void>with().withMessage("提现成功!");
		} else {
			return CoreResultEnum.FAILURE.<Void>with().withMessage("提现失败!");
		}

	}
	
	
	// *******************************复合相关**************************************
	
	
	@ApiOperation(value = "复合填充数据")
	@ResponseBody
	@RequestMapping(value = "/compositeCredit/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderBussinessPreCreditVo> compositeCreditSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		return CoreResultEnum.SUCCESS.with(orderCreditService.getOrderBussinessCompositeCreditVo(appid, orderId));
	}
	
	
	
	@ApiOperation(value = "复合授信")
	@ResponseBody
	@RequestMapping(value = "/compositeCredit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> compositeCredit(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId,
			@RequestBody OrderPreCreditDto orderPreCreditDto) {

		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(orderId, "orderId 不能为空");
		AssertUtils.notNullOrBlank(orderPreCreditDto.getProductCode(), "productCode 不能为空");
		orderService.checkStatus(appid, orderId, 60, null);

		// 获取默认产品
		RestResult<ProductConfigVo> restResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (restResult == null || !restResult.isSuccess()) {
			return CoreResultEnum.REMOTE_ERROR.with();
		}

		BigDecimal creditLine = restResult.getData().getBody().getPrice();

		if (orderCreditService.compositeCredit(appid, creditLine, orderId, orderPreCreditDto)) {
			return CoreResultEnum.SUCCESS.<Void>with().withMessage("支付成功!");
		} else {
			return CoreResultEnum.FAILURE.<Void>with().withMessage("支付失败!");
		}

	}
	

}
