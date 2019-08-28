package com.shangyong.thryt.dao;

import com.shangyong.thryt.entity.RytCheckR;

public interface RytCheckRMapper {
    int deleteByPrimaryKey(String checkId);

    int insert(RytCheckR record);

    int insertSelective(RytCheckR record);

    RytCheckR selectByPrimaryKey(String checkId);

    int updateByPrimaryKeySelective(RytCheckR record);

    int updateByPrimaryKey(RytCheckR record);
}