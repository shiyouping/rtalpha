package com.rtalpha.base.core.validation;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Performs the logical {@linkplain BaseDto} validation
 * 
 * @author Ricky
 * @since May 13, 2017
 *
 */
@FunctionalInterface
public interface ValidationService {

	<D extends BaseDto> void validate(@Nonnull D dto);
}
