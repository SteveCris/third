package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.OperatorPackagesItems;

public interface OperatorPackagesItemsMapper {
    int deleteByPrimaryKey(String operatorPackagesItemsId);

    int insert(OperatorPackagesItems record);

    int insertSelective(OperatorPackagesItems record);

    OperatorPackagesItems selectByPrimaryKey(String operatorPackagesItemsId);

    int updateByPrimaryKeySelective(OperatorPackagesItems record);

    int updateByPrimaryKey(OperatorPackagesItems record);
}