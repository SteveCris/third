package com.shangyong.thorder.service;

import com.shangyong.thorder.dto.LoanCallBackDto;
import com.shangyong.thorder.dto.RepaymentCallBackDto;

public interface CallBackService {

	/**
	 * 放款成功回调
	 * 
	 * @param appid
	 * @param loanCallBackDto
	 * @return
	 */
	boolean processLoanCallBack(String appid, LoanCallBackDto loanCallBackDto);

	/**
	 * 
	 * 还款成功回调
	 * 
	 * @param appid
	 * @param repaymentCallBackDto
	 * @return
	 */
	boolean processRepaymentCallback(String appid, RepaymentCallBackDto repaymentCallBackDto);
}
