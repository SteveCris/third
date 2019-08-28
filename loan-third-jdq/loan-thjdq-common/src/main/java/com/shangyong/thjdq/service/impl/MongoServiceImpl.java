package com.shangyong.thjdq.service.impl;

import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thjdq.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by ybds on 2019-04-02.
 */
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @SleuthLoggerExclude(excludeOut = true, excludeInput = false)
    public <T> T findOne(Query query, Class<T> clazz, String collectionName) {
        return mongoTemplate.findOne(query, clazz, collectionName);
    }
}
