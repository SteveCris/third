package com.shangyong.thorder.utils;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.shangyong.common.utils.LocalDateUtil;

public class CheckUtil {

	private CheckUtil() {

	}

	public static boolean checkIsOverdue(Date repaymentPlanTime) {
		return !new Date().before(LocalDateUtil.plus(repaymentPlanTime, 1, ChronoUnit.DAYS));
	}

	
	public static boolean checkToken(String token) {
		return PropertiesUtil.get("check.token").equals(token);
	}
}
