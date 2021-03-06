package com.shangyong.thbase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thbase.contants.CoreContants;
import com.shangyong.thbase.enums.CoreResult;
import com.shangyong.thbase.enums.CoreResultEnum;
import com.shangyong.thbase.service.BaseBankService;
import com.shangyong.thbase.service.ConfigService;
import com.shangyong.thbase.utils.AssertUtils;
import com.shangyong.thcore.vo.BankConfigVo;
import com.shangyong.thcore.vo.BankMatchVo;
import com.shangyong.thcore.vo.CenterConfigVo;
import com.shangyong.thcore.vo.ParamConfigVo;
import com.shangyong.thcore.vo.ProductConfigVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-基础配置相关接口")
@RestController
@RequestMapping("/baseConfig" + CoreContants.PATH_APPID)
public class BaseConfigController {

	@Autowired
	private ConfigService configService;

	@Autowired
	private BaseBankService baseBankService;

	@SleuthLoggerExclude(excludeOut = true)
	@ApiOperation(value = "查询信贷中心相关配置信息")
	@ResponseBody
	@RequestMapping(value = "/center/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<CenterConfigVo> getCenterConfigVo(@PathVariable("appid") String appid) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		return CoreResultEnum.SUCCESS.with(configService.getCenterConfigVo(appid));
	}

	@SleuthLoggerExclude(excludeOut = true)
	@ApiOperation(value = "查询产品相关配置信息")
	@ResponseBody
	@RequestMapping(value = "/product/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<ProductConfigVo> getProductConfigVo(@PathVariable("appid") String appid) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		return CoreResultEnum.SUCCESS.with(configService.getProductConfigVo(appid));
	}

	@SleuthLoggerExclude(excludeOut = true)
	@ApiOperation(value = "查询银行卡列表信息")
	@ResponseBody
	@RequestMapping(value = "/bank/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<List<BankConfigVo>> listBank(@PathVariable("appid") String appid,
			@RequestParam("cardType") int cardType) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		return CoreResultEnum.SUCCESS.with(baseBankService.listThBankConfigVo(appid, cardType));
	}

	@SleuthLoggerExclude(excludeOut = true)
	@ApiOperation(value = "查询银行卡匹配信息")
	@ResponseBody
	@RequestMapping(value = "/bank/match", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<BankMatchVo> bankMatch(@PathVariable("appid") String appid,
			@RequestParam("cardNoPrefix") String cardNoPrefix) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		AssertUtils.notNullOrBlank(cardNoPrefix, "卡号前六位 不能为空");
		if (cardNoPrefix.length() != 6) {
			return CoreResultEnum.FAILURE.with();
		}
		return CoreResultEnum.SUCCESS.with(baseBankService.getBankMatchVo(appid, cardNoPrefix));
	}

	@SleuthLoggerExclude(excludeOut = true)
	@ApiOperation(value = "校验银行卡信息是否支持")
	@ResponseBody
	@RequestMapping(value = "/bank/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Void> checkBank(@PathVariable("appid") String appid, @RequestParam("bankCode") String bankCode,
			@RequestParam("cardType") Integer cardType) {
		AssertUtils.notNullOrBlank(appid, "appid 不能为空");
		if (cardType == null) {
			cardType = 2;
		}
		if (baseBankService.checkBank(appid, cardType, bankCode)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}
	}

	@ApiOperation(value = "查询参数配置信息")
	@ResponseBody
	@RequestMapping(value = "/param/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<Map<String, ParamConfigVo>> listParam(@PathVariable("appid") String appid,
			@RequestParam("keys") String keys) {
		return CoreResultEnum.SUCCESS.with(configService.getListParamConfigVo(appid, keys));
	}

}
