package com.shangyong.thryt.event;

import java.io.Serializable;

public class RytPushEvent implements Serializable {

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
		builder.append("RytPushTimeEvent [orderNo=");
		builder.append(orderNo);
		builder.append("]");
		return builder.toString();
	}

}
