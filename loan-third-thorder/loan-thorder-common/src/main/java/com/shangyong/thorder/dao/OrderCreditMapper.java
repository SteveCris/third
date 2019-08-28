package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderCreditBo;
import com.shangyong.thorder.entity.OrderCredit;

public interface OrderCreditMapper {
	int deleteByPrimaryKey(String creditId);

	int insert(OrderCredit record);

	int insertSelective(OrderCredit record);

	OrderCredit selectByPrimaryKey(String creditId);

	int updateByPrimaryKeySelective(OrderCredit record);

	int updateByPrimaryKey(OrderCredit record);

	OrderCreditBo getOrderCreditBo(@Param("appid") String appid, @Param("orderId") String orderId);
}