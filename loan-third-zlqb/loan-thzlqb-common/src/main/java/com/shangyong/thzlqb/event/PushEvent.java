package com.shangyong.thzlqb.event;

import java.io.Serializable;

public class PushEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PushEvent [orderNo=");
		builder.append(orderNo);
		builder.append("]");
		return builder.toString();
	}

}
