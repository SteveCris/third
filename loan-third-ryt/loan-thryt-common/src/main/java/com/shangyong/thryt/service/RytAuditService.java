package com.shangyong.thryt.service;

/**
 * 认证服务
 * 
 * @author caijunjun
 * @date 2019年6月2日
 */
public interface RytAuditService {

	/**
	 * 处理风控认证事件
	 * 
	 * @param orderNo
	 * @param ifSuccess
	 * @return
	 */
	boolean processAudit(String orderNo, boolean ifSuccess);
}
