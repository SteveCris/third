package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thorder.entity.OrderEquity;

public interface OrderEquityMapper {
    int deleteByPrimaryKey(String eqId);

    int insert(OrderEquity record);

    int insertSelective(OrderEquity record);

    OrderEquity selectByPrimaryKey(String eqId);

    int updateByPrimaryKeySelective(OrderEquity record);

    int updateByPrimaryKey(OrderEquity record);

	int insertOrIgnore(OrderEquity orderEquity);

	OrderEquity getOrderEquity(@Param("appid") String appid,@Param("orderId") String orderId);
}