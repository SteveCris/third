package com.shangyong.thryt.self.enums;

public enum LocalLiveEnum {
	LOCALLIVEENUM1("1", "半年以内", "", ""), //
	LOCALLIVEENUM2("2", "半年至一年", "", ""), //
	LOCALLIVEENUM3("3", "一年至上", "", ""), //
	LOCALLIVEENUM("0", "其他", "", ""), //
	;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private LocalLiveEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static LocalLiveEnum valueOfByCode(String code) {
		try {
			return LocalLiveEnum.valueOf(LocalLiveEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return LOCALLIVEENUM;
		}
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getToCode() {
		return toCode;
	}

	public String getToName() {
		return toName;
	}
}
