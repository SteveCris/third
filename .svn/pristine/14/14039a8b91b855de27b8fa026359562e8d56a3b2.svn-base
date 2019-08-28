package com.shangyong.thzlqb.enums;

/**
 * 行业类型
 * 
 * @author caijunjun
 * @date 2019年5月31日
 */
public enum ZlqbJobCategoryEnum {
	INTERNET(0,"互联网","1000101","互联网/电子商务"),
	BUILDER(1,"房地产/建筑","1003206","房地产/建筑/建材/工程"),
	SERVER(2,"服务业","1004708","生活服务"),
	TRADE(3,"贸易批发","1001503","批发/零售"),
	FINANCIAL(4,"金融","1001002","金融/投资/证券/基金"),
	EDUCATION(5,"教育艺术","1003907","教育/培训/院校"),
	MEDICINE(6,"医药卫生","1002404","医疗/护理/卫生"),
	ENTERTAINMENT(7,"文化传媒","1002905","文字媒体/出版"),
	ELECTRONIC(8,"电子电功","1005210","电气/电力/水利"),
	OTHER(9,"其它","1006022","其他");
	private int code;
	private String name;
	private String toCode;
	private String toName;

	private ZlqbJobCategoryEnum(int code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static ZlqbJobCategoryEnum valueOfByCode(int code) {
		try {
			return ZlqbJobCategoryEnum.valueOf(ZlqbJobCategoryEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return OTHER;
		}
	}

	public int getCode() {
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
