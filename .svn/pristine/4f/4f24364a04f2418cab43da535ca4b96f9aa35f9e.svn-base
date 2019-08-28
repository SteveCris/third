package com.shangyong.thorder.service;

import com.shangyong.thcore.vo.OrderUserVo;

public interface OrderUserService {

	/**
	 * 获取订单用户信息
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderUserVo getOrderUserVo(String appid, String orderId);

	/**
	 * 校验是不是老用户（存在还款成功订单）
	 * 
	 * @param appid
	 * @param identityNo
	 * @return
	 */
	boolean checkOlder(String appid, String identityNo);
}
