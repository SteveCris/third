package com.shangyong.interact.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单查询对象")
public class InteractOrderBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "交互系统订单id",example="IN154612315645",required=true)
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InteractOrderBo [orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}



}
