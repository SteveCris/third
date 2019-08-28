package com.shangyong.thorder.service;

import com.shangyong.thcore.event.dto.EventActualLoan;
import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventPreSign;
import com.shangyong.thcore.event.dto.EventRepayment;
import com.shangyong.thcore.event.dto.EventSign;
import com.shangyong.thcore.event.dto.EventUserInfo;

/**
 * 订单事件服务
 * 
 * @author caijunjun
 * @date 2019年3月14日
 */
public interface OrderEventService {

	/**
	 * 处理审核成功事件
	 * 
	 * @param eventHeader
	 * @param eventUserInfo
	 *            审核用户信息
	 * @return
	 */
	boolean processAuditSuccess(EventHeader eventHeader, EventUserInfo eventUserInfo);

	/**
	 * 处理审核失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 *            事件失败原因
	 * @return
	 */
	boolean processAuditFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 处理银行卡绑定成功事件
	 * 
	 * @param eventHeader
	 * @param eventBankBind
	 *            银行绑定信息
	 * @return
	 */
	boolean processBankBindSuccess(EventHeader eventHeader, EventBankBind eventBankBind);

	/**
	 * 处理银行卡绑定失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 *            事件失败原因
	 * 
	 * @return
	 */
	boolean processBankBindFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 处理前置签约成功事件
	 * 
	 * @param eventHeader
	 * @param eventPreSign
	 *            前置签约信息
	 * 
	 * @return
	 */
	boolean processPreSignSuccess(EventHeader eventHeader, EventPreSign eventPreSign);

	/**
	 * 处理前置签约失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 *            事件失败原因
	 * 
	 * @return
	 */
	boolean processPreSignFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 处理授信成功事件
	 * 
	 * @param eventHeader
	 * @param eventSign
	 *            后置签约信息
	 * @return
	 */
	boolean processSignSuccess(EventHeader eventHeader, EventSign eventSign);

	/**
	 * 处理授信失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 *            事件失败信息
	 * @return
	 */
	boolean processSignFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 放款成功事件
	 * 
	 * @param eventHeader
	 * @param eventActualLoan
	 *            放款成功信息
	 * @return
	 */
	boolean processActualLoanSuccess(EventHeader eventHeader, EventActualLoan eventActualLoan);

	/**
	 * 处理放款失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 *            放款失败错误信息
	 * @return
	 */
	boolean processActualLoanFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 还款成功事件
	 * 
	 * @param eventHeader
	 * @param eventRepayment
	 * @return
	 */
	boolean processRepaymentSuccess(EventHeader eventHeader, EventRepayment eventRepayment);

	/**
	 * 还款失败事件
	 * 
	 * @param eventHeader
	 * @param eventFailureResult
	 * @return
	 */
	boolean processRepaymentFailure(EventHeader eventHeader, EventFailureResult eventFailureResult);

	/**
	 * 处理前置授信失效事件
	 * 
	 * @param eventHeader
	 * 
	 * @return
	 */
	boolean processPreCreditExpireEvent(EventHeader eventHeader);

	/**
	 * 逾期事件
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	boolean processOverdue(String appid, String orderId);

	/**
	 * 订单取消事件
	 * 
	 * @param appid
	 * @param orderId
	 * @param remark 
	 * @return
	 */
	boolean processCancel(String appid, String orderId, String remark);

}
