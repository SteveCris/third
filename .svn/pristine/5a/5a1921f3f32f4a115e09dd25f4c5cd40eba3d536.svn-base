package com.shangyong.interact.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.interact.contants.CoreContants;
import com.shangyong.interact.dto.UserCheckDto;
import com.shangyong.interact.enums.CoreVoid;
import com.shangyong.interact.enums.CoreResult;
import com.shangyong.interact.enums.CoreResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "1、撞库相关接口-cjj-蔡俊俊")
@RestController
@RequestMapping("/hitLibrary" + CoreContants.PATH_APPID)
public class HitLibraryController {

	@ApiOperation(value = "前置校验环节-100-撞库校验")
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CoreVoid> checkAndSearch(@PathVariable(CoreContants.APPID) String appid,
			@RequestBody UserCheckDto userCheckDto) {
		return CoreResultEnum.SUCCESS.with();
	}
}
