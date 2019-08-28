package com.shangyong.thorder.service;

import com.shangyong.thorder.vo.OrderInsuranceVo;

public interface OrderInsuranceService {

	
	/**
	 * 查询保单信息
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderInsuranceVo getOrderInsuranceVo(String appid, String orderId);

	
	
	
}
