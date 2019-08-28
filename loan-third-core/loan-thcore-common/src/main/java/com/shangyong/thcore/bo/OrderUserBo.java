package com.shangyong.thcore.bo;

import java.io.Serializable;

public class OrderUserBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4682355869693365368L;

	private String identityNo;

	private String mobile;

	private String userName;

	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderUserBo [identityNo=");
		builder.append(identityNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
