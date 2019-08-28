package com.shangyong.thorder.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderBorrowBo;
import com.shangyong.thcore.bo.OrderLoanBo;
import com.shangyong.thorder.bo.OrderAuditExpireBo;

public interface TaskMapper {

	/**
	 * 获取 待还款订单列表 按照创建时间来获取
	 * @param beforeTime
	 * @param afterTime
	 * @return
	 */
	List<OrderLoanBo> listOrderLoanBo(@Param("beforeTime") Date beforeTime,
			@Param("afterTime") Date afterTime);
	
	/**
	 * 获取用户申请借款信息
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderBorrowBo getOrderBorrowBo(@Param("appid") String appid,@Param("orderId") String orderId);

	/**
	 * 获取订单信息（未结款的订单信息）
	 * @param beforeTime
	 * @param afterTime
	 * @return
	 */
	List<OrderAuditExpireBo> listOrderLoanBoNoBorrow(@Param("beforeTime") Date beforeTime,
			@Param("afterTime") Date afterTime);
	
}
