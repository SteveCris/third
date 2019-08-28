package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.UserAddressBook;
import com.shangyong.thjdq.entity.UserContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserContactMapper {
    int deleteByPrimaryKey(String userContactId);

    int insert(UserContact record);

    int insertSelective(UserContact record);

    UserContact selectByPrimaryKey(String userContactId);

    int updateByPrimaryKeySelective(UserContact record);

    int updateByPrimaryKey(UserContact record);

    /**
     * 根据用户信息id查询紧急联系人
     * @param userInfoId
     * @return
     */
    UserContact selectByUserInfoId(@Param("userInfoId") String userInfoId);
}