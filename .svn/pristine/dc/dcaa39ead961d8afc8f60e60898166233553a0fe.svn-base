package com.shangyong.thryt.ryt.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.exception.CalfException;
import com.shangyong.thryt.utils.IdUtil;
import com.shangyong.thryt.utils.JacksonUtil;
import com.shangyong.thryt.utils.JsonNodeUtil;
import com.shangyong.thryt.utils.RytUtil;

/**
 * 签名工具类
 * 
 * @author caijunjun
 * @date 2019年5月28日
 */
public class SignUtil {

	private static AES aes = new AES();

	private static String aesKey;
	private static String aesIv;

	private static String signKey;
	private static String signParam;

	private static String partner;

	static {
		aesKey = RytUtil.getAesKey();
		aesIv = RytUtil.getAesIv();
		signKey = RytUtil.getSignKey();
		partner = RytUtil.getPartner();
		signParam = "sign";
	}

	private SignUtil() {

	}

	/**
	 * 校验并返回data对象
	 * 
	 * @param objectNode
	 * @return
	 */
	public static ObjectNode checkAndGetData(ObjectNode objectNode) {
		// 验签
		if (!checkSign(objectNode)) {
			throw new CalfException(RytResultEnum.SIGN_ERROR);
		}
		// 解密
		return JacksonUtil.parseToObjectNodeWithSnakeCase(decrypt(objectNode.get("data").asText()));
	}

	/**
	 * 创建签名请求对象
	 * 
	 * @param data
	 * @param channel
	 * @return
	 */
	public static ObjectNode buildSignRequest(Object data, String channel) {
		ObjectNode node = JsonNodeUtil.data()//
				.put("channel", channel)//
				.put("data", encrypt(JacksonUtil.parseToJsonStringWithSnakeCase(data)))//
				.put("partner", partner)//
				.put("random", IdUtil.getUuid(32))//
				.put("timestamp", System.currentTimeMillis())//
				.put("token", IdUtil.getUuid(32))//
		;
		node.put("sign", createSign(node, signKey));
		return node;
	}

	/*********************************** 私有方法 ************************************/
	private static String encrypt(String data) {
		// aes加密
		byte[] bytesAes = aes.encrypt(data.getBytes(), aesKey.getBytes(), aesIv.getBytes());
		// base64加密
		byte[] bytesBase64 = Base64Encoder.encode(bytesAes).getBytes();

		try {
			return new String(bytesBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new CalfException(RytResultEnum.UNSUPPORTED_ENCODING);
		}
	}

	private static String decrypt(String data) {
		if (data == null) {
			throw new CalfException(RytResultEnum.NULL_ERROR);
		}
		// base64解密
		byte[] bytesBase64 = Base64Encoder.decode(data);
		// aes解密
		byte[] bytesAes = aes.decrypt(bytesBase64, aesKey.getBytes(), aesIv.getBytes());

		try {
			return new String(bytesAes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new CalfException(RytResultEnum.UNSUPPORTED_ENCODING);
		}
	}

	public static boolean checkSign(ObjectNode objectNode) {
		return createSign(objectNode, signKey).equals(objectNode.get(signParam).asText());
	}

	private static String createSign(Object object, String key) {
		SortedMap<String, Object> sortedMap = JacksonUtil.parseToSortedMapWithSnakeCase(object);
		sortedMap.remove(signParam);
		StringBuilder sb = new StringBuilder();
		Set<Entry<String, Object>> set = sortedMap.entrySet();
		Iterator<Entry<String, Object>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			sb.append(entry.getValue());
		}
		// 添加商户支付KYE
		sb.append(key);
		// MD5签名
		return DigestUtils.md5Hex(sb.toString()).toLowerCase();
	}

}
