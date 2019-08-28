package com.shangyong.thzlqb.self.enums;

public enum MarriageEnum {
	// 婚姻状态 1：未婚 2：已婚无子女 3：已婚有子女 4：离异 5：丧偶 6：复婚 7:其他（备注）

	// SPINSTERHOOD-未婚、MARRIED-已婚、DIVORCED-离异、WIDOWED-丧偶、REMARRY-再婚、REMARRYFORMER-复婚

	MARRIAGEENUM1("1", "未婚", "SPINSTERHOOD", "未婚"), //
	MARRIAGEENUM2("2", "已婚无子女", "MARRIED", "已婚"), //
	MARRIAGEENUM3("3", "已婚有子女", "MARRIED", "已婚"), //
	MARRIAGEENUM4("4", "离异", "DIVORCED", "离异"), //
	MARRIAGEENUM5("5", "丧偶", "WIDOWED", "丧偶"), //
	MARRIAGEENUM6("6", "复婚", "REMARRYFORMER", "复婚"), //
	MARRIAGEENUM7("7", "其他（备注）", "MARRIED", "已婚"), //
	MARRIAGEENUM("0", "其他", "MARRIED", "已婚"), //
	;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private MarriageEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static MarriageEnum valueOfByCode(String code) {
		try {
			return MarriageEnum.valueOf(MarriageEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return MARRIAGEENUM;
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
