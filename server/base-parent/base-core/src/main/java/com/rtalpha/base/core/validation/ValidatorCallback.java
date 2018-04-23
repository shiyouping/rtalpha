package com.rtalpha.base.core.validation;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * 
 * The actual place to perform logical validations used in
 * {@linkplain LogicalValitior#validate(callback)}
 * 
 * @author Ricky
 * @since Jun 17, 2017
 *
 */
@FunctionalInterface
public interface ValidatorCallback {

	/**
	 * DO NOT throw any exceptions in this method, including checked exceptions and
	 * unchecked exceptions
	 */
	void validate(@Nonnull final List<String> errors);
}
