package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.OperatorCalls;

public interface OperatorCallsMapper {
    int deleteByPrimaryKey(String operatorCallsId);

    int insert(OperatorCalls record);

    int insertSelective(OperatorCalls record);

    OperatorCalls selectByPrimaryKey(String operatorCallsId);

    int updateByPrimaryKeySelective(OperatorCalls record);

    int updateByPrimaryKey(OperatorCalls record);
}