package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.UserAddressBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressBookMapper {
    int deleteByPrimaryKey(String addressBookId);

    int insert(UserAddressBook record);

    int insertSelective(UserAddressBook record);

    UserAddressBook selectByPrimaryKey(String addressBookId);

    int updateByPrimaryKeySelective(UserAddressBook record);

    int updateByPrimaryKey(UserAddressBook record);

    /**
     * 根据用户信息id查询通讯录
     * @param userInfoId
     * @return
     */
    List<UserAddressBook> selectByUserInfoId(@Param("userInfoId") String userInfoId);
}