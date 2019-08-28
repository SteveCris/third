package com.shangyong.interact.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("创建订单传输对象")
public class InteractOrderDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "第三方订单id",example="JDHF154612315645",required=true)
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
		builder.append("InteractOrderDto [otherOrderId=");
		builder.append(otherOrderId);
		builder.append("]");
		return builder.toString();
	}



	
}
