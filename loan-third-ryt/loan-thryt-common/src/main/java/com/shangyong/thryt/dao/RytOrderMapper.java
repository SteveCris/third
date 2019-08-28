package com.shangyong.thryt.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.bo.RytOrderSimpleBo;
import com.shangyong.thryt.entity.RytOrder;

public interface RytOrderMapper {
	int deleteByPrimaryKey(String orderNo);

	int insert(RytOrder record);

	int insertSelective(RytOrder record);

	RytOrder selectByPrimaryKey(String orderNo);

	int updateByPrimaryKeySelective(RytOrder record);

	int updateByPrimaryKey(RytOrder record);

	int count(@Param("orderNo") String orderNo);

	int updateOrderStatus(@Param("orderNo") String orderNo, @Param("newStatus") int newStatus,
			@Param("oldStatus") int oldStatus, @Param("ifFinish") boolean ifFinish, @Param("remark") String remark);

	RytOrderBo getRytOrderBo(@Param("orderNo") String orderNo);

	RytOrderSimpleBo getRytOrderSimpleBoByOrderId(@Param("orderId") String orderId);

}