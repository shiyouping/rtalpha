package com.rtalpha.base.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Parse the location information from every request
 * 
 * @author Ricky
 * @since 2016.12.11
 */
public class HttpLocationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(HttpLocationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO improve this method
		logger.info("Http request from {} to {}", request.getRemoteAddr(), request.getRequestURL());
		return true;
	}
}
