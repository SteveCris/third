package com.shangyong.thbase.enums;

import com.shangyong.common.entity.RestResult;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "统一返回对象")
public class CoreResult<T> extends RestResult<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1311018632600485276L;

	public CoreResult() {
		super();
	}

	public CoreResult(CoreResultEnum coreResultEnum, T body) {
		super();
		super.setCode(coreResultEnum.getCode());
		super.setMessage(coreResultEnum.getMessage());
		super.setData(new Data<>(body));
	}

	/**
	 * 通用描述语句修改
	 * 
	 * @param message
	 * @return
	 */
	public CoreResult<T> withMessage(String message) {
		super.setMessage(message);
		return this;
	}

	/**
	 * 动态描述语句修改
	 * 
	 * @param dynaMessage
	 * @return
	 */
	public CoreResult<T> withDynaMessage(String dynaMessage) {
		super.setDynaMessage(dynaMessage);
		return this;
	}

	/**
	 * 获取扩展body
	 * 
	 * @param extendBody
	 * @return
	 */
	public CoreResult<T> withExtendBody(Object extendBody) {
		if (this.getData() == null) {
			return this;
		}
		this.getData().setExtendBody(extendBody);
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + "CoreResult []";
	}

}
