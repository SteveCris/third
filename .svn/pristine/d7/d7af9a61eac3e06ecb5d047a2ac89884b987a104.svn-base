package com.shangyong.interact.controller;

import java.math.BigDecimal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.enums.CoreVoid;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;
import com.shangyong.interact.vo.OrderRuleVo;
import com.shangyong.interact.vo.OrderSceneVo;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "4、H5页面-订单场景相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/H5/scene" + CoreContants.PATH_APPID)
public class OrderSceneH5Controller {

	@ApiOperation(value = "场景环节H5-410-获取场景规则")
	@ResponseBody
	@RequestMapping(value = "/rule/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderRuleVo> orderRuleSearch(@PathVariable("appid") String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId,
			@RequestParam(name = "creditLine", required = false) BigDecimal creditLine) {

		return CoreResultEnum.SUCCESS.with();
	}

	@SleuthLoggerExclude(excludeOut = true)
	@ApiOperation(value = "场景环节H5-420-获取场景数据")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderSceneVo> search(@PathVariable("appid") String appid, @RequestParam("appName") int appName,
			@RequestParam("orderId") String orderId, @RequestParam("sceneId") int sceneId,
			@RequestParam("creditLine") String creditLine) {
		return CoreResultEnum.SUCCESS.with();
	}

	@ApiOperation(value = "场景环节H5-430-同意场景操作")
	@ResponseBody
	@RequestMapping(value = "/agree", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> agree(@PathVariable("appid") String appid, @RequestParam("appName") int appName,
			@RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();

	}

}
