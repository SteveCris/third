package com.shangyong.thzlqb.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thzlqb.bo.OrderSimpleBo;
import com.shangyong.thzlqb.entity.ZlqbOrder;

public interface ZlqbOrderMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(ZlqbOrder record);

    int insertSelective(ZlqbOrder record);

    ZlqbOrder selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(ZlqbOrder record);

    int updateByPrimaryKey(ZlqbOrder record);
    
    int count(@Param("orderNo") String orderNo);

	int updateOrderStatus(@Param("orderNo") String orderNo, @Param("newStatus") int newStatus,
			@Param("oldStatus") int oldStatus, @Param("ifFinish") boolean ifFinish, @Param("remark") String remark);

	OrderSimpleBo getOrderSimpleBoByOrderNo(@Param("orderNo") String orderNo);

	OrderSimpleBo getOrderSimpleBoByOrderId(@Param("orderId") String orderId);

    int updateOrderIsSign(@Param("orderNo")String orderNo);
}