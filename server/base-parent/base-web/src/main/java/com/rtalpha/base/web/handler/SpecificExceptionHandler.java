package com.rtalpha.base.web.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.MongoException;
import com.rtalpha.base.core.exception.ApplicationException;
import com.rtalpha.base.core.exception.ApplicationException.ExceptionType;
import com.rtalpha.base.core.exception.EmailException;
import com.rtalpha.base.core.exception.ValidationException;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.rest.response.RestErrorResponse;

/**
 * Handle exceptions thrown from http request processing
 * 
 * @author Ricky
 * @since Dec 11, 2016
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public final class SpecificExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<RestErrorResponse> handleApplicationException(ApplicationException exception,
			WebRequest request) {

		HttpStatus status = null;
		String message = exception.getMessage();

		if (StringUtils.isBlank(message)) {
			message = exception.getExceptionType().name();
		}

		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, message);

		if (exception.getExceptionType() == ExceptionType.HTTP_REQUEST) {
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return createErrorResponse(exception, messages, status, request);
	}

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public ResponseEntity<RestErrorResponse> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException exception,
			WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, "Request processing timeout");
		return createErrorResponse(exception, messages, HttpStatus.SERVICE_UNAVAILABLE, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<RestErrorResponse> handleConstraintViolationException(ConstraintViolationException exception,
			WebRequest request) {
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(violations.size());
		for (ConstraintViolation<?> violation : violations) {
			messages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(EmailException.class)
	public ResponseEntity<RestErrorResponse> handleEmailException(EmailException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, "Failed to process email");
		return createErrorResponse(exception, messages, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<RestErrorResponse> handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		List<MediaType> mediaTypes = exception.getSupportedMediaTypes();

		if (CollectionUtils.isNotEmpty(mediaTypes)) {
			List<String> types = Lists.newArrayListWithExpectedSize(mediaTypes.size());
			for (MediaType type : mediaTypes) {
				types.add(type.getSubtype());
			}

			messages.put(RestErrorResponse.MESSAGE_KEY_SUPPORTED_MEDIA_TYPES, types);
		} else {
			messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getMessage());
		}

		return createErrorResponse(exception, messages, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<RestErrorResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getMessage());
		return createErrorResponse(exception, messages, HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<RestErrorResponse> handleIOException(IOException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getCause().getMessage());
		return createErrorResponse(exception, messages, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<RestErrorResponse> handleJsonMappingException(JsonMappingException exception,
			WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);

		if (StringUtils.contains(exception.getOriginalMessage(), "that is to contain type id")) {
			messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL,
					"Json deserialization: Missing type info for non-java-built-in class");
		} else {
			messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getOriginalMessage());
		}

		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RestErrorResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception, WebRequest request) {
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(errors.size());
		for (FieldError error : errors) {
			if (error.getRejectedValue() == null) {
				messages.put(error.getField(), error.getDefaultMessage());
			} else {
				messages.put(error.getField(), error.getDefaultMessage() + " [" + error.getRejectedValue() + "]");
			}
		}

		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<RestErrorResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getMessage());

		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(MongoException.class)
	public ResponseEntity<RestErrorResponse> handleMongoException(MongoException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_EXCEPTION_TYPE, exception.getClass().getSimpleName());

		return createErrorResponse(exception, messages, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<RestErrorResponse> handleMultipartException(MultipartException exception,
			WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, "Violate the file upload constraints");
		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<RestErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException exception,
			WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getMessage());
		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(RestException.class)
	public ResponseEntity<RestErrorResponse> handleRestException(RestException exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getMessage());
		return createErrorResponse(exception, messages, exception.getStatus(), request);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<RestErrorResponse> handleValidationException(ValidationException exception,
			WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, exception.getErrors());
		return createErrorResponse(exception, messages, HttpStatus.BAD_REQUEST, request);
	}
}
