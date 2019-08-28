package com.shangyong.thryt.service;

import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.bo.RytOrderLinkBo;
import com.shangyong.thryt.bo.RytOrderSimpleBo;
import com.shangyong.thryt.entity.RytOrder;
import com.shangyong.thryt.vo.RytOrderSimpleVo;

public interface RytOrderService {

	/**
	 * 比较状态是否是期望值
	 * 
	 * @param orderNo
	 * @param expectStatus
	 * @return
	 */
	boolean checkStatus(String orderNo, int expectStatus);

	/**
	 * 修改订单状态
	 * @param orderNo
	 * @param newStatus
	 * @param oldStatus
	 * @param ifFinish
	 * @param remark
	 * @return
	 */
	boolean updateOrderStatus(String orderNo, int newStatus, int oldStatus, boolean ifFinish, String remark);

	/**
	 * 判断是否存在该订单信息
	 * 
	 * @param orderNo
	 * @return
	 */
	boolean isExist(String orderNo);

	/**
	 * 创建订单
	 * 
	 * @param rytOrder
	 * @return
	 */
	boolean create(RytOrder rytOrder);

	/**
	 * 获取订单信息
	 * 
	 * @param orderNo
	 * @return
	 */
	RytOrderBo getRytOrderBo(String orderNo);
	
	/**
	 * 获取订单信息（简要）
	 * @param orderNo
	 * @return
	 */
	RytOrderSimpleVo getRytOrderSimpleVo(String orderNo);

	/**
	 * 获取订单简要信息（通过orderId获取）
	 * @param orderId
	 * @return
	 */
	RytOrderSimpleBo getRytOrderSimpleBoByOrderId(String orderId);
	
	/**
	 * 获得订单环节信息
	 * @param orderNo
	 * @param newStatus
	 * @param oldStatus
	 * @return
	 */
	RytOrderLinkBo getRytOrderLinkBo(String orderNo,int newStatus,int oldStatus);
}
