package com.shangyong.thzlqb.dao;

import com.shangyong.thzlqb.entity.ZlqbCheckR;

public interface ZlqbCheckRMapper {
    int deleteByPrimaryKey(String checkId);

    int insert(ZlqbCheckR record);

    int insertSelective(ZlqbCheckR record);

    ZlqbCheckR selectByPrimaryKey(String checkId);

    int updateByPrimaryKeySelective(ZlqbCheckR record);

    int updateByPrimaryKey(ZlqbCheckR record);
}