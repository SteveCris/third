package com.shangyong.thjdq.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UUIDUtils {

	private UUIDUtils() {

	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return new String[] {};
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

	public static String getUUID20() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		Random ne = new Random();
		int x = ne.nextInt(999 - 100 + 1) + 100;
		return String.valueOf(dateFormater.format(date)) + getUUID().substring(0, 5) + String.valueOf(x);
	}

}
