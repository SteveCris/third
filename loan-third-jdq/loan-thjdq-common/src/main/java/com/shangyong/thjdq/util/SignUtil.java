package com.shangyong.thjdq.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {

	static String merchantKey = "SHANGYONGRISK";

	public static void main(String[] args) {

		Map<String, String> requsetMap = new HashMap<>();
		requsetMap.put("isPass", "1");
		requsetMap.put("customerId", "29707e2e5ecf4f53adaa12e0c400c422");
		requsetMap.put("serialNumber", "92d529651a864939b9d92e24819270ef");

		String sign = getSign(requsetMap, merchantKey);
		System.out.println("获取签名sign===============" + sign);

		System.out.println("验签是否通过===============" + checkSign(requsetMap, sign, merchantKey));
	}

	/**
	 * 验签
	 * 
	 * @param requestParamMap
	 *            请求的参数
	 * @param giveSign
	 *            传入的sign
	 * @return
	 */
	public static boolean checkSign(Map<String, String> requestParamMap, String giveSign, String merchantKey) {
		String sign = getSign(requestParamMap, merchantKey);
		return giveSign != null && giveSign.equals(sign);
	}

	/**
	 * 获取签名
	 * 
	 * @param param
	 *            参数Map
	 * @param merchantKey
	 *            商户密钥
	 * @return
	 */
	public static String getSign(Map<String, String> param, String merchantKey) {
		if (param == null) {
			throw new IllegalArgumentException("数据不能为空");
		}
		if (param.isEmpty()) {
			return null;
		}
		if (merchantKey == null) {
			throw new IllegalArgumentException("安全校验码数据不能为空");
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(param);
		if (sortedMap.containsKey("sign")) {
			sortedMap.remove("sign");
		}
		StringBuilder stringToSign = new StringBuilder();
		if (sortedMap.size() > 0) {
			for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
				if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
					stringToSign.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				}
			}
			stringToSign.deleteCharAt(stringToSign.length() - 1);
		}
		System.out.println("代签字符串:" + stringToSign);
		stringToSign.append(merchantKey);
		String signature = DigestUtils.md5Hex(stringToSign.toString());
		System.out.println("签名结果:" + signature);
		return signature;
	}
}
