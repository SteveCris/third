package com.shangyong.thorder.service;

import com.shangyong.thcore.dto.BankVerifyCodeDto;
import com.shangyong.thcore.dto.OrderBankDto;
import com.shangyong.thcore.vo.BankVerifyCodeVo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thorder.vo.OrderBindRuleVo;

public interface OrderBankService {

	/**
	 * 获取订单绑卡场景规则
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderBindRuleVo getOrderBindRuleVo(String appid, String orderId);

	/**
	 * 校验并获取 已绑定银行卡信息
	 * 
	 * @param appid
	 * @param orderId
	 * @param rule
	 * @param appName
	 * @param bankCardNo
	 * @return
	 */
	boolean canBind(String appid, String orderId, String rule, String appName,
			String bankCardNo);

	/**
	 * 获取订单 绑定银行卡
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	OrderBankVo getOrderBankVo(String appid, String orderId);

	/**
	 * 判断订单是否绑定银行卡
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	boolean checkOrderBankBind(String appid, String orderId);

	BankVerifyCodeVo getNewBankVerifyVo(String appid, String appName, String orderId, BankVerifyCodeDto bankVerifyCodeDto);

	/**
	 * 新的绑卡方法
	 * 
	 * @param appid
	 * @param orderId
	 * @param appName
	 * @param orderBankDto
	 * @return
	 */
	boolean bindNewBankCard(String appid, String orderId, String appName, OrderBankDto orderBankDto);

	/**
	 * 获取银行验证码对象
	 * 
	 * @param appid
	 * @param orderId
	 * @param bankVerifyCodeDto
	 * @return
	 */
	BankVerifyCodeVo getBankVerifyVo(String appid, String orderId, BankVerifyCodeDto bankVerifyCodeDto);

	/**
	 * 绑定银行卡
	 * 
	 * @param appid
	 * @param orderId
	 * @param orderBankDto
	 * @return
	 */
	boolean bindBankCard(String appid, String orderId, OrderBankDto orderBankDto);
}
