package com.shangyong.thorder.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SignUtil {

	private SignUtil() {

	}

	public static String toMd5Upper(String str) {
		return toMd5(str).toUpperCase();
	}

	public static String toMd5(String str) {
		return DigestUtils.md5Hex(str);
	}
}
