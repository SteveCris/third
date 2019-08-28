package com.shangyong.thcore.dto;

import java.io.Serializable;

public class OrderPreCreditDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5643179997699654586L;
	// 授信产品编号
	private String productCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBorrowPreDto [productCode=");
		builder.append(productCode);
		builder.append("]");
		return builder.toString();
	}

}
