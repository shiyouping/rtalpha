package com.rtalpha.base.web.rest.controller.external;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.DeleteService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.BaseModel;
import com.rtalpha.base.web.rest.response.external.ExternalVoidResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * The internal rest controller for delete operation, and delegates
 * {@linkplain DeleteService}. If used, the non-java-built-in type information
 * in the body of JSON requests from client must be specified.
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface ExternalDeleteController<Dto extends BaseDto, Doc extends BaseDocument, M extends BaseModel>
		extends ExternalCrudController<Dto, Doc, M> {

	/**
	 * Delegate the delete operation to its corresponding {@linkplain DeleteService}
	 */
	@DeleteMapping(path = RequestPath.METHOD_DELETE_ONE)
	default ExternalVoidResponse deleteOne(@Nonnull @Valid @RequestBody M model) {
		RequestChecker.checkNotNullBody(model);
		return new ExternalVoidResponse(() -> {
			preDeleteOne(model);

			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			DeleteService<Dto, Doc> service = (DeleteService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, M> mapper = metaData.getMapper();

			Dto dto = mapper.toDto(model);
			service.delete(dto);

			postDeleteOne(model, dto);
		});
	}

	/**
	 * Delegate the delete operation to its corresponding {@linkplain DeleteService}
	 */
	@DeleteMapping(path = RequestPath.METHOD_DELETE_ONE + "ById")
	default ExternalVoidResponse deleteOneById(@Nonnull @RequestParam(value = "id") String id) {
		RequestChecker.checkNotBlankString(id, "id");
		return new ExternalVoidResponse(() -> {
			preDeleteOneById(id);

			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			DeleteService<Dto, Doc> service = (DeleteService<Dto, Doc>) metaData.getCrudService();
			service.delete(id);

			postDeleteOneById(id);
		});
	}

	/**
	 * Perform operations after delete. Provide the implementation if necessary
	 */
	default void postDeleteOne(@Nonnull M deletedModel, @Nonnull Dto deletedDto) {
		// Nothing to do for the default implementation
	}

	/**
	 * Perform operations after delete. Provide the implementation if necessary
	 */
	default void postDeleteOneById(@Nonnull String id) {
		// Nothing to do for the default implementation
	}

	/**
	 * Perform operations before delete, e.g. validation, etc. Provide the
	 * implementation if necessary
	 */
	default void preDeleteOne(@Nonnull M modelToBeDeleted) {
		// Nothing to do for the default implementation
	}

	/**
	 * Perform operations before delete, e.g. validation, etc. Provide the
	 * implementation if necessary
	 */
	default void preDeleteOneById(@Nonnull String id) {
		// Nothing to do for the default implementation
	}
}
