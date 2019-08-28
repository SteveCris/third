package com.shangyong.thcore.vo;

import java.io.Serializable;

/**
 * 订单对象
 * @author caijunjun
 * @date 2019年3月19日
 */
public class OrderLoanVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7310658340411943756L;

	// 订单id
	private String orderId;

	// 第三方订单id
	private String otherOrderId;

	// 订单状态
	private int status;

	// 是否完结
	private boolean ifFinish;

	// 还款状态
	private int repaymentStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOtherOrderId() {
		return otherOrderId;
	}

	public void setOtherOrderId(String otherOrderId) {
		this.otherOrderId = otherOrderId;
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

	public int getRepaymentStatus() {
		return repaymentStatus;
	}

	public void setRepaymentStatus(int repaymentStatus) {
		this.repaymentStatus = repaymentStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderLoanVo [orderId=");
		builder.append(orderId);
		builder.append(", otherOrderId=");
		builder.append(otherOrderId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", ifFinish=");
		builder.append(ifFinish);
		builder.append(", repaymentStatus=");
		builder.append(repaymentStatus);
		builder.append("]");
		return builder.toString();
	}

}
