package com.shangyong.thorder.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderRuleBo;
import com.shangyong.thcore.vo.OrderRuleVo;
import com.shangyong.thorder.entity.OrderCreditRule;

public interface OrderCreditRuleMapper {
	int deleteByPrimaryKey(String ruleId);

	int insert(OrderCreditRule record);

	int insertSelective(OrderCreditRule record);

	OrderCreditRule selectByPrimaryKey(String ruleId);

	int updateByPrimaryKeySelective(OrderCreditRule record);

	int updateByPrimaryKey(OrderCreditRule record);

	int insertOrIgnore(OrderCreditRule record);

	OrderRuleVo getOrderRuleVo(@Param("appid") String appid, @Param("orderId") String orderId,
			@Param("creditLine") BigDecimal creditLine);

	OrderRuleBo getOrderRuleBo(@Param("appid") String appid, @Param("orderId") String orderId,
			@Param("creditLine") BigDecimal creditLine);

	int removeCreditRule(@Param("appid") String appid, @Param("orderId") String orderId);
}