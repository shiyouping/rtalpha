package com.rtalpha.base.jpa.mapper;

import javax.annotation.Nullable;

import com.rtalpha.base.jpa.entity.BaseEntity;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Mapper between {@linkplain BaseDto} and {@linkplain BaseEntity}
 * 
 * @author Ricky
 * @since Apr 8, 2017
 *
 * @param <D>
 *            subtype of {@linkplain BaseDto}
 * @param <E>
 *            subtype of {@linkplain BaseEntity}
 */
public interface DtoEntityMapper<D extends BaseDto, E extends BaseEntity> {

	/**
	 * Convert from {@linkplain BaseEntity} to {@linkplain BaseDto}
	 */
	@Nullable
	D toDto(@Nullable E entity);

	/**
	 * Convert from {@linkplain BaseDto} to {@linkplain BaseEntity}
	 */
	@Nullable
	E toEntity(@Nullable D dto);
}
