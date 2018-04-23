package com.rtalpha.base.web.rest.controller.internal;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.CreateService;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.rest.response.internal.InternalSingleResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * The internal rest controller for create operation, and delegates
 * {@linkplain CreateService}
 * 
 * 
 * @author Ricky
 * @param <Dto>
 * @since Jun 29, 2017
 */
public interface InternalCreateController<Dto extends BaseDto, Doc extends BaseDocument>
		extends InternalCrudController<Dto, Doc> {

	/**
	 * Delegate the save operation to its corresponding {@linkplain CrudService}
	 */
	@PostMapping(path = RequestPath.METHOD_CREATE_ONE)
	default InternalSingleResponse<Dto> createOne(@Nonnull @RequestBody Dto dto) {
		RequestChecker.checkNotNullBody(dto);
		return new InternalSingleResponse<>(() -> {
			CreateService<Dto, Doc> service = (CreateService<Dto, Doc>) getCrudService();
			return service.save(dto);
		});
	}
}