package com.shangyong.thcore.event.dto;

import java.io.Serializable;

/**
 * 事件用户信息
 * @author caijunjun
 * @date 2019年3月14日
 */
public class EventUserInfo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 身份证号码
	 */
	private String identityNo;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 手机身份证MD5
	 */
	private String phoneIdNumberMd5;
	
	/**
	 * 用户居住地址
	 */
	private String address;
	
	/**
	 * 来源订单id
	 */
	private String otherOrderId;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 扩展字段
	 */
	private String ext;
	
	/**
	 * 扩展字段2
	 */
	private String ext2;

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

	public String getPhoneIdNumberMd5() {
		return phoneIdNumberMd5;
	}

	public void setPhoneIdNumberMd5(String phoneIdNumberMd5) {
		this.phoneIdNumberMd5 = phoneIdNumberMd5;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOtherOrderId() {
		return otherOrderId;
	}

	public void setOtherOrderId(String otherOrderId) {
		this.otherOrderId = otherOrderId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventUserInfo [identityNo=");
		builder.append(identityNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", phoneIdNumberMd5=");
		builder.append(phoneIdNumberMd5);
		builder.append(", address=");
		builder.append(address);
		builder.append(", otherOrderId=");
		builder.append(otherOrderId);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", ext=");
		builder.append(ext);
		builder.append(", ext2=");
		builder.append(ext2);
		builder.append("]");
		return builder.toString();
	}



}
