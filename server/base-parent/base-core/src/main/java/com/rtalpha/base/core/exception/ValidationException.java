package com.rtalpha.base.core.exception;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

/**
 * Thrown when there is any logical validation violation
 * 
 * @author Ricky
 * @since Jun 15, 2017
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final List<String> errors = Lists.newArrayListWithExpectedSize(10);

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param field
	 *            on which filed the value violates the constraint
	 * @param error
	 *            the detailed message about the violations
	 */
	public void addError(@Nonnull String error) {
		checkNotNull(error, "error cannot be null");
		errors.add(error);
	}

	public List<String> getErrors() {
		return errors;
	}
}
