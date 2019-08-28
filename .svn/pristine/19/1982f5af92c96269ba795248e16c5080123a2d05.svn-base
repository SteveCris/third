package com.shangyong.thzlqb.self.enums;

public enum LinkmanEnum {
	//联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事
	
	// father 父亲、mother 母亲、spouse 配偶、child 子女、other_relative 其他亲属、friend 朋友、coworker
	// 同事、others 其他
	LINKMANENUM1("1", "父亲", "father", "父亲"), //
	LINKMANENUM2("2", "母亲", "mother", "母亲"), //
	LINKMANENUM3("3", "兄弟姐妹", "other_relative", "其他亲属"), //
	LINKMANENUM4("4", "子女", "child", "子女"), //
	LINKMANENUM5("5", "配偶", "spouse", "配偶"), //
	LINKMANENUM6("6", "其它亲属", "other_relative", "其他亲属"), //
	LINKMANENUM7("7", "朋友", "friend", "朋友"), //
	LINKMANENUM8("8", "同学", "others", "其他"), //
	LINKMANENUM9("9", "同事", "coworker", "同事"), //
	LINKMANENUM("0", "其他", "others", "其他"), //
	;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private LinkmanEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static LinkmanEnum valueOfByCode(String code) {
		try {
			return LinkmanEnum.valueOf(LinkmanEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return LINKMANENUM;
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
