package com.shangyong.interact.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单绑卡对象")
public class OrderBankVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名称", example = "李四",required=true)
	private String userName;

	@ApiModelProperty(value = "卡类型(1借记卡2信用卡)", example = "1",required=true)
	private Integer cardType;

	@ApiModelProperty(value = "银行名称", example = "工商银行",required=true)
	private String bankName;

	@ApiModelProperty(value = "银行code", example = "ICBC",required=true)
	private String bankCode;

	@ApiModelProperty(value = "银行卡号", example = "888228885252588",required=true)
	private String bankCardNo;

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
		builder.append("]");
		return builder.toString();
	}


}
