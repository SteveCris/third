package com.shangyong.thorder.bo;

import java.io.Serializable;
import java.util.Date;

public class OrderAuditExpireBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appid;
	
	private String orderId;
	
	private int status;
	
	private Date createTime;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderAuditExpireBo [appid=");
		builder.append(appid);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
