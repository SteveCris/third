package com.shangyong.interact.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("还款页面展示对象")
public class RepaymentShowVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "编号", example = "1", required = true)
	private String code;

	@ApiModelProperty(value = "描述信息", example = "描述", required = false)
	private String msg;

	@ApiModelProperty(value = "地址", example = "地址", required = true)
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RepaymentShowVo [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
