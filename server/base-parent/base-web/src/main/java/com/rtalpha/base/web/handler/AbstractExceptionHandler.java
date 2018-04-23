package com.rtalpha.base.web.handler;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.rtalpha.base.core.exception.ValidationException;
import com.rtalpha.base.kernel.utility.UniqueId;
import com.rtalpha.base.web.rest.response.RestErrorResponse;

/**
 * @author Ricky Shi
 * @since Sep 13, 2017
 */
public abstract class AbstractExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(SpecificExceptionHandler.class);

	@Nonnull
	protected ResponseEntity<RestErrorResponse> createErrorResponse(@Nonnull Exception exception,
			@Nonnull Map<String, Object> messages, @Nonnull HttpStatus status, @Nonnull WebRequest request) {
		checkNotNull(exception, "exception cannot be null");
		checkNotNull(messages, "messages cannot be null");
		checkNotNull(status, "status cannot be null");
		checkNotNull(request, "request cannot be null");

		if (exception instanceof ValidationException) {
			ValidationException validationException = (ValidationException) exception;
			validationException.getErrors().forEach((error) -> {
				logger.warn("Validation failure: {}", error);
			});
		}

		String code = UniqueId.get();
		String path = getPath(request);

		logger.warn("Failed to process http request. Http Status={}, Error Code={}, Requested Path={}", status, code,
				path, exception);

		RestErrorResponse body = new RestErrorResponse(status, messages, path, code);
		return new ResponseEntity<>(body, status);
	}

	private String getPath(WebRequest request) {
		if (!(request instanceof ServletWebRequest)) {
			return null;
		}

		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		return servletWebRequest.getRequest().getServletPath();
	}
}
