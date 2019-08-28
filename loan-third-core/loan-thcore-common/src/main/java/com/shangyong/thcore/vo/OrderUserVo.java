package com.shangyong.thcore.vo;

import java.io.Serializable;

public class OrderUserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7581536217457333068L;

	// 身份证
	private String identityNo;

	// 手机号
	private String mobile;

	// 名称
	private String userName;

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderUserVo [identityNo=");
		builder.append(identityNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}
	
	

}
