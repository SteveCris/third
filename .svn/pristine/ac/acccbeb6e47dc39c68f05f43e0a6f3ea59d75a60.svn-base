package com.shangyong.thryt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.thryt.enums.CoreResult;
import com.shangyong.thryt.enums.CoreResultEnum;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.vo.RytOrderSimpleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-融易推H5调用接口")
@RestController
@RequestMapping("/order/H5")
public class RytH5Controller {

	@Autowired
	private RytOrderService rytOrderService;

	@ApiOperation(value = "查询交互订单信息")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<RytOrderSimpleVo> orderSearch(@RequestParam("orderNo") String orderNo) {
		return CoreResultEnum.SUCCESS.with(rytOrderService.getRytOrderSimpleVo(orderNo));
	}

}
