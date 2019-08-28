package com.shangyong.thcore.dto;

import java.io.Serializable;

public class CheckUserInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5776911297715025103L;
	// 身份证前缀
	private String identityNoPrefix;
	// 手机号前缀
	private String mobilePrefix;
	// 用户名称
	private String userName;
	// 身份证号+手机号 大写 md5签名
	private String signMd5;
	// 应用appid
	private String appid;
	// 0默认方式匹配1身份证明文匹配
	private int type;
	// 身份证明文
	private String identityNo;

	public String getIdentityNoPrefix() {
		return identityNoPrefix;
	}

	public void setIdentityNoPrefix(String identityNoPrefix) {
		this.identityNoPrefix = identityNoPrefix;
	}

	public String getMobilePrefix() {
		return mobilePrefix;
	}

	public void setMobilePrefix(String mobilePrefix) {
		this.mobilePrefix = mobilePrefix;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSignMd5() {
		return signMd5;
	}

	public void setSignMd5(String signMd5) {
		this.signMd5 = signMd5;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CheckUserInfoDto [identityNoPrefix=");
		builder.append(identityNoPrefix);
		builder.append(", mobilePrefix=");
		builder.append(mobilePrefix);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", signMd5=");
		builder.append(signMd5);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", identityNo=");
		builder.append(identityNo);
		builder.append("]");
		return builder.toString();
	}

}
