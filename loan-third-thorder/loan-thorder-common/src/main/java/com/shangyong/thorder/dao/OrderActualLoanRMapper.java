package com.shangyong.thorder.dao;

import com.shangyong.thorder.entity.OrderActualLoanR;

public interface OrderActualLoanRMapper {
    int deleteByPrimaryKey(String loanId);

    int insert(OrderActualLoanR record);

    int insertSelective(OrderActualLoanR record);

    OrderActualLoanR selectByPrimaryKey(String loanId);

    int updateByPrimaryKeySelective(OrderActualLoanR record);

    int updateByPrimaryKey(OrderActualLoanR record);
}