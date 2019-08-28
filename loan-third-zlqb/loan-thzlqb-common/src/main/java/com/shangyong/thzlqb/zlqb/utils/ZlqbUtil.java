package com.shangyong.thzlqb.zlqb.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.JacksonUtil;
import com.shangyong.loan.ext.util.JsonNodeUtil;
import com.shangyong.loan.ext.util.PropertiesUtil;

public class ZlqbUtil {

	private ZlqbUtil() {

	}

	public static ObjectNode checkAndGetRequest(ObjectNode node) {
		String appid = node.get("appId").asText();
		if (!appid.equals(getThirdAppid())) {
			throw new CalfException("appid:" + appid + " 校验异常");
		}
		return JacksonUtil.build().parseFrom(AesUtil.decrypt(node.get("request").asText(), getThirdAesKey()),
				ObjectNode.class);

	}

	public static ObjectNode buildRequest(Object request) {
		return JsonNodeUtil.data()//
				.put("appId", getThirdAppid())//
				.put("request", AesUtil.encrypt(JacksonUtil.build().parseToJsonString(request), getThirdAesKey()));//
	}

	// ***************************私有方法**************************

	public static String getAppid() {
		return PropertiesUtil.get("zlqb.common.appid");
	}

	public static int getAppName() {
		return PropertiesUtil.getInt("zlqb.common.appName");
	}
	
	public static String getChannel() {
		return PropertiesUtil.get("zlqb.common.channel");
	}
	

	public static String getThirdAesKey() {
		return PropertiesUtil.get("zlqb.third.aesKey");
	}

	public static String getThirdAppid() {
		return PropertiesUtil.get("zlqb.third.appid");
	}

	public static String getCallbackUrl() {
		return PropertiesUtil.get("zlqb.third.callbackUrl");
	}

	public static boolean checkToken(String token) {
		return PropertiesUtil.get("zlqb.common.check.token").equals(token);
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
		return PropertiesUtil.get("zlqb.common.audit.callbackUrl");
	}

	/**
	 * 获得应用版本信息
	 *
	 * @return
	 */
	public static String getAppVersion() {
		// 1.0.0
		return PropertiesUtil.get("zlqb.common.audit.appVersion");
	}

	/**
	 * 获得市场信息
	 *
	 * @return
	 */
	public static String getAppMarket() {
		// API_RYT
		return PropertiesUtil.get("zlqb.common.audit.appMarket");
	}


	/**
	 * 获得运营商信息
	 *
	 * @return
	 */
	public static String getMobileWebsite() {
		// moxie
		return PropertiesUtil.get("zlqb.common.audit.mobileWebsite");
	}

	/**
	 * 获取绑卡成功跳转第三方地址
	 * @return
	 */
	public static String getBankBindSuccessUrl() {
		return PropertiesUtil.get("zlqb.common.order.bind.success.url");
	}

	public static String getCancelBankUrl() {
		return PropertiesUtil.get("zlqb.third.cancelBankUrl");
	}
}
