package com.rtalpha.base.web.rest.controller.internal;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.UpdateService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.rest.response.internal.InternalSingleResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * 
 * The internal rest controller for update operation, and delegates
 * {@linkplain UpdateService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface InternalUpdateController<Dto extends BaseDto, Doc extends BaseDocument>
		extends InternalCrudController<Dto, Doc> {

	/**
	 * Delegate the update operation to its corresponding {@linkplain UpdateService}
	 */
	@PutMapping(path = RequestPath.METHOD_UPDATE_ONE)
	default InternalSingleResponse<Dto> updateOne(@Nonnull @RequestBody Dto dto) {
		RequestChecker.checkNotNullBody(dto);
		return new InternalSingleResponse<>(() -> {
			UpdateService<Dto, Doc> service = (UpdateService<Dto, Doc>) getCrudService();
			return service.update(dto);
		});
	}
}
