package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.UserLoanInfo;

public interface UserLoanInfoMapper {
    int deleteByPrimaryKey(String loanInfoId);

    int insert(UserLoanInfo record);

    int insertSelective(UserLoanInfo record);

    UserLoanInfo selectByPrimaryKey(String loanInfoId);

    int updateByPrimaryKeySelective(UserLoanInfo record);

    int updateByPrimaryKey(UserLoanInfo record);
}