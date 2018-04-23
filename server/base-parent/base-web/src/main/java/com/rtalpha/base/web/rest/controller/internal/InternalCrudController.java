package com.rtalpha.base.web.rest.controller.internal;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * 
 * The parent rest controller for internal system use. Its subclasses should
 * delegate the CRUD operations to their corresponding {@link CrudService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface InternalCrudController<Dto extends BaseDto, Doc extends BaseDocument> extends InternalController {
	/**
	 * This method is intended to be internally used. Do NOT call this method
	 * explicitly.
	 */
	@Nonnull
	CrudService<Dto, Doc> getCrudService();
}
