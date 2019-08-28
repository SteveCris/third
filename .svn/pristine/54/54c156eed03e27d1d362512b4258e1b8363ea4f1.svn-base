package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thorder.entity.OrderBindRule;
import com.shangyong.thorder.vo.OrderBindRuleVo;

public interface OrderBindRuleMapper {
	int deleteByPrimaryKey(String bindRId);

	int insert(OrderBindRule record);

	int insertSelective(OrderBindRule record);

	OrderBindRule selectByPrimaryKey(String bindRId);

	int updateByPrimaryKeySelective(OrderBindRule record);

	int updateByPrimaryKey(OrderBindRule record);

	OrderBindRuleVo getOrderBindRuleVo(@Param("appid") String appid, @Param("orderId") String orderId);

	int insertOrIgnore(OrderBindRule orderBindRule);
}