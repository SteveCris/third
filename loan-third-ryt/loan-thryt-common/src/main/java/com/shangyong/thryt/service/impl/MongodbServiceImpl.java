package com.shangyong.thryt.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thryt.contants.CoreContants;
import com.shangyong.thryt.service.MongodbService;
import com.shangyong.thryt.utils.JacksonUtil;

@Service
public class MongodbServiceImpl implements MongodbService {

	private Logger logger = LoggerFactory.getLogger(MongodbServiceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@SleuthLoggerExclude(excludeInput = true,excludeOut=false)
	@Override
	public boolean saveData(String collection, String id, ObjectNode data) {
		logger.info("保存mongodb记录 集合：{}，主键：{}", collection, id);
		logger.debug("保存数据为：{}", data);
		DBCollection dbCollection = getCollection(collection);
		if (data.get("create_time") == null) {
			data.put("create_time", LocalDateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		}
		data.put(CoreContants.COLLECTION_ID, id);
		WriteResult writeResult = dbCollection.save(JacksonUtil.parseWithSnakeCase(data, BasicDBObject.class));
		return writeResult.getN() > 0;
	}

	@SleuthLoggerExclude(excludeInput = true,excludeOut=false)
	@Override
	public boolean updateData(String collection, String id, ObjectNode data) {
		logger.info("修改mongodb记录 集合：{}，主键：{}", collection, id);
		logger.debug("修改数据为：{}", data);
		DBCollection dbCollection = getCollection(collection);
		WriteResult writeResult = dbCollection.update(queryById(id),
				JacksonUtil.parseWithSnakeCase(data, BasicDBObject.class));
		return writeResult.getN() > 0;
	}

	@SleuthLoggerExclude(excludeInput = false,excludeOut = true)
	@Override
	public ObjectNode getData(String collection, String id) {
		logger.info("获取mongodb记录 集合：{}，主键：{}", collection, id);
		DBObject dbObject = getCollection(collection).findOne(queryById(id));
		ObjectNode objectNode = JacksonUtil.parseToObjectNodeWithSnakeCase(dbObject);
		logger.debug("获取数据为：{}", objectNode);
		return objectNode;
	}

	// ***************************私有方法**********************************
	private DBCollection getCollection(String name) {
		DBCollection dbCollection;
		if (mongoTemplate.collectionExists(name)) {
			dbCollection = mongoTemplate.getCollection(name);
		} else {
			dbCollection = mongoTemplate.createCollection(name);
		}
		return dbCollection;
	}

	private DBObject queryById(String id) {
		return BasicDBObjectBuilder.start(CoreContants.COLLECTION_ID, id).get();
	}

}
