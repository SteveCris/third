package com.shangyong.thryt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thryt.entity.RytLinkman;

public interface RytLinkmanMapper {
	int deleteByPrimaryKey(String linkId);

	int insert(RytLinkman record);

	int insertSelective(RytLinkman record);

	RytLinkman selectByPrimaryKey(String linkId);

	int updateByPrimaryKeySelective(RytLinkman record);

	int updateByPrimaryKey(RytLinkman record);

	int batchSave(@Param("rytLinkmanList") List<RytLinkman> rytLinkmanList);
}