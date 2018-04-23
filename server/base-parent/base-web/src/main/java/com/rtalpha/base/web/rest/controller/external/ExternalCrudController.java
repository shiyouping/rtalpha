package com.rtalpha.base.web.rest.controller.external;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.model.BaseModel;

/**
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <Dto>
 * @param <Doc>
 * @param <Model>
 */
public interface ExternalCrudController<Dto extends BaseDto, Doc extends BaseDocument, M extends BaseModel>
		extends ExternalController {

	/**
	 * DO NOT call this method explicitly which is intended to be internal use
	 */
	@Nonnull
	ExternalControllerMetaData<Dto, Doc, M> getControllerMetaData();
}
