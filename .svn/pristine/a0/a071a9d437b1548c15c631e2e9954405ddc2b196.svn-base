package com.shangyong.interact.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("绑卡匹配对象")
public class BankMatchVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "银行名称", example = "工商银行",required=true)
    private String bankName;
	
	@ApiModelProperty(value = "银行code", example = "ICBC",required=true)
    private String bankCode;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankMatchVo [bankName=");
		builder.append(bankName);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append("]");
		return builder.toString();
	}


	
	

}
