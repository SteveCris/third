package com.shangyong.thzlqb.utils;

import com.shangyong.loan.ext.util.IdUtil;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thzlqb.contants.UuidPrefix;

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

	
	
}
