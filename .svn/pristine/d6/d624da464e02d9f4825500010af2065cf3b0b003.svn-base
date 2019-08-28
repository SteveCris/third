package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderLoanBo;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thorder.entity.OrderLoan;

public interface OrderLoanMapper {
	int deleteByPrimaryKey(String orderId);

	int insert(OrderLoan record);

	int insertSelective(OrderLoan record);

	OrderLoan selectByPrimaryKey(String orderId);

	int updateByPrimaryKeySelective(OrderLoan record);

	int updateByPrimaryKey(OrderLoan record);

	/**
	 * 创建或者忽略订单
	 * 
	 * @param orderLoan
	 * @return
	 */
	int createOrIgnore(OrderLoan orderLoan);

	/**
	 * 更新订单状态
	 * 
	 * @param appid
	 *            应用id
	 * @param orderId
	 *            订单id
	 * @param newStatus
	 *            新状态
	 * @param oldStatus
	 *            旧状态
	 * @param ifFinish
	 *            是否结束
	 * @param repaymentStatus
	 *            还款状态
	 * @param remark
	 *            描述
	 * @return
	 */
	int updateOrderStatus(@Param("appid") String appid, @Param("orderId") String orderId,
			@Param("newStatus") int newStatus, @Param("oldStatus") int oldStatus, @Param("ifFinish") boolean ifFinish,
			@Param("repaymentStatus") Integer repaymentStatus,@Param("remark")  String remark);

	/**
	 * 获取订单详情（三方订单号码）
	 * 
	 * @param appid
	 * @param otherOrderId
	 * @return
	 */
	OrderLoanVo getOrderLoanVoByOtherOrderId(@Param("appid") String appid, @Param("otherOrderId") String otherOrderId);

	/**
	 * 获取订单详情（订单号码）
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderLoanVo getOrderLoanVoByOrderId(@Param("appid") String appid, @Param("orderId") String orderId);

	/**
	 * 获得订单详情
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderLoanBo getOrderLoanBo(@Param("appid") String appid, @Param("orderId") String orderId);

	/**
	 * 返回在途用户订单数
	 * 
	 * @param appid
	 * @param identityNo
	 * @return
	 */
	int countOnWayOrder(@Param("appid") String appid, @Param("identityNo") String identityNo);

	/**
	 * 逾期处理
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	int overdue(@Param("appid") String appid, @Param("orderId") String orderId);

}