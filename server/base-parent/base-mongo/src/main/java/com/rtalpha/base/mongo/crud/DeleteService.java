package com.rtalpha.base.mongo.crud;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.rtalpha.base.core.cache.CacheEvictor;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * Defines the minimum methods for local DTO deletion. Extra methods should be
 * defined in the sub-interfaces if necessary
 * <p>
 * Created by Ricky on 2016/11/13.
 */
public interface DeleteService<Dto extends BaseDto, Doc extends BaseDocument>
		extends CrudService<Dto, Doc>, CacheEvictor {

	/**
	 * Deletes the underlying entity with the given id.
	 *
	 * @param id
	 *            the underlying entity with this id
	 */
	default void delete(@Nonnull String id) {
		checkNotNull(id, "id cannot be null");

		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		metaData.getRepository().delete(id);
	}

	/**
	 * Deletes the underlying entity associated with the given dto
	 * 
	 * @param dto
	 */
	default void delete(@Nonnull Dto dto) {
		checkNotNull(dto, "dto cannot be null");
		checkNotNull(dto.getId(), "id of dto cannot be null");

		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		Doc document = metaData.getMapper().toDocument(dto);
		metaData.getRepository().delete(document);
	}
}
