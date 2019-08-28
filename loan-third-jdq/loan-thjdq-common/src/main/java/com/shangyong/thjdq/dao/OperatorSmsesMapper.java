package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.OperatorSmses;

public interface OperatorSmsesMapper {
    int deleteByPrimaryKey(String operatorSmsesId);

    int insert(OperatorSmses record);

    int insertSelective(OperatorSmses record);

    OperatorSmses selectByPrimaryKey(String operatorSmsesId);

    int updateByPrimaryKeySelective(OperatorSmses record);

    int updateByPrimaryKey(OperatorSmses record);
}