package com.shangyong.thzlqb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 还款相关服务
 * 
 * @author caijunjun
 * @date 2019年7月19日
 */
public interface ZlqbRepaymentService {

	/**
	 * 获取还款页面h5
	 * 
	 * @param request
	 * @return
	 */
	JsonNode getRepaymentH5(ObjectNode request);

}
