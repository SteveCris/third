package com.shangyong.interact.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("获取银行验证码传输对象")
public class BankVerifyCodeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "银行卡号", example = "88888888888",required=true)
	private String bankCardNo;

	@ApiModelProperty(value = "银行预留手机号", example = "18310600000",required=true)
	private String reservedMobile;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankVerifyCodeDto [bankCardNo=");
		builder.append(bankCardNo);
		builder.append(", reservedMobile=");
		builder.append(reservedMobile);
		builder.append("]");
		return builder.toString();
	}

}
