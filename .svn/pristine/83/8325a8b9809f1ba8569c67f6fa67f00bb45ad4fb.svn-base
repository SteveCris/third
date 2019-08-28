package com.shangyong.thorder.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangyong.thcore.dto.OrderBankH5Dto;
import com.shangyong.thcore.dto.OrderBorrowH5Dto;
import com.shangyong.thcore.dto.OrderRepaymentH5Dto;

public class UrlUtil {
	private static Logger logger = LoggerFactory.getLogger(UrlUtil.class);

	private UrlUtil() {

	}

	public static String getBankBindUrl(String appid, String orderId, OrderBankH5Dto orderBankH5Dto) {

		return buildUrl(PropertiesUtil.get("h5.bank_bind"), appid, orderId, orderBankH5Dto.getAppName(),
				orderBankH5Dto.getSuccessReturnUrl(), orderBankH5Dto.getFailReturnUrl());

	}

	public static String getSureorderUrl(String appid, String orderId, OrderBorrowH5Dto orderBorrowH5Dto) {
		return buildUrl(PropertiesUtil.get("h5.sureorder"), appid, orderId, orderBorrowH5Dto.getAppName(),
				orderBorrowH5Dto.getSuccessReturnUrl(), orderBorrowH5Dto.getFailReturnUrl());

	}
	
	public static String getWithdrawUrl(String appid, String orderId, OrderBorrowH5Dto orderBorrowH5Dto) {
		return buildUrl(PropertiesUtil.get("h5.withdraw"), appid, orderId, orderBorrowH5Dto.getAppName(),
				orderBorrowH5Dto.getSuccessReturnUrl(), orderBorrowH5Dto.getFailReturnUrl());

	}

	public static String getRepaymentUrl(String appid, String orderId, OrderRepaymentH5Dto orderRepaymentH5Dto) {
		return buildUrl(PropertiesUtil.get("h5.repayment"), appid, orderId, orderRepaymentH5Dto.getAppName(),
				orderRepaymentH5Dto.getSuccessReturnUrl(), orderRepaymentH5Dto.getFailReturnUrl());
	}

	private static String buildUrl(String url, String appid, String orderId, String appName, String successReturnUrl,
			String failReturnUrl) {

		StringBuilder sbUrl = new StringBuilder(url);
		try {
			return sbUrl.append("?appid=").append(appid)// 应用编号
					.append("&orderId=").append(orderId)// 订单编号
					.append("&appName=").append(appName)// 应用标识
					.append("&sUrl=").append(URLEncoder.encode(successReturnUrl, "utf-8"))// 跳转成功页面
					.append("&fUrl=").append(URLEncoder.encode(failReturnUrl, "utf-8"))// 跳转失败页面
					.toString();

		} catch (UnsupportedEncodingException e) {
			logger.error("转码失败 successReturnUrl:{};failReturnUrl:{}", successReturnUrl, failReturnUrl, e);
			return failReturnUrl;
		}
	}
}
