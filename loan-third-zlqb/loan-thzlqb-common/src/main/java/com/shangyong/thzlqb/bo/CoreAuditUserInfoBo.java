package com.shangyong.thzlqb.bo;

import java.io.Serializable;

public class CoreAuditUserInfoBo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户id
	private String userId;

	// 用户名称
	private String userName;

	// 身份证号码
	private String idCard;

	// 手机号
	private String userMobile;

	// 详细地址
	private String address;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
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
		builder.append("ZlqbUserInfoBo [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", idCard=");
		builder.append(idCard);
		builder.append(", userMobile=");
		builder.append(userMobile);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
