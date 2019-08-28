package com.shangyong.interact.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("认证审批回调对象")
public class AuditCallBackDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "处理结果",example="1",required=true)
	private int result;
	
	@ApiModelProperty(value = "申请编号",example="sxgfs22512651655",required=true)
	private String applyId;
	
	@ApiModelProperty(value = "申请单用户名称",example="李四",required=true)
	private String userName;
	
	@ApiModelProperty(value = "申请单用户身份证号码",example="331081199220202020",required=true)
	private String identityNo;
	
	@ApiModelProperty(value = "申请单用户手机号",example="18310606060",required=true)
	private String mobile;
	
	@ApiModelProperty(value = "申请单用户身份证地址",example="浙江杭州滨江滨康小区",required=true)
	private String address;
	
	@ApiModelProperty(value = "申请单用户性别",example="1",required=true)
	private int sex;
	
	@ApiModelProperty(value = "申请单用户年龄",example="23",required=true)
	private int age;
	
	@ApiModelProperty(value = "申请单用户民族",example="汉",required=true)
	private String nation;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditCallBackDto [result=");
		builder.append(result);
		builder.append(", applyId=");
		builder.append(applyId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", identityNo=");
		builder.append(identityNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", address=");
		builder.append(address);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", age=");
		builder.append(age);
		builder.append(", nation=");
		builder.append(nation);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
