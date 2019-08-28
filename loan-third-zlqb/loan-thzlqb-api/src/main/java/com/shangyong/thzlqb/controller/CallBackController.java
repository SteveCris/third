package com.shangyong.thzlqb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.thzlqb.dto.AuditResultNotifyDto;
import com.shangyong.thzlqb.enums.CoreResult;
import com.shangyong.thzlqb.enums.CoreResultEnum;
import com.shangyong.thzlqb.service.CoreAuditService;
import com.shangyong.thzlqb.sign.AuditSignUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-助力钱包回调")
@RestController
@RequestMapping("/callback")
public class CallBackController {

	@Autowired
	private CoreAuditService coreAuditService;

	@ApiOperation(value = "审核回调")
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded;charset=UTF-8", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CoreResult<ObjectNode> auditCallBack(AuditResultNotifyDto auditResultNotifyDto) {
		
		if (!AuditSignUtil.checkSign(auditResultNotifyDto, auditResultNotifyDto.getSign())) {
			return CoreResultEnum.SIGN_ERROR.with();
		}
		boolean ifSuccess = "1".equals(auditResultNotifyDto.getIsPass());
		if (coreAuditService.processAudit(auditResultNotifyDto.getSerialNumber(), ifSuccess)) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}

	}
}
