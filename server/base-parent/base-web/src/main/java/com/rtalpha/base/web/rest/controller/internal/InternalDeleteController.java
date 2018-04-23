package com.rtalpha.base.web.rest.controller.internal;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.DeleteService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.rest.response.internal.InternalVoidResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * The internal rest controller for delete operation, and delegates
 * {@linkplain DeleteService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface InternalDeleteController<Dto extends BaseDto, Doc extends BaseDocument>
		extends InternalCrudController<Dto, Doc> {

	/**
	 * Delegate the delete operation to its corresponding {@linkplain DeleteService}
	 */
	@DeleteMapping(path = RequestPath.METHOD_DELETE_ONE + "ById")
	default InternalVoidResponse deleteOneById(@Nonnull @RequestParam(value = "id") String id) {
		RequestChecker.checkNotBlankString(id, "id");
		return new InternalVoidResponse(() -> {
			DeleteService<Dto, Doc> service = (DeleteService<Dto, Doc>) getCrudService();
			service.delete(id);
		});
	}

	/**
	 * Delegate the delete operation to its corresponding {@linkplain DeleteService}
	 */
	@DeleteMapping(path = RequestPath.METHOD_DELETE_ONE)
	default InternalVoidResponse deleteOne(@Nonnull @RequestBody Dto dto) {
		RequestChecker.checkNotNullBody(dto);
		return new InternalVoidResponse(() -> {
			DeleteService<Dto, Doc> service = (DeleteService<Dto, Doc>) getCrudService();
			service.delete(dto);
		});
	}
}
