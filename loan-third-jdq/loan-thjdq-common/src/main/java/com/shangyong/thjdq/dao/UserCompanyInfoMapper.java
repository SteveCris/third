package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.UserCompanyInfo;
import org.apache.ibatis.annotations.Param;

public interface UserCompanyInfoMapper {
    int deleteByPrimaryKey(String userCompanyInfoId);

    int insert(UserCompanyInfo record);

    int insertSelective(UserCompanyInfo record);

    UserCompanyInfo selectByPrimaryKey(String userCompanyInfoId);

    int updateByPrimaryKeySelective(UserCompanyInfo record);

    int updateByPrimaryKey(UserCompanyInfo record);

    /**
     * 根据用户信息id获取用户公司信息
     * @param userInfoId
     * @return
     */
    UserCompanyInfo selectByUserInfoId(@Param("userInfoId") String userInfoId);
}