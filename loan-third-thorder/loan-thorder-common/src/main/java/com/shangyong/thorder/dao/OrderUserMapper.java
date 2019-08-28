package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderUserBo;
import com.shangyong.thcore.vo.OrderUserVo;
import com.shangyong.thorder.entity.OrderUser;

public interface OrderUserMapper {
	int deleteByPrimaryKey(String orderId);

	int insert(OrderUser record);

	int insertSelective(OrderUser record);

	OrderUser selectByPrimaryKey(String orderId);

	int updateByPrimaryKeySelective(OrderUser record);

	int updateByPrimaryKey(OrderUser record);

	OrderUserBo getOrderUserBo(@Param("appid") String appid, @Param("orderId") String orderId);

	OrderUserVo getOrderUserVo(@Param("appid") String appid, @Param("orderId") String orderId);

	int countHasRepayment(@Param("appid") String appid, @Param("identityNo") String identityNo);
}