package com.shangyong.thcore.vo;

import java.io.Serializable;

public class OrderBankVo implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户名称
	private String userName;
	
	// 卡类型(1借记卡2信用卡)
	private Integer cardType;

	// 银行名称
	private String bankName;

	// 银行代码
	private String bankCode;
	
	// 银行卡号
	private String bankCardNo;
	
	//预留手机号
	private String reservedMobile;
	
	//支付标记
	private String signNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getReservedMobile() {
		return reservedMobile;
	}

	public void setReservedMobile(String reservedMobile) {
		this.reservedMobile = reservedMobile;
	}

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBankVo [userName=");
		builder.append(userName);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bankCardNo=");
		builder.append(bankCardNo);
		builder.append(", reservedMobile=");
		builder.append(reservedMobile);
		builder.append(", signNo=");
		builder.append(signNo);
		builder.append("]");
		return builder.toString();
	}


}
