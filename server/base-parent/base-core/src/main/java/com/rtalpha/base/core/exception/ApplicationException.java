package com.rtalpha.base.core.exception;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Application runtime exception that should not be caught in most cases
 * <p>
 * Created by Ricky on 2016/11/14.
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ExceptionType exceptionType;

	public ApplicationException(@Nullable String message, @Nonnull ExceptionType exceptionType) {
		super(message);
		checkNotNull(exceptionType, "exceptionType cannot be null");
		this.exceptionType = exceptionType;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public static enum ExceptionType {
		GENERIC, DATA_ACCESS, EMAIL, HTTP_REQUEST, INVALID_PARAMETER, DUPLICATE_KEY, VALIDATION
	}
}
