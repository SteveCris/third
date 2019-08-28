package com.shangyong.thorder.utils;

import com.shangyong.center.client.exception.RemoteServerException;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thorder.contants.UuidPrefix;

public class EventUtil {
	
	private EventUtil() {
		
	}
	
	public static String getMessageId() {
		return IdUtil.getNumberUuid(UuidPrefix.MESSAGE);
	}

	public static EventHeader buildEventHeader(String messageId, String appid,String orderId,String appName, boolean isSuccess) {
		EventHeader eventHeader = new EventHeader();
		eventHeader.setAppid(appid);
		eventHeader.setHappenTime(System.currentTimeMillis());
		eventHeader.setMessageId(messageId);
		eventHeader.setOrderId(orderId);
		eventHeader.setAppName(appName);
		eventHeader.setSuccess(isSuccess);
		return eventHeader;
	}

	public static EventHeader buildEventHeader(String messageId, String appid,String orderId,String appName, boolean isSuccess,String remark) {
		EventHeader eventHeader = new EventHeader();
		eventHeader.setAppid(appid);
		eventHeader.setHappenTime(System.currentTimeMillis());
		eventHeader.setMessageId(messageId);
		eventHeader.setOrderId(orderId);
		eventHeader.setAppName(appName);
		eventHeader.setSuccess(isSuccess);
		eventHeader.setRemark(remark);
		return eventHeader;
	}
	
	public static EventFailureResult buildEventFailureResult(RemoteServerException remoteServerException) {
		EventFailureResult eventFailureResult = new EventFailureResult();
		eventFailureResult.setCode(remoteServerException.getCode());
		eventFailureResult.setReason(remoteServerException.getDynaMessage() == null ? remoteServerException.getMessage() : remoteServerException.getDynaMessage());
		return eventFailureResult;
	}
}
