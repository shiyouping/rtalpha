package com.rtalpha.base.web.rest.controller.external;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.CreateService;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.FullModel;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * The external rest controller for create operation, and delegates
 * {@linkplain CreateService}. If used, the non-java-built-in type information
 * in the body of JSON requests from client must be specified.
 * 
 * 
 * @author Ricky
 * @param <Dto>
 * @since Jun 29, 2017
 */
public interface ExternalCreateController<Dto extends BaseDto, Doc extends BaseDocument, M extends FullModel>
		extends ExternalCrudController<Dto, Doc, M> {

	/**
	 * Delegate the save operation to its corresponding {@linkplain CrudService}
	 */
	@PostMapping(path = RequestPath.METHOD_CREATE_ONE)
	default ExternalSingleResponse<M> createOne(@Nonnull @Valid @RequestBody M newModel) {
		RequestChecker.checkNotNullBody(newModel);

		return new ExternalSingleResponse<>(() -> {
			preCreateOne(newModel);

			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			CreateService<Dto, Doc> service = (CreateService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, M> mapper = metaData.getMapper();

			Dto newDto = mapper.toDto(newModel);
			Dto persistedDto = service.save(newDto);
			M persistedModel = mapper.toModel(persistedDto);

			postCreateOne(persistedModel, persistedDto);

			return persistedModel;
		});
	}

	/**
	 * Perform operations after save. Provide the implementation if necessary
	 */
	default void postCreateOne(@Nonnull M persistedModel, @Nonnull Dto persistedDto) {
		// Nothing to do for the default implementation
	}

	/**
	 * Perform operations before save, e.g. validation, etc. Provide the
	 * implementation if necessary
	 */
	default void preCreateOne(@Nonnull M newModel) {
		// Nothing to do for the default implementation
	}
}