package com.rtalpha.base.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Control the request traffic from outside the system
 * 
 * @author Ricky
 * @since Dec 11, 2016
 *
 */
public class HttpTrafficInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO improve this method
		return super.preHandle(request, response, handler);
	}
}
