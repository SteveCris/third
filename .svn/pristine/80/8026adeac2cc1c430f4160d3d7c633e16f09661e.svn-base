package com.shangyong.interact.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单绑卡传输对象")
public class OrderBankDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "验证码", example = "625456",required=true)
	private String verifyCode;

	@ApiModelProperty(value = "支付标记", example = "2ddfrd",required=true)
	private String signNo;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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
		builder.append("OrderBankDto [verifyCode=");
		builder.append(verifyCode);
		builder.append(", signNo=");
		builder.append(signNo);
		builder.append("]");
		return builder.toString();
	}

}
