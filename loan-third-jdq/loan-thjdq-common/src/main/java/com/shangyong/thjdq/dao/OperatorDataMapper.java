package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.OperatorData;

public interface OperatorDataMapper {
    int deleteByPrimaryKey(String operatorDataId);

    int insert(OperatorData record);

    int insertSelective(OperatorData record);

    OperatorData selectByPrimaryKey(String operatorDataId);

    int updateByPrimaryKeySelective(OperatorData record);

    int updateByPrimaryKey(OperatorData record);
}