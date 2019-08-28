package com.shangyong.thcore.bo;

import java.io.Serializable;
import java.util.Date;

public class OrderPreCreditBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5498120118953998504L;
	// 创建时间
	private Date createTime;
	// 授信申请单号
	private String creditUuid;
	// 产品编号
	private String productCode;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreditUuid() {
		return creditUuid;
	}

	public void setCreditUuid(String creditUuid) {
		this.creditUuid = creditUuid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderPreCreditBo [createTime=");
		builder.append(createTime);
		builder.append(", creditUuid=");
		builder.append(creditUuid);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append("]");
		return builder.toString();
	}

}
