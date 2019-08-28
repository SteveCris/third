package com.shangyong.thryt.self.enums;

public enum JobTypeEnum {

	// 职业身份 1：企业主 2：个体户 3：工薪族 4：学生 5：自由职业 6：公务员 7：工人 8：农民
	JOBTYPEENUM1("1", "企业主", "", ""), //
	JOBTYPEENUM2("2", "个体户", "", ""), //
	JOBTYPEENUM3("3", "工薪族", "", ""), //
	JOBTYPEENUM4("4", "学生", "", ""), //
	JOBTYPEENUM5("5", "自由职业", "", ""), //
	JOBTYPEENUM6("6", "公务员", "", ""), //
	JOBTYPEENUM7("7", "工人", "", ""), //
	JOBTYPEENUM8("8", "农民", "", ""), //
	JOBTYPEENUM("0", "其他", "", ""), //
	;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private JobTypeEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static JobTypeEnum valueOfByCode(String code) {
		try {
			return JobTypeEnum.valueOf(JobTypeEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return JOBTYPEENUM;
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
