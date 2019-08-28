package com.shangyong.thorder.vo;

import java.io.Serializable;

public class OrderBindRuleVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 绑定场景规则参数
	private String rule;

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBindRuleVo [rule=");
		builder.append(rule);
		builder.append("]");
		return builder.toString();
	}

}
