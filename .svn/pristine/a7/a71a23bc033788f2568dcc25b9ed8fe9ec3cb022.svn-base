package com.shangyong.thorder.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shangyong.thorder.contants.CoreContants;
import com.shangyong.thorder.utils.JacksonUtil;

@Configuration
public class TokenHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(TokenHandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取uri map
		Object obj = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Map<String, String> map = JacksonUtil.parseToMap(obj);
		String appid = map.get(CoreContants.APPID);
		if (appid == null) {
			return super.preHandle(request, response, handler);
		}
		// 日志记录初始化
		MDC.put("uid", appid);
		logger.info("客户端ip地址：{}", request.getHeader("XClientIp"));

		// 初始化日志记录参数
		return super.preHandle(request, response, handler);

	}

}
