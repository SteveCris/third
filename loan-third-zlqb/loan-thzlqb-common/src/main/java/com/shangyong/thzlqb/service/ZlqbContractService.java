package com.shangyong.thzlqb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 签约服务
 * 
 * @author caijunjun
 * @date 2019年7月19日
 */
public interface ZlqbContractService {

	/**
	 * 获取签约信息
	 * 
	 * @param request
	 * @return
	 */
	JsonNode getSignInfo(ObjectNode request);

	/**
	 * 签约操作
	 * 
	 * @param request
	 * @return
	 */
	int signContract(ObjectNode request);

	/**
	 * 根据状态获取签约地址
	 * 
	 * @param request
	 * @return
	 */
	JsonNode getCreditH5(ObjectNode request);


}
