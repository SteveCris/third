package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderRepaymentBo;
import com.shangyong.thorder.entity.OrderRepayment;

public interface OrderRepaymentMapper {
	int deleteByPrimaryKey(String repaymentId);

	int insert(OrderRepayment record);

	int insertSelective(OrderRepayment record);

	OrderRepayment selectByPrimaryKey(String repaymentId);

	int updateByPrimaryKeySelective(OrderRepayment record);

	int updateByPrimaryKey(OrderRepayment record);

	OrderRepaymentBo getOrderRepaymentBo(@Param("appid") String appid, @Param("orderId") String orderId);
}