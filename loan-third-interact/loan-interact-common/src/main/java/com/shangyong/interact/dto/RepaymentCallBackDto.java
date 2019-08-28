package com.shangyong.interact.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 还款成功回调
 * 
 * @author caijunjun
 * @date 2019年3月26日
 */
public class RepaymentCallBackDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4627113300563631308L;

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
	 * 还款总金额
	 */
	private BigDecimal repayMoney;

	/**
	 * 主流程还款金额
	 */
	private BigDecimal repayLoanMoney;

	/**
	 * 保险还款金额
	 */
	private BigDecimal repayInsuranceMoney;

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

	public BigDecimal getRepayMoney() {
		return repayMoney;
	}

	public void setRepayMoney(BigDecimal repayMoney) {
		this.repayMoney = repayMoney;
	}

	public BigDecimal getRepayLoanMoney() {
		return repayLoanMoney;
	}

	public void setRepayLoanMoney(BigDecimal repayLoanMoney) {
		this.repayLoanMoney = repayLoanMoney;
	}

	public BigDecimal getRepayInsuranceMoney() {
		return repayInsuranceMoney;
	}

	public void setRepayInsuranceMoney(BigDecimal repayInsuranceMoney) {
		this.repayInsuranceMoney = repayInsuranceMoney;
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
		builder.append("RepaymentCallBackDto [state=");
		builder.append(state);
		builder.append(", applicationId=");
		builder.append(applicationId);
		builder.append(", statement=");
		builder.append(statement);
		builder.append(", repayMoney=");
		builder.append(repayMoney);
		builder.append(", repayLoanMoney=");
		builder.append(repayLoanMoney);
		builder.append(", repayInsuranceMoney=");
		builder.append(repayInsuranceMoney);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}

}
