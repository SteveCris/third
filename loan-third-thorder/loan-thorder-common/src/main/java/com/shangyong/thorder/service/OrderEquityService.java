package com.shangyong.thorder.service;

import com.shangyong.thorder.entity.OrderEquity;

public interface OrderEquityService {

	OrderEquity getOrderEquity(String appid, String orderId);

	/**
	 * 创建操作
	 * 
	 * @param orderEquity
	 * @return
	 */
	OrderEquity insertOrIgnore(String appid, String orderId);

	/**
	 * 修改操作
	 * 
	 * @param orderEquity
	 * 
	 * @return
	 */
	boolean updateOrderEquity(OrderEquity orderEquity);
}
