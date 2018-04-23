package com.rtalpha.base.core.validation;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.rtalpha.base.core.exception.ApplicationException;
import com.rtalpha.base.core.exception.ApplicationException.ExceptionType;
import com.rtalpha.base.core.exception.ValidationException;

/**
 * Performs the validation to the logic of given objects
 * 
 * @author Ricky
 * @since Jun 15, 2017
 */
public interface LogicalValidator {

	/**
	 * Invoke the callback and determine if any {@linkplain ValidationException}
	 * needs to be thrown
	 * 
	 */
	default void validate(@Nonnull ValidatorCallback callback) {
		checkNotNull(callback, "callback cannot be null");

		ValidationException exception = new ValidationException("Cannot pass validation");

		try {
			callback.validate(exception.getErrors());
		} catch (Exception e) {
			throw new ApplicationException("Cannot perform logical validation", ExceptionType.VALIDATION);
		}

		if (exception.getErrors().size() > 0) {
			throw exception;
		}
	}
}
