package com.shangyong.thryt.self.enums;

/**
 * 行业类型
 * 
 * @author caijunjun
 * @date 2019年5月31日
 */
public enum JobCategoryEnum {
	// 行业类型 1：商业、服务业人员 2：专业技术人员 3：办事人员、文员、行政等有关人员 4：工厂、生产、运输设备操作人员 5：农、林、牧、渔、水利业生产人员
	// 6：前线销售人员 7：国家机关、企事业单位管理人员 8：军人

	JOBCATEGORYENUM1("1", "商业、服务业人员", "1004708", "生活服务"), //
	JOBCATEGORYENUM2("2", "专业技术人员", "1004007", "学术/科研"), //
	JOBCATEGORYENUM3("3", "办事人员、文员、行政等有关人员", "1005811", "多元化集团"), //
	JOBCATEGORYENUM4("4", "工厂、生产、运输设备操作人员", "1002103", "机械/设备/重工"), //
	JOBCATEGORYENUM5("5", "农、林、牧、渔、水利业生产人员", "1005711", "农/林/牧/渔"), //
	JOBCATEGORYENUM6("6", "前线销售人员", "1003407", "中介服务"), //
	JOBCATEGORYENUM7("7", "国家机关、企事业单位管理人员", "1005511", "政府/公共事业/非盈利机构"), //
	JOBCATEGORYENUM8("8", "军人", "1005511", "政府/公共事业/非盈利机构"), //
	JOBCATEGORYENUM("0", "其他", "1006022", "其他"),;//
	private String code;
	private String name;
	private String toCode;
	private String toName;

	private JobCategoryEnum(String code, String name, String toCode, String toName) {
		this.code = code;
		this.name = name;
		this.toCode = toCode;
		this.toName = toName;
	}

	public static JobCategoryEnum valueOfByCode(String code) {
		try {
			return JobCategoryEnum.valueOf(JobCategoryEnum.class.getSimpleName().toUpperCase() + code);
		} catch (Exception e) {
			return JOBCATEGORYENUM;
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
