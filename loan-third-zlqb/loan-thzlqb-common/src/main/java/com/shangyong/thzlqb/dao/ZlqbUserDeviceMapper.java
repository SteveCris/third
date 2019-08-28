package com.shangyong.thzlqb.dao;

import com.shangyong.thzlqb.entity.ZlqbUserDevice;

public interface ZlqbUserDeviceMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(ZlqbUserDevice record);

    int insertSelective(ZlqbUserDevice record);

    ZlqbUserDevice selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(ZlqbUserDevice record);

    int updateByPrimaryKey(ZlqbUserDevice record);
}