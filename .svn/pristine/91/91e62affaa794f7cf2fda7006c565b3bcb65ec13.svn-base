package com.shangyong.interact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.interact.bo.OrderUrlBo;
import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "3、订单绑卡相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/bankBind" + CoreContants.PATH_APPID)
public class OrderBankController {

	@ApiOperation(value = "绑卡环节-300-获取绑卡H5链接相关信息")
	@ResponseBody
	@RequestMapping(value = "/searchH5", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<OrderUrlBo> bindH5Search(@PathVariable(CoreContants.APPID) String appid,
			@RequestParam("appName") int appName, @RequestParam("orderId") String orderId) {
		return CoreResultEnum.SUCCESS.with();
	}

}
