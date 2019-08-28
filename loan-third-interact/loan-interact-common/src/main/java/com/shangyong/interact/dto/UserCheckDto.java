package com.shangyong.interact.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("脱敏用户校验对象")
public class UserCheckDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "身份证号码（允许脱敏）",example="3310811993021*****",required=true)
	private String identityNo;
	
	@ApiModelProperty(value = "手机号码（允许脱敏）",example="1831021****",required=true)
	private String mobile;
	
	@ApiModelProperty(value = "用户名称",example="李四",required=true)
	private String userName;
	
	@ApiModelProperty(value = "身份证+手机号进行md5 hash",example="dsfGC4GhDgfdg2sdf51g5a",required=true)
	private String signMd5;

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

	public String getSignMd5() {
		return signMd5;
	}

	public void setSignMd5(String signMd5) {
		this.signMd5 = signMd5;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserCheckDto [identityNo=");
		builder.append(identityNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", signMd5=");
		builder.append(signMd5);
		builder.append("]");
		return builder.toString();
	}
	
	
}
