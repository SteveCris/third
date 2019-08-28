package com.shangyong.thcore.vo;

import java.io.Serializable;

public class RepaymentTypeVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8679916381482821988L;

	private String payType;
	
	private String state;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RepaymentTypeVo [payType=");
		builder.append(payType);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}


	
	
}
