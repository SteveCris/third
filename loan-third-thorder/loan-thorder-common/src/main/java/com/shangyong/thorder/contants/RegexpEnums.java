package com.shangyong.thorder.contants;

import com.shangyong.common.utils.StringUtil;

/**
 * 正则常量 参考 http://tool.chinaz.com/regex
 * 
 * @author caijunjun
 * @date 2018年6月27日
 */
public enum RegexpEnums {

	ID_CARD() {
		@Override
		public boolean match(String str) {
			return StringUtil.matcherCaseInsensitive(ID_CARD_STR, str).matches();
		}
	},

	MOBILE() {
		@Override
		public boolean match(String str) {
			return StringUtil.matcherCaseInsensitive(MOBILE_STR, str).matches();
		}
	},
	EMAIL() {
		@Override
		public boolean match(String str) {
			return StringUtil.matcherCaseInsensitive(EMAIL_STR, str).matches();
		}
	},
	NUMBER() {
		@Override
		public boolean match(String str) {
			return StringUtil.matcherCaseInsensitive(NUMBER_STR, str).matches();
		}
	};

	//
	/**
	 * 身份证正则
	 */
	public static final String ID_CARD_STR = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
	public static final String ID_CARD_MESSAGE = "身份证格式不正确";

	/**
	 * 手机号码
	 */
	public static final String MOBILE_STR = "^1[3-9][0-9]{9}$";
	public static final String MOBILE_MESSAGE = "手机号码格式不正确（国内）";

	/**
	 * 邮箱
	 */
	public static final String EMAIL_STR = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	public static final String EMAIL_MESSAGE = "邮箱地址格式不正确";

	/**
	 * 数字型
	 */
	public static final String NUMBER_STR = "[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*";
	public static final String NUMBER_MESSAGE = "数字类型格式不正确";

	
	
	public abstract boolean match(String str);
}
