package com.shangyong.thbase.contants;

public class RedisPrefix {

	private RedisPrefix() {

	}
	/**
	 * 重复点击
	 */
	public static final String LOCK_REPEAT = "LOCK_REPEAT:";

	/**
	 * 信贷中心配置 缓存前缀
	 */
	public static final String CONFIG_CENTER = "CONFIG:CENTER:";

	/**
	 * 产品配置 缓存前缀
	 */
	public static final String CONFIG_PRODUCT = "CONFIG:PRODUCT:";

	/**
	 * 参数配置 缓存前缀
	 */
	public static final String CONFIG_PARAM = "CONFIG:PARAM:";

	/**
	 * 参数集配置 缓存前缀
	 */
	public static final String CONFIG_PARAMS = "CONFIG:PARAMS:";

	/**
	 * 银行卡配置 缓存前缀
	 */
	public static final String CONFIG_BANK = "CONFIG:BANK:";

	/**
	 * 银行匹配前缀 缓存前缀
	 */
	public static final String CONFIG_MATCH = "CONFIG:MATCH:";
	
	/**
	 * 银行校验前缀 缓存前缀
	 */
	public static final String CONFIG_CHECK = "CONFIG:CHECK:";
	
}
