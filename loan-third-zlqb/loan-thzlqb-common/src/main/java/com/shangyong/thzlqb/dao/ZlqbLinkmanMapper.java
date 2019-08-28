package com.shangyong.thzlqb.dao;

import com.shangyong.thzlqb.entity.ZlqbLinkman;

public interface ZlqbLinkmanMapper {
    int deleteByPrimaryKey(String linkId);

    int insert(ZlqbLinkman record);

    int insertSelective(ZlqbLinkman record);

    ZlqbLinkman selectByPrimaryKey(String linkId);

    int updateByPrimaryKeySelective(ZlqbLinkman record);

    int updateByPrimaryKey(ZlqbLinkman record);
}