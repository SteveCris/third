package com.shangyong.thryt.enums;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "统一返回对象")
public class RytResult<T> extends RytRestResult<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1311018632600485276L;

	public RytResult() {
		super();
	}

	public RytResult(RytResultEnum rytResultEnum, T body) {
		super();
		super.setCode(rytResultEnum.getCode());
		super.setMsg(rytResultEnum.getMessage());
		super.setData(body);
	}

	/**
	 * 通用描述语句修改
	 * 
	 * @param message
	 * @return
	 */
	public RytResult<T> withMessage(String message) {
		super.setMsg(message);
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + "CoreResult []";
	}

}
