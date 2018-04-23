package com.rtalpha.base.core.exception;

import javax.annotation.Nullable;

/**
 * Thrown when email sending exception happens
 * <p>
 * Created by Ricky on 16/6/5.
 */
public class EmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailException() {
		super();
	}

	public EmailException(@Nullable String message) {
		super(message);
	}

	public EmailException(@Nullable String message, @Nullable Throwable cause) {
		super(message, cause);
	}

	public EmailException(@Nullable Throwable cause) {
		super(cause);
	}
}
