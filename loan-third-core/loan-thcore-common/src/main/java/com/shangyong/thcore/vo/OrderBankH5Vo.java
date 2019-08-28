package com.shangyong.thcore.vo;

import java.io.Serializable;

public class OrderBankH5Vo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3766333565050748021L;

	// 1、已绑卡2、没有绑卡
	private int status;

	// 获取H5借款地址
	private String h5Url;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getH5Url() {
		return h5Url;
	}

	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBankH5Vo [status=");
		builder.append(status);
		builder.append(", h5Url=");
		builder.append(h5Url);
		builder.append("]");
		return builder.toString();
	}

}
