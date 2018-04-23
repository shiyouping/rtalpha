package com.rtalpha.base.web.rest.response;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nonnull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Maps;

/**
 * A factory tool for REST {@linkplain ResponseEntity}
 * 
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
public class ResponseEntityFactory {

	@Nonnull
	public static ResponseEntity<?> getOk() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Nonnull
	public static ResponseEntity<?> getOk(@Nonnull Object body) {
		checkNotNull(body, "body cannnot be null");
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	@Nonnull
	public static ResponseEntity<?> getBadRequest(@Nonnull String message) {
		checkNotNull(message, "message cannot be null");

		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, message);
		return new ResponseEntity<>(new RestErrorResponse(HttpStatus.BAD_REQUEST, messages, null, null),
				HttpStatus.BAD_REQUEST);
	}

	@Nonnull
	public static ResponseEntity<?> getNotFound(@Nonnull String message) {
		checkNotNull(message, "message cannot be null");

		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, message);
		return new ResponseEntity<>(new RestErrorResponse(HttpStatus.NOT_FOUND, messages, null, null),
				HttpStatus.NOT_FOUND);
	}

	@Nonnull
	public static ResponseEntity<?> getInternalServerError(String message) {
		checkNotNull(message, "message cannot be null");

		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, message);
		return new ResponseEntity<>(new RestErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, messages, null, null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Nonnull
	public static ResponseEntity<?> getError(@Nonnull HttpStatus status, @Nonnull String message) {
		checkNotNull(message, "message cannot be null");
		checkNotNull(status, "status cannot be null");
		checkArgument(status.value() >= 400, "Not an error status");

		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(RestErrorResponse.MESSAGE_KEY_DETAIL, message);
		return new ResponseEntity<>(new RestErrorResponse(status, messages, null, null), status);
	}
}
