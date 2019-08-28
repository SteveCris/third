package com.shangyong.thbase.controller;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.thbase.enums.CoreResult;
import com.shangyong.thbase.enums.CoreResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-核心订单")
@RestController
@RequestMapping("/test")
public class TestController {

	@ApiOperation(value = "测试订单，后期删除")
	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> orderSearch() {
		try {
			TimeUnit.SECONDS.sleep(RandomUtils.nextInt(0, 7));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CoreResultEnum.SUCCESS.with();
	}



}
