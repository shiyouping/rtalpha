package com.rtalpha.base.web.exception;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.springframework.http.HttpStatus;

/**
 * @author Ricky
 * @since Jun 10, 2017
 *
 */
public class RestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;
	private final String message;

	public RestException(@Nonnull HttpStatus status, @Nonnull String message) {
		checkNotNull(status, "status cannot be null");
		checkNotNull(message, "message cannot be null");

		this.message = message;
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
