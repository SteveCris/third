package com.shangyong.thcore.bo;

import java.io.Serializable;
import java.util.Date;

public class OrderAuditUserBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderAuditUserBo [createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
