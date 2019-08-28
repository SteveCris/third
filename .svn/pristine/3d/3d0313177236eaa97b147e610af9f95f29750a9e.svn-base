package com.shangyong.thzlqb.enums;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "统一返回对象")
public class ZlqbResult<T> extends ZlqbRestResult<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1311018632600485276L;

	public ZlqbResult() {
		super();
	}

	public ZlqbResult(ZlqbResultEnum zlqbResultEnum, T body) {
		super();
		super.setCode(zlqbResultEnum.getCode());
		super.setMsg(zlqbResultEnum.getMessage());
		super.setResponse(body);
	}

	/**
	 * 通用描述语句修改
	 * 
	 * @param message
	 * @return
	 */
	public ZlqbResult<T> withMessage(String message) {
		super.setMsg(message);
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + "ZlqbResult []";
	}

}
