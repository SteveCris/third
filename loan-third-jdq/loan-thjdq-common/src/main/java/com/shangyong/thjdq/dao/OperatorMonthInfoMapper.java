package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.OperatorMonthInfo;

public interface OperatorMonthInfoMapper {
    int deleteByPrimaryKey(String operatorMonthInfoId);

    int insert(OperatorMonthInfo record);

    int insertSelective(OperatorMonthInfo record);

    OperatorMonthInfo selectByPrimaryKey(String operatorMonthInfoId);

    int updateByPrimaryKeySelective(OperatorMonthInfo record);

    int updateByPrimaryKey(OperatorMonthInfo record);
}