package com.shangyong.thzlqb.zlqb.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AesUtil {
	
	private static Logger logger=LoggerFactory.getLogger(AesUtil.class); 
	
	public static String encrypt(String input, String aesKey) {
		try {
			if (aesKey == null) {
				return null;
			}
			// 判断Key是否为16位
			if (aesKey.length() != 16) {
				return null;
			}
			byte[] raw = aesKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算 法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(input.getBytes("utf-8"));
			return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {
			logger.error("encrypt error,input:{},aesKey:{}",input,aesKey,e);
			return null;
		}

	}

	// 解密
	public static String decrypt(String input, String aesKey) {
		try {
			// 判断Key是否正确
			if (aesKey == null) {
				return null;
			}
			// 判断Key是否为16位
			if (aesKey.length() != 16) {
				return null;
			}
			byte[] raw = aesKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(input);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original, "utf-8");
		} catch (Exception e) {
			logger.error("decrypt error,input:{},aesKey:{}",input,aesKey,e);
			return null;
		}
	}
	
	


}
