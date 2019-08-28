package com.shangyong.thryt.utils;

public class RytUtil {

	private RytUtil() {

	}

	// ##################融易推-平台方配置##############################

	public static String getRytCallbackUrl() {
		return PropertiesUtil.get("ryt.callback.url");
	}
	
	
	public static String getAesKey() {
		return PropertiesUtil.get("ryt.aes.key");
	}

	public static String getAesIv() {
		return PropertiesUtil.get("ryt.aes.iv");
	}

	public static String getSignKey() {
		return PropertiesUtil.get("ryt.sign.key");
	}

	public static String getPartner() {
		return PropertiesUtil.get("ryt.partner");
	}

	// ##################融易推-接入方配置###########################

	public static String getAppid() {
		return PropertiesUtil.get("ryt.common.appid");
	}

	public static int getAppName() {
		return PropertiesUtil.getInt("ryt.common.appName");
	}

	/**
	 * 需要引流的appid
	 * 
	 * @return
	 */
	public static String getToAppid() {
		return PropertiesUtil.get("ryt.common.to.appid");
	}

	/**
	 * 需要引流的appName
	 * 
	 * @return
	 */
	public static int getToAppName() {
		return PropertiesUtil.getInt("ryt.common.to.appName");
	}

	/**
	 * 获取用户customerId
	 * 
	 * @param idCard
	 * @return
	 */
	public static String getCustomerId(String idCard) {
		return getChannel() + idCard;
	}

	/**
	 * 获得申请单回调地址
	 * 
	 * @return
	 */
	public static String getAuditCallBackUrl() {
		return PropertiesUtil.get("ryt.common.audit.callbackUrl");
	}

	/**
	 * 获得应用版本信息
	 * 
	 * @return
	 */
	public static String getAppVersion() {
		// 1.0.0
		return PropertiesUtil.get("ryt.common.audit.appVersion");
	}

	/**
	 * 获得市场信息
	 * 
	 * @return
	 */
	public static String getAppMarket() {
		// API_RYT
		return PropertiesUtil.get("ryt.common.audit.appMarket");
	}

	/**
	 * 获得渠道信息
	 * 
	 * @return
	 */
	public static String getChannel() {
		// RYT
		return PropertiesUtil.get("ryt.common.audit.channel");
	}

	/**
	 * 获得运营商信息
	 * 
	 * @return
	 */
	public static String getMobileWebsite() {
		// moxie
		return PropertiesUtil.get("ryt.common.audit.mobileWebsite");
	}

	/**
	 * 获取接口协议链接
	 * 
	 * @return
	 */
	public static String getInterfaceProtocolUrl() {
		return PropertiesUtil.get("ryt.common.protocol.link");
	}

	/**
	 * 获取接口协议名称
	 * 
	 * @return
	 */
	public static String getInterfaceProtocolName() {
		return PropertiesUtil.get("ryt.common.protocol.name");
	}

	/**
	 * 获取绑卡地址
	 * @return
	 */
	public static String getBankBindUrl() {
		return PropertiesUtil.get("ryt.common.order.bind.url");
	}
	
	/**
	 * 获取处理成功跳转地址
	 * 
	 * @return
	 */
	public static String getSuccessUrl() {
		return PropertiesUtil.get("ryt.common.order.sUrl");
	}

	/**
	 * 获取处理失败跳转地址
	 * 
	 * @return
	 */
	public static String getFailureUrl() {
		return PropertiesUtil.get("ryt.common.order.fUrl");
	}

}
