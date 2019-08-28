package com.shangyong.thryt.service;

import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventUserInfo;

public interface CallBackService {

	/**
	 * 处理审核成功事件
	 * 
	 * @param eventHeader
	 * @param eventUserInfo
	 * @return
	 */
	boolean processAuditSuccess(EventHeader eventHeader, EventUserInfo eventUserInfo);

	/**
	 * 处理审核失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 * @return
	 */
	boolean processAuditFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 处理绑卡成功事件
	 * 
	 * @param eventHeader
	 * @param eventBankBind
	 * @return
	 */
	boolean processBankBindSuccess(EventHeader eventHeader, EventBankBind eventBankBind);

	/**
	 * 处理取消订单事件
	 * 
	 * @param eventHeader
	 * @return
	 */
	boolean processCancelOrder(EventHeader eventHeader);

}
