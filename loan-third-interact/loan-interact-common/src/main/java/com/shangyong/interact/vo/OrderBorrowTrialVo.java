package com.shangyong.interact.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("借款试算对象")
public class OrderBorrowTrialVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "场景有效期至", example = "2018-9-9 12:00:01", required = true)
	private Date preCreateTime;

	@ApiModelProperty(value = "还款计划对象", required = true)
	private OrderRepaymentPlanVo orderRepaymentPlanVo;

	public Date getPreCreateTime() {
		return preCreateTime;
	}

	public void setPreCreateTime(Date preCreateTime) {
		this.preCreateTime = preCreateTime;
	}

	public OrderRepaymentPlanVo getOrderRepaymentPlanVo() {
		return orderRepaymentPlanVo;
	}

	public void setOrderRepaymentPlanVo(OrderRepaymentPlanVo orderRepaymentPlanVo) {
		this.orderRepaymentPlanVo = orderRepaymentPlanVo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBorrowTrialVo [preCreateTime=");
		builder.append(preCreateTime);
		builder.append(", orderRepaymentPlanVo=");
		builder.append(orderRepaymentPlanVo);
		builder.append("]");
		return builder.toString();
	}

}
