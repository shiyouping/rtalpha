package com.rtalpha.base.web.handler;

import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.google.common.collect.Maps;
import com.rtalpha.base.web.rest.response.RestErrorResponse;

/**
 * Handle exceptions that cannot be handled by other handlers
 * 
 * @author Ricky
 * @since Jul 16, 2017
 *
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GenericExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestErrorResponse> handleException(Exception exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, "Internal Server Error");
		return createErrorResponse(exception, messages, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
