package com.shangyong.thzlqb.self.enums;

public enum WorkingAgeEnum {
	WORKINGAGEENUM1("1", "6~12个月", "2", "6~12个月"), //
	WORKINGAGEENUM2("2", "1~3年", "3", "1~3年"), //
	WORKINGAGEENUM3("3", "3~7年", "4", "3-7年"), //
	WORKINGAGEENUM("0", "0~6个月", "1", "0~5个月"), //
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
