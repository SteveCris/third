package com.shangyong.thryt.service.client;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface RytClient {

	/**
	 * 处理审核结果回调
	 * 
	 * @param data
	 * @return
	 */
	boolean pushAuditResult(ObjectNode data);

	/**
	 * 处理绑卡结果回调
	 * 
	 * @param data
	 * @return
	 */
	boolean pushBankBindResult(ObjectNode data);

	/**
	 * 推送订单回调
	 * 
	 * @param data
	 * @return
	 */
	boolean pushOrderStatusResult(ObjectNode data);

}
