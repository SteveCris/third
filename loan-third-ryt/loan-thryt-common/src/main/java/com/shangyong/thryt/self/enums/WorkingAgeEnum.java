package com.shangyong.thryt.self.enums;

public enum WorkingAgeEnum {
	WORKINGAGEENUM1("1", "0~5个月", "", ""), //
	WORKINGAGEENUM2("2", "6~12个月", "", ""), //
	WORKINGAGEENUM3("3", "1~2年", "", ""), //
	WORKINGAGEENUM4("4", "3~4年", "", ""), //
	WORKINGAGEENUM5("5", "5年以上", "", ""), //
	WORKINGAGEENUM("0", "其他", "", ""), //
	;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private WorkingAgeEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static WorkingAgeEnum valueOfByCode(String code) {
		try {
			return WorkingAgeEnum.valueOf(WorkingAgeEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return WORKINGAGEENUM;
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
