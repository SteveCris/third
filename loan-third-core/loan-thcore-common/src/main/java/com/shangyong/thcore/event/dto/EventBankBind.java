package com.shangyong.thcore.event.dto;

import java.io.Serializable;

public class EventBankBind implements Serializable {


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
	private String reservedMobile;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 银行卡号
	 */
	private String bankCardNo;

	/**
	 * 银行代码
	 */
	private String bankCode;

	/**
	 * 银行名称
	 */
	private String bankName;

	/**
	 * 卡类别
	 */
	private int cardType;
	
	/**
	 * 支付标记
	 */
	private String signNo;
	
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

	public String getReservedMobile() {
		return reservedMobile;
	}

	public void setReservedMobile(String reservedMobile) {
		this.reservedMobile = reservedMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
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
		builder.append("EventBankBind [identityNo=");
		builder.append(identityNo);
		builder.append(", reservedMobile=");
		builder.append(reservedMobile);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", bankCardNo=");
		builder.append(bankCardNo);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", signNo=");
		builder.append(signNo);
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
