package com.rtalpha.base.core.validation;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Validates if update operation is valid or not
 * 
 * @author Ricky
 * @param <D>
 * @since Jun 17, 2017
 *
 */
@FunctionalInterface
public interface UpdateValidator<D extends BaseDto> extends LogicalValidator {

	void validateUpdate(@Nonnull D dto);
}
