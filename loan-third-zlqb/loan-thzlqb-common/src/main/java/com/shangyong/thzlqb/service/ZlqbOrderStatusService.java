package com.shangyong.thzlqb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thcore.vo.OrderLoanVo;

/**
 * 订单状态 处理服务
 * 
 * @author caijunjun
 * @date 2019年7月19日
 */
public interface ZlqbOrderStatusService {
	/**
	 * 拉取订单状态信息
	 * 
	 * @param request
	 * @return
	 */
	JsonNode pullOrder(ObjectNode request);

	/**
	 * 推送订单状态信息
	 * 
	 * @param orderNo
	 * @return
	 */
	boolean pushOrder(String orderNo);
	
	/**
	 * 推送中间状态
	 * @param orderNo
	 * @param orderLoanVoCheck
	 * @return
	 */
	boolean pushMiddleOrder(String orderNo, OrderLoanVo orderLoanVoCheck);

	boolean concelBankInfoAndPushOrder(String orderNo,String bankCardNo);

	OrderBankVo bankInfoDtoGet(OrderLoanVo orderLoanVo);

	
}
