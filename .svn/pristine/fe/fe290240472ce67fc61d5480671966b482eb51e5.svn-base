package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderPreCreditBo;
import com.shangyong.thcore.vo.OrderPreCreditVo;
import com.shangyong.thorder.entity.OrderPreCredit;

public interface OrderPreCreditMapper {
	int deleteByPrimaryKey(String preCreditId);

	int insert(OrderPreCredit record);

	int insertSelective(OrderPreCredit record);

	OrderPreCredit selectByPrimaryKey(String preCreditId);

	int updateByPrimaryKeySelective(OrderPreCredit record);

	int updateByPrimaryKey(OrderPreCredit record);

	OrderPreCreditVo getOrderPreCreditVo(@Param("appid") String appid, @Param("orderId") String orderId);

	OrderPreCreditBo getOrderPreCreditBo(@Param("appid") String appid, @Param("orderId") String orderId);

	int invalidPreCredit(@Param("appid") String appid, @Param("orderId") String orderId,
			@Param("remark") String remark);
}