package com.shangyong.thjdq.enums;

public enum CoreResultEnum {
	SUCCESS("200", "处理成功"), //
	FAILURE("500", "服务异常"), //
	ERROR("99999", "系统异常"), //

	/* 通用模块code码1000起 */
	COMMON_FAILURE("1000", "通用处理失败"), //
	CLICK_REPEAT("1001", "重复点击"), //
	PARAM_ERROR("1002", "参数校验异常"), //
	PARAM_MISSING_ERROR("1003", "参数缺少"), //
	NO_UPDATE("1012", "无需更新"), //

	; // 加注释，防止格式化格式

	private CoreResultEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 错误码
	 */
	private String code;
	/**
	 * 错误描述
	 */
	private String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public <T> CoreResult<T> with(T body) {
		return new CoreResult<>(this, body);
	}

	public <T> CoreResult<T> with() {
		return with((T) null);
	}

	public boolean isSuccess() {
		return SUCCESS.getCode().equals(getCode());
	}
}
