package com.shangyong.thryt.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface MongodbService {

	/**
	 * 保存对象
	 * @param collection
	 * @param id
	 * @param data
	 * @return
	 */
	boolean saveData(String collection, String id, ObjectNode data);
	
	/**
	 * 修改对象
	 * @param collection TODO
	 * @param id
	 * @param data
	 * @return
	 */
	boolean updateData(String collection, String id, ObjectNode data);

	/**
	 * 获取对象
	 * @param collection TODO
	 * @param id
	 * @return
	 */
	ObjectNode getData(String collection, String id);

}
