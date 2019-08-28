package com.shangyong.interact.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("绑卡支付标记对象")
public class BankVerifyCodeVo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8656467341924705220L;
	
	@ApiModelProperty(value = "支付标记", example = "dsfsdf",required=true)
	private String signNo;

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankVerifyCodeVo [signNo=");
		builder.append(signNo);
		builder.append("]");
		return builder.toString();
	}
	
	
}
