package com.shangyong.thzlqb.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thzlqb.bo.OrderLinkBo;
import com.shangyong.thzlqb.entity.ZlqbOrderLink;

public interface ZlqbOrderLinkMapper {
    int deleteByPrimaryKey(String linkId);

    int insert(ZlqbOrderLink record);

    int insertSelective(ZlqbOrderLink record);

    ZlqbOrderLink selectByPrimaryKey(String linkId);

    int updateByPrimaryKeySelective(ZlqbOrderLink record);

    int updateByPrimaryKey(ZlqbOrderLink record);
    
    OrderLinkBo getOrderLinkBo(@Param("orderNo") String orderNo, @Param("newStatus") int newStatus,
			@Param("oldStatus") int oldStatus);
}