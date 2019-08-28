package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderActualLoanBo;
import com.shangyong.thcore.vo.OrderActualLoanVo;
import com.shangyong.thorder.entity.OrderActualLoan;

public interface OrderActualLoanMapper {
	int deleteByPrimaryKey(String loanId);

	int insert(OrderActualLoan record);

	int insertSelective(OrderActualLoan record);

	OrderActualLoan selectByPrimaryKey(String loanId);

	int updateByPrimaryKeySelective(OrderActualLoan record);

	int updateByPrimaryKey(OrderActualLoan record);

	OrderActualLoanVo getOrderActualLoanVo(@Param("appid") String appid, @Param("orderId") String orderId);
	
	OrderActualLoanBo getOrderActualLoanBo(@Param("appid") String appid, @Param("orderId") String orderId);
}