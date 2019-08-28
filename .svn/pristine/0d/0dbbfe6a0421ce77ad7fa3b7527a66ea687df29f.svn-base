package com.shangyong.thzlqb.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class TokenHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(TokenHandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ip = request.getHeader("XClientIp");
		if (ip != null) {
			logger.info("客户端ip地址：{}", ip);
		}

		// 初始化日志记录参数
		return super.preHandle(request, response, handler);

	}

}
