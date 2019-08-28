package com.shangyong.interact.contants;

public class RedisPrefix {

	private RedisPrefix() {

	}

	/**
	 * 重复点击
	 */
	public static final String LOCK_REPEAT = "LOCK_REPEAT:";

	
	//***********************以下不使用******************
	/**
	 * 获取验证码接口 防爆点击
	 */
	public static final String REPEAT_VERIFYCODE = LOCK_REPEAT + "V:";

	/**
	 * 银行卡绑定接口 防爆点击
	 */
	public static final String REPEAT_BIND = LOCK_REPEAT + "B:";

	/**
	 * 前置授信接口 防爆点击
	 */
	public static final String REPEAT_PRE_CREDIT = LOCK_REPEAT + "P_C:";

	/**
	 * 后置授信接口 防爆点击
	 */
	public static final String REPEAT_CREDIT = LOCK_REPEAT + "C:";

	/**
	 * 一键还款接口 防爆点击
	 */
	public static final String REPEAT_ONEKEY_REPAYMENT = LOCK_REPEAT + "O_R:";

	/**
	 * 快捷支付还款接口 防爆点击
	 */
	public static final String REPEAT_QUICK_REPAYMENT = LOCK_REPEAT + "Q_R:";

	/**
	 * 微信支付还款接口 防爆点击
	 */
	public static final String REPEAT_WECHAT_REPAYMENT = LOCK_REPEAT + "W_R:";
	
	//*****************************缓存************************************
	
	public static final String CONFIG_BASE = "CONFIG:OBASE:";
	
	public static final String CONFIG_SCENE = "CONFIG:OSCENE:";
	
	public static final String CONFIG_SCENED = "CONFIG:OSCENED:";
	

}
