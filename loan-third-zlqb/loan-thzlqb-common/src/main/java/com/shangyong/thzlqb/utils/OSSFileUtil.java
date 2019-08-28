package com.shangyong.thzlqb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.IdUtil;
import com.shangyong.loan.ext.util.PropertiesUtil;

public class OSSFileUtil {

	private static String endpoint;

	private static String endpointOut;

	private static String accessId;

	private static String accessKey;

	private static String bucketName;

	private static String active;

	private static String protocol;

	private static volatile OSSClient ossClient;

	static {
		endpoint = PropertiesUtil.get("oss.endpoint");
		endpointOut = PropertiesUtil.get("oss.endpointOut");
		accessId = PropertiesUtil.get("oss.accessId");
		accessKey = PropertiesUtil.get("oss.accessKey");
		bucketName = PropertiesUtil.get("oss.bucketName");
		active = PropertiesUtil.get("oss.active");
		protocol = PropertiesUtil.get("oss.request.protocol");

	}

	private OSSFileUtil() {

	}

	/**
	 * 通过网络地址上传
	 * 
	 * @param appid
	 * @param busDir
	 * @param downUrl
	 * @return
	 */
	public static String uploadByUrl(String appid, String busDir, String downUrl) {
		try {
			return upload(appid, busDir, getSuffix(downUrl), new URL(downUrl).openStream());
		} catch (IOException e) {
			throw new CalfException("IOException", e);
		}
	}

	/**
	 * 通过本地地址上传
	 * 
	 * @param appid
	 * @param busDir
	 * @param file
	 * @return
	 */
	public static String uploadByFile(String appid, String busDir, String file) {
		try {
			return upload(appid, busDir, getSuffix(file), new FileInputStream(file));
		} catch (IOException e) {
			throw new CalfException("IOException", e);
		}
	}

	private static String upload(String appid, String busDir, String suffix, InputStream inputStream) {
		StringBuilder sb = new StringBuilder();
		sb//
				.append(active).append("/")//
				.append(appid).append("/")//
				.append(busDir).append("/")//
				.append(LocalDateUtil.dateToString(new Date(), "yyyyMMdd")).append("/")//
				.append(IdUtil.getUuid(32)).append(suffix)//
		;
		try {
			getInstance().putObject(bucketName, sb.toString(), inputStream);
		} catch (OSSException | ClientException e) {
			throw new CalfException("OSSException ClientException", e);
		}

		StringBuilder sbUri = new StringBuilder();
		// 拼接url
		sbUri.append(protocol).append("://").append(bucketName).append(".").append(endpointOut).append("/")
				.append(sb.toString());
		return sbUri.toString();

	}

	private static OSSClient getInstance() {
		if (ossClient == null) {
			synchronized (OSSClient.class) {
				if (ossClient == null) {
					CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessId, accessKey);
					ClientConfiguration config = new ClientBuilderConfiguration();
					ossClient = new OSSClient(endpoint, credentialsProvider, config);
					Runtime.getRuntime().addShutdownHook(new Thread(() -> ossClient.shutdown()));
				}
			}
		}
		return ossClient;
	}

	public static String getSuffix(String url) {
		int index = url.indexOf('?');
		if (index == -1) {
			return url.substring(url.lastIndexOf('.'));
		} else {
			return url.substring(url.lastIndexOf('.'), index);
		}

	}

}
