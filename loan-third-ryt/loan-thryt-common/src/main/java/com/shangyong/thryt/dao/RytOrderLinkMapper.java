package com.shangyong.thryt.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thryt.bo.RytOrderLinkBo;
import com.shangyong.thryt.entity.RytOrderLink;

public interface RytOrderLinkMapper {
	int deleteByPrimaryKey(String linkId);

	int insert(RytOrderLink record);

	int insertSelective(RytOrderLink record);

	RytOrderLink selectByPrimaryKey(String linkId);

	int updateByPrimaryKeySelective(RytOrderLink record);

	int updateByPrimaryKey(RytOrderLink record);

	RytOrderLinkBo getRytOrderLinkBo(@Param("orderNo") String orderNo, @Param("newStatus") int newStatus,
			@Param("oldStatus") int oldStatus);
}