package com.shangyong.thzlqb.self.enums;

public enum EducationEnum {
	EDUCATIONENUM1("1", "高中及以下", "4", "中专/中及以下"), //
	EDUCATIONENUM2("2", "大专", "3", "大专"), //
	EDUCATIONENUM3("3", "本科", "2", "本科"), //
	EDUCATIONENUM4("4", "硕士及以上", "1", "硕及以上"), //
	EDUCATIONENUM("0", "其他", "4", "中专/中及以下"),;

	private String code;
	private String name;
	private String toCode;
	private String toName;

	private EducationEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static EducationEnum valueOfByCode(String code) {
		try {
			return EducationEnum.valueOf(EducationEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return EDUCATIONENUM;
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
