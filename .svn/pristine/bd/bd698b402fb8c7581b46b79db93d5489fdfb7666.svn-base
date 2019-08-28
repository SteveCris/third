package com.shangyong.thorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.service.TaskService;
import com.shangyong.thorder.utils.CheckUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cjj-蔡俊俊-任务相关接口")
@RestController
@RequestMapping("/task")
public class OrderTaskController {

	@Autowired
	private BatchRedisTemplate batchRedisTemplate;

	@Autowired
	private TaskService taskService;

	@ApiOperation(value = "逾期计算接口")
	@RequestMapping(value = "/open/overdue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> overdue(@RequestParam("token") String token,
			@RequestParam(required = false, value = "beforeDateStr") String beforeDateStr,
			@RequestParam(required = false, value = "afterDateStr") String afterDateStr) {

		if (batchRedisTemplate.isRepeatClick("OVERDUE", 120)) {
			return CoreResultEnum.CLICK_REPEAT.with();
		}

		if (!CheckUtil.checkToken(token)) {
			return CoreResultEnum.AUTH_ERROR.with();
		}

		taskService.processOverdue(beforeDateStr, afterDateStr);

		return CoreResultEnum.SUCCESS.with();
	}
	
	@ApiOperation(value = "审核有效期过期")
	@RequestMapping(value = "/open/auditExpire", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> auditExpire(@RequestParam("token") String token,
			@RequestParam(required = false, value = "beforeDateStr") String beforeDateStr,
			@RequestParam(required = false, value = "afterDateStr") String afterDateStr) {

		if (batchRedisTemplate.isRepeatClick("auditExpire", 120)) {
			return CoreResultEnum.CLICK_REPEAT.with();
		}

		if (!CheckUtil.checkToken(token)) {
			return CoreResultEnum.AUTH_ERROR.with();
		}

		taskService.processAuditExpire(beforeDateStr, afterDateStr);

		return CoreResultEnum.SUCCESS.with();
	}

}
