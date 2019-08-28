package com.shangyong.thzlqb.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * mongodb工具服务
 * 
 * @author caijunjun
 * @date 2019年7月19日
 */
public interface MongodbService {

	/**
	 * 保存对象
	 * 
	 * @param collection
	 * @param id
	 * @param data
	 * @return
	 */
	boolean saveData(String collection, String id, ObjectNode data);

	/**
	 * 修改对象
	 * 
	 * @param collection
	 * @param id
	 * @param data
	 * @return
	 */
	boolean updateData(String collection, String id, ObjectNode data);

	/**
	 * 获取对象
	 * 
	 * @param collection
	 * @param id
	 * @return
	 */
	ObjectNode getData(String collection, String id);

}
