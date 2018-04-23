package com.rtalpha.base.mongo.mapper;

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
import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * Mapper between {@linkplain BaseDto} and {@linkplain BaseDocument}
 * 
 * @author Ricky
 * @since Apr 8, 2017
 *
 * @param <Dto>
 *            subtype of {@linkplain BaseDto}
 * @param <Doc>
 *            subtype of {@linkplain BaseDocument}
 */
public interface DtoDocumentMapper<Dto extends BaseDto, Doc extends BaseDocument> {

	/**
	 * Convert from {@linkplain BaseDto} to {@linkplain BaseDocument}
	 */
	@Nullable
	Doc toDocument(@Nullable Dto dto);

	/**
	 * Convert from a list of {@linkplain BaseDto}s to a list of
	 * {@linkplain BaseDocument}s
	 */
	@Nullable
	default List<Doc> toDocumentList(@Nullable List<Dto> dtos) {

		if (dtos == null || dtos.size() == 0) {
			return null;
		}

		List<Doc> documents = new ArrayList<>();

		for (Dto dto : dtos) {
			documents.add(toDocument(dto));
		}

		return documents;
	}

	/**
	 * Convert from a page of {@linkplain BaseDto}s to a page of
	 * {@linkplain BaseDocument}s
	 *
	 */
	@Nonnull
	default Page<Doc> toDocumentPage(@Nonnull Page<Dto> page, @Nonnull Pageable pageable) {
		checkNotNull(page, "page cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		List<Doc> content = Lists.newArrayListWithCapacity(0);

		if (CollectionUtils.isNotEmpty(page.getContent())) {
			content = toDocumentList(page.getContent());
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	/**
	 * Convert from {@linkplain BaseDocument} to {@linkplain BaseDto}
	 */
	@Nullable
	Dto toDto(@Nullable Doc document);

	/**
	 * Convert from a list of {@linkplain BaseDocument}s to a list of
	 * {@linkplain BaseDto}s
	 */
	@Nullable
	default List<Dto> toDtoList(@Nullable List<Doc> documents) {

		if (CollectionUtils.isEmpty(documents)) {
			return null;
		}

		List<Dto> dtos = Lists.newArrayListWithExpectedSize(documents.size());

		for (Doc document : documents) {
			dtos.add(toDto(document));
		}

		return dtos;
	}

	/**
	 * Convert from a page of {@linkplain BaseDocument}s to a page of
	 * {@linkplain BaseDto}s
	 */
	@Nonnull
	default Page<Dto> toDtoPage(@Nonnull Page<Doc> page, @Nonnull Pageable pageable) {
		checkNotNull(page, "page cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		List<Dto> content = Lists.newArrayListWithCapacity(0);

		if (CollectionUtils.isNotEmpty(page.getContent())) {
			content = toDtoList(page.getContent());
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	/**
	 * Update the passed {@linkplain BaseDocument}
	 */
	Doc updateDoc(@Nullable Dto source, @Nullable @MappingTarget Doc toBeUpdatedTarget);

	/**
	 * Update the passed {@linkplain BaseDto}
	 */
	Dto updateDto(@Nullable Doc source, @Nullable @MappingTarget Dto toBeUpdatedTarget);
}
