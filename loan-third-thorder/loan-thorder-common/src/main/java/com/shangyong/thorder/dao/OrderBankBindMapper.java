package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderBankBo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thorder.entity.OrderBankBind;

public interface OrderBankBindMapper {
	int deleteByPrimaryKey(String bindId);

	int insert(OrderBankBind record);

	int insertSelective(OrderBankBind record);

	OrderBankBind selectByPrimaryKey(String bindId);

	int updateByPrimaryKeySelective(OrderBankBind record);

	int updateByPrimaryKey(OrderBankBind record);

	OrderBankVo getOrderBankVo(@Param("appid") String appid, @Param("orderId") String orderId);
	
	OrderBankBo getOrderBankBo(@Param("appid") String appid, @Param("orderId") String orderId);
}