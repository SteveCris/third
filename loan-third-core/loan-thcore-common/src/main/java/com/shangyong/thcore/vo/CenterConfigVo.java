package com.shangyong.thcore.vo;

import java.io.Serializable;

public class CenterConfigVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4513790262819701650L;

	private String appid;

	private String appsecret;

	private String environment;

	private String baseUrl;

	private String tokenKey;

	private String subTokenKey;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getSubTokenKey() {
		return subTokenKey;
	}

	public void setSubTokenKey(String subTokenKey) {
		this.subTokenKey = subTokenKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CenterConfigVo [appid=");
		builder.append(appid);
		builder.append(", appsecret=");
		builder.append(appsecret);
		builder.append(", environment=");
		builder.append(environment);
		builder.append(", baseUrl=");
		builder.append(baseUrl);
		builder.append(", tokenKey=");
		builder.append(tokenKey);
		builder.append(", subTokenKey=");
		builder.append(subTokenKey);
		builder.append("]");
		return builder.toString();
	}

}
