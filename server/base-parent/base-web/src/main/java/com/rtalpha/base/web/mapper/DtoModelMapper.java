package com.rtalpha.base.web.mapper;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.web.model.BaseModel;

/**
 * Mapper between {@linkplain BaseDto} and {@linkplain BaseModel}
 * 
 * @author Ricky
 * @since Apr 8, 2017
 *
 * @param <D>
 *            subtype of {@linkplain BaseDto}
 * @param <M>
 *            subtype of {@linkplain BaseModel}
 */
public interface DtoModelMapper<D extends BaseDto, M extends BaseModel> {

	/**
	 * Convert from {@linkplain BaseDto} to {@linkplain BaseModel}
	 */
	@Nullable
	M toModel(@Nullable D dto);

	/**
	 * Convert from a list of {@linkplain BaseDto}s to a list of
	 * {@linkplain BaseModel}s
	 */
	@Nullable
	default List<M> toModelList(@Nullable List<D> dtos) {

		if (dtos == null || dtos.size() == 0) {
			return null;
		}

		List<M> models = new ArrayList<>();

		for (D dto : dtos) {
			models.add(toModel(dto));
		}

		return models;
	}

	/**
	 * Convert from a page of {@linkplain BaseDto}s to a page of
	 * {@linkplain BaseModel}s
	 *
	 */
	@Nonnull
	default Page<M> toModelPage(@Nonnull Page<D> page, @Nonnull Pageable pageable) {
		checkNotNull(page, "page cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		List<M> content = Lists.newArrayListWithCapacity(0);

		if (CollectionUtils.isNotEmpty(page.getContent())) {
			content = toModelList(page.getContent());
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	/**
	 * Convert from {@linkplain BaseModel} to {@linkplain BaseDto}
	 */
	@Nullable
	D toDto(@Nullable M model);

	/**
	 * Convert from a list of {@linkplain BaseModel}s to a list of
	 * {@linkplain BaseDto}s
	 */
	@Nullable
	default List<D> toDtoList(@Nullable List<M> models) {

		if (CollectionUtils.isEmpty(models)) {
			return null;
		}

		List<D> dtos = Lists.newArrayListWithExpectedSize(models.size());

		for (M model : models) {
			dtos.add(toDto(model));
		}

		return dtos;
	}

	/**
	 * Convert from a page of {@linkplain BaseModel}s to a page of
	 * {@linkplain BaseDto}s
	 */
	@Nonnull
	default Page<D> toDtoPage(@Nonnull Page<M> page, @Nonnull Pageable pageable) {
		checkNotNull(page, "page cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		List<D> content = Lists.newArrayListWithCapacity(0);

		if (CollectionUtils.isNotEmpty(page.getContent())) {
			content = toDtoList(page.getContent());
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	/**
	 * Update the passed {@linkplain BaseModel}
	 */
	M updateModel(@Nullable D source, @Nullable @MappingTarget M toBeUpdatedTarget);

	/**
	 * Update the passed {@linkplain BaseDto}
	 */
	D updateDto(@Nullable M source, @Nullable @MappingTarget D toBeUpdatedTarget);
}
