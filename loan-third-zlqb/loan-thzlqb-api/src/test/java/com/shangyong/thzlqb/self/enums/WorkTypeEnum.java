package com.shangyong.thzlqb.self.enums;

public enum WorkTypeEnum {

	// 职业身份 1：企业主 2：个体户 3：工薪族 4：学生 5：自由职业 6：公务员 7：工人 8：农民
	WORKTYPEENUM1("1", "工薪族", "3", "上班群"), //
	WORKTYPEENUM2("2", "企业主", "1", "企业主"), //
	WORKTYPEENUM3("3", "学生", "4", "学生"), //
	WORKTYPEENUM4("4", "自由职业者", "5", "无固定职业"), //
	WORKTYPEENUM("0", "其他", "5", "无固定职业"), //
	;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private WorkTypeEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static WorkTypeEnum valueOfByCode(String code) {
		try {
			return WorkTypeEnum.valueOf(WorkTypeEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return WORKTYPEENUM;
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
