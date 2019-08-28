package com.shangyong.interact.dto;

import java.io.Serializable;

/**
 * 放款成功回调
 * 
 * @author caijunjun
 * @date 2019年3月26日
 */
public class LoanCallBackDto implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8246305630393885283L;

	/**
	 * 放款结果 -1:失败 1:成功
	 */
	private String state;

	/**
	 * 订单申请id(现金白卡还款回调参数为手机号)
	 */
	private String applicationId;

	/**
	 * 账务系统ID
	 */
	private String statement;

	/**
	 * 备注信息
	 */
	private String remark;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoanCallBackDto [state=");
		builder.append(state);
		builder.append(", applicationId=");
		builder.append(applicationId);
		builder.append(", statement=");
		builder.append(statement);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}


}
