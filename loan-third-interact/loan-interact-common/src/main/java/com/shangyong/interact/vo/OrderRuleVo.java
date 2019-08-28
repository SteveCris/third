package com.shangyong.interact.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("规则对象")
public class OrderRuleVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3213984353076206435L;

	@ApiModelProperty(value = "合同规则对象", example = "2,4",required=true)
	private String rule;
	
	@ApiModelProperty(value = "场景规则对象", example = "2,1",required=true)
	private String sceneRule;

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getSceneRule() {
		return sceneRule;
	}

	public void setSceneRule(String sceneRule) {
		this.sceneRule = sceneRule;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderRuleVo [rule=");
		builder.append(rule);
		builder.append(", sceneRule=");
		builder.append(sceneRule);
		builder.append("]");
		return builder.toString();
	}


	
}
