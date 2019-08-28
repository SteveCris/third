package com.shangyong.interact.enums;

public enum CoreResultEnum {
	SUCCESS("200", "处理成功"), //
	FAILURE("500", "处理失败"), //
	ERROR("99999", "系统异常"), //

	COMMON_FAILURE("1000", "通用处理失败"), //
	CLICK_REPEAT("1001", "请不要重复点击"), //
	PARAM_ERROR("1002", "参数校验异常"), //
	PARAM_MISSING_ERROR("1003", "参数缺少"), //
	NULL_ERROR("1004", "空异常"), //
	AUTH_ERROR("1005", "没有权限"), //


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
}
