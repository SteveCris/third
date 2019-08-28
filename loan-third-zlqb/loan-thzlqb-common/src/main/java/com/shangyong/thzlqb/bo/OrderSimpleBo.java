package com.shangyong.thzlqb.bo;

import java.io.Serializable;

public class OrderSimpleBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;

	private String orderNo;

	private int status;

	private int isSign;

	private boolean ifFinish;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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

	public boolean isIfFinish() {
		return ifFinish;
	}

	public void setIfFinish(boolean ifFinish) {
		this.ifFinish = ifFinish;
	}

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderSimpleBo [orderId=");
		builder.append(orderId);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", status=");
		builder.append(status);
		builder.append(", ifFinish=");
		builder.append(ifFinish);
		builder.append("]");
		return builder.toString();
	}

}
