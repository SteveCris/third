package com.shangyong.thbase.service;

import com.shangyong.thcore.dto.CheckUserInfoDto;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thcore.vo.BaseUserInfoVo;

public interface BaseUserService {

	/**
	 * 处理审核事件
	 * 
	 * @param eventHeader
	 * @param eventUserInfo
	 * @return
	 */
	boolean processAuditEvent(EventHeader eventHeader, EventUserInfo eventUserInfo);

	/**
	 * 处理审核失败事件
	 * 
	 * @param eventHeader
	 * @param eventUserInfo
	 * @param eventFailureResult
	 * @return
	 */
	boolean processFailureAuditEvent(EventHeader eventHeader, EventUserInfo eventUserInfo,
			EventFailureResult eventFailureResult);

	/**
	 * 校验用户，并返回用户信息
	 * 
	 * @param checkUserInfoDto
	 * @return
	 */
	BaseUserInfoVo checkAndGetUserInfo(CheckUserInfoDto checkUserInfoDto);

	/**
	 * 校验用户，并返回用户信息
	 * 
	 * @param appid
	 * @param identityNo
	 * @return
	 */
	BaseUserInfoVo checkAndGetUserInfo(String appid, String identityNo);

}
