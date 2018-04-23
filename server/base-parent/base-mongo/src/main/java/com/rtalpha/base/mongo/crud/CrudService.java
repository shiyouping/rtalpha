package com.rtalpha.base.mongo.crud;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * Superclass of all database services for local CRUD
 * 
 * @author Ricky
 * @param <Doc>
 * @since Jun 29, 2017
 */
public interface CrudService<Dto extends BaseDto, Doc extends BaseDocument> {

	/**
	 * DO NOT call this method explicitly which is intended to be internal use
	 */
	@Nonnull
	ServiceMetaData<Dto, Doc> getServiceMetaData();
}
