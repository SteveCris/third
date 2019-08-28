package com.shangyong.thzlqb.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 订单处理服务
 * 
 * @author caijunjun
 * @date 2019年7月19日
 */
public interface ZlqbOrderService {

	/**
	 * 处理推送订单请求
	 * 
	 * @param request
	 * @return
	 */
	boolean processPushOrder(ObjectNode request);

}
