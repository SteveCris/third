package com.shangyong.thryt.self.enums;

public enum EducationEnum {
	// 教育程度 1：硕士及以上 2：本科 3：大专 4：中专/高中以下
	
	// "1" : "博士", "2" : "硕士", "3" : "本科", "4" : "专科", "5" : "中专", "6" : "高中", "7" :
	// "职高", "8" : "初中及以下"
	EDUCATIONENUM1("1", "硕士及以上", "1", "博士"), //
	EDUCATIONENUM2("2", "本科", "3", "本科"), //
	EDUCATIONENUM3("3", "大专", "4", "大专"), //
	EDUCATIONENUM4("4", "中专/高中以下", "6", "高中"), //
	EDUCATIONENUM("0","其他","8","初中及以下"),
	;

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
