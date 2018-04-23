package com.rtalpha.base.mongo.crud;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * Defines the minimum methods for DTO query. Extra methods should be defined in
 * the sub-interfaces if necessary
 * <p>
 * Created by Ricky on 2016/11/13.
 */
public interface ReadService<Dto extends BaseDto, Doc extends BaseDocument> extends CrudService<Dto, Doc> {

	/**
	 * Find all dtos ˙
	 * 
	 * @return the found dtos. Null if not found.
	 */
	@Nullable
	default List<Dto> findAll() {
		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		List<Doc> documents = metaData.getRepository().findAll();
		return metaData.getMapper().toDtoList(documents);
	}

	/**
	 * Find the unique dto for the given id
	 *
	 * @param id
	 *            the unique id
	 * @return the found dto. Null if not found.
	 */
	@Nullable
	default Dto findOne(@Nonnull String id) {
		checkNotNull(id, "id cannot be null");

		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		Doc document = metaData.getRepository().findOne(id);
		return metaData.getMapper().toDto(document);
	}
}
