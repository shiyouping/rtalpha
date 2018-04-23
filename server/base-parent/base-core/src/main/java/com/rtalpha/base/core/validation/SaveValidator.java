package com.rtalpha.base.core.validation;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Validates if save operation is valid or not
 * 
 * @author Ricky
 * @since Jun 17, 2017
 *
 * @param <D>
 */
@FunctionalInterface
public interface SaveValidator<D extends BaseDto> extends LogicalValidator {

	void validateSave(@Nonnull D dto);
}
