package com.shangyong.thcore.dto;

import java.io.Serializable;

public class OrderLoanDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7471972140977550307L;

	// 第三方订单id
	private String otherOrderId;

	public String getOtherOrderId() {
		return otherOrderId;
	}

	public void setOtherOrderId(String otherOrderId) {
		this.otherOrderId = otherOrderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderLoanDto [otherOrderId=");
		builder.append(otherOrderId);
		builder.append("]");
		return builder.toString();
	}


	
}
