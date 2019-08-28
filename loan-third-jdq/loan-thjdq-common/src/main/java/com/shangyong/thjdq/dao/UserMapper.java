package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据身份证查询用户来源
     * @param idNumber
     * @return
     */
    User selectByIdNumber(String idNumber);

    /**
     * 根据身份证查询用户来源
     * @param maskIdNumber
     * @param maskUserName
     * @return
     */
    User selectByIdNumberAndUserName(@Param("maskIdNumber") String maskIdNumber, @Param("maskUserName") String maskUserName);

}