package com.rtalpha.base.web.rest.controller.internal;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.ReadService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.rest.response.internal.InternalBulkResponse;
import com.rtalpha.base.web.rest.response.internal.InternalSingleResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * 
 * The internal rest controller for read operation, and delegates
 * {@linkplain ReadService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface InternalReadController<Dto extends BaseDto, Doc extends BaseDocument>
		extends InternalCrudController<Dto, Doc> {

	/**
	 * Delegate the read operation to its corresponding {@linkplain ReadService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_ONE + "ById")
	default InternalSingleResponse<Dto> findOneById(@Nonnull @RequestParam(value = "id") String id) {
		RequestChecker.checkNotBlankString(id, "id");
		return new InternalSingleResponse<>(() -> {
			ReadService<Dto, Doc> service = (ReadService<Dto, Doc>) getCrudService();
			return service.findOne(id);
		});
	}

	/**
	 * Delegate the read operation to its corresponding {@linkplain ReadService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_ALL)
	default InternalBulkResponse<Dto> findAll() {
		return new InternalBulkResponse<>(() -> {
			ReadService<Dto, Doc> service = (ReadService<Dto, Doc>) getCrudService();
			return service.findAll();
		});
	}
}
