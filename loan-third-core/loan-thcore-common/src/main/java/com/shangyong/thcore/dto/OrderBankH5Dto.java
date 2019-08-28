package com.shangyong.thcore.dto;

import java.io.Serializable;

public class OrderBankH5Dto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8717665345653105512L;

	private String appName;
	
	private String successReturnUrl;
	
	private String failReturnUrl;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSuccessReturnUrl() {
		return successReturnUrl;
	}

	public void setSuccessReturnUrl(String successReturnUrl) {
		this.successReturnUrl = successReturnUrl;
	}

	public String getFailReturnUrl() {
		return failReturnUrl;
	}

	public void setFailReturnUrl(String failReturnUrl) {
		this.failReturnUrl = failReturnUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBankH5Dto [appName=");
		builder.append(appName);
		builder.append(", successReturnUrl=");
		builder.append(successReturnUrl);
		builder.append(", failReturnUrl=");
		builder.append(failReturnUrl);
		builder.append("]");
		return builder.toString();
	}
	
	
}
