package com.shangyong.thzlqb.enums;

public enum ZlqbResultEnum {
	SUCCESS("200", "处理成功"), //
	FAILURE("500", "处理失败"), //
	CALF_ERROR("9999", "系统自定义异常"), //
	ERROR("99999", "系统异常"),
	DB_FAILURE("10001", "DB添加异常"), ////

	COMMON_FAILURE("1000", "通用处理失败"), //
	CLICK_REPEAT("1001", "重复点击"), //
	PARAM_ERROR("1002", "参数校验异常"), //
	PARAM_MISSING_ERROR("1003", "参数缺少"), //
	NULL_ERROR("1004", "空异常"), //
	UNSUPPORTED_ENCODING("1005", "不支持的编码异常"), //
	SIGN_ERROR("1006", "签名异常"), //
	NO_CHANNEL("1007", "不存在的渠道"), //
	IO_EXCEPTION("1008", "文件流操作异常"), //
	MONGODB_UPDATE_ERROR("1009", "mongodb更新失败"), //
	REMOTE_ERROR("1010", "调用远程接口异常"), //
	SAVE_ERROR("1011", "保存异常"), //
	THIRD_REMOTE_ERROR("1012", "调用第三方平台远程接口异常"),
	MONGODB_SEARCH_NULL("1013", "mongodb查询为空"),
	IDCARD_AGE("1014", "年龄不符合"), ////
    IDCARD_AREAEROR("1015", "地区不符合"),
	UPLOAD_OPERATOR_ERROR("1016", "上传运营商数据出错"),
	TIECARD_IS_TIECARD("1017", "H5已绑卡"),
    ; // 加注释，防止格式化格式

	private ZlqbResultEnum(String code, String message) {
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

	public <T> ZlqbResult<T> with(T body) {
		return new ZlqbResult<>(this, body);
	}

	public <T> ZlqbResult<T> with() {
		return with((T) null);
	}
}
