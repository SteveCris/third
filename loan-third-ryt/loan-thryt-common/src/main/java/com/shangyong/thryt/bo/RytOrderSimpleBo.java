package com.shangyong.thryt.bo;

import java.io.Serializable;

public class RytOrderSimpleBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderNo;

	private int status;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RytOrderSimpleBo [orderNo=");
		builder.append(orderNo);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
