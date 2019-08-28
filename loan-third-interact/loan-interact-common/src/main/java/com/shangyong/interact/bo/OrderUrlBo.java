package com.shangyong.interact.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单链接查询对象")
public class OrderUrlBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "H5地址",example="http://www.baidu.com",required=true)
	private String h5Url;

	public String getH5Url() {
		return h5Url;
	}

	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderUrlBo [h5Url=");
		builder.append(h5Url);
		builder.append("]");
		return builder.toString();
	}


	
	

}
