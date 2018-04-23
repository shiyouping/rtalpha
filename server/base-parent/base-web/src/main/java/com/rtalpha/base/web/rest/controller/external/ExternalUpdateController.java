package com.rtalpha.base.web.rest.controller.external;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.kernel.utility.BeanUtil;
import com.rtalpha.base.mongo.crud.ReadService;
import com.rtalpha.base.mongo.crud.UpdateService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.FullModel;
import com.rtalpha.base.web.model.PartialModel;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * 
 * The internal rest controller for update operation, and delegates
 * {@linkplain UpdateService}. If used, the non-java-built-in type information
 * in the body of JSON requests from client must be specified.
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface ExternalUpdateController<Dto extends BaseDto, Doc extends BaseDocument, F extends FullModel, P extends PartialModel>
		extends ExternalCrudController<Dto, Doc, F> {

	/**
	 * Perform operations after update. Provide the implementation if necessary
	 */
	default void postUpdateOne(@Nonnull F updatedModel, @Nonnull Dto updatedDto) {
		// Nothing to do for the default implementation
	}

	/**
	 * Perform operations before update, e.g. validation, etc. Provide the
	 * implementation if necessary
	 */
	default void preUpdateOne(@Nonnull P modelToBeUpdated, @Nonnull Dto existingDto) {
		// Nothing to do for the default implementation
	}

	/**
	 * Delegate the update operation to its corresponding {@linkplain UpdateService}
	 */
	@PutMapping(path = RequestPath.METHOD_UPDATE_ONE)
	default ExternalSingleResponse<F> updateOne(@Nonnull @Valid @RequestBody P model) {
		RequestChecker.checkNotNullBody(model);

		return new ExternalSingleResponse<>(() -> {
			if (StringUtils.isBlank(model.getId())) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Missing id for update");
			}

			ExternalControllerMetaData<Dto, Doc, F> metaData = getControllerMetaData();
			DtoModelMapper<Dto, F> mapper = metaData.getMapper();
			ReadService<Dto, Doc> readService = (ReadService<Dto, Doc>) metaData.getCrudService();
			UpdateService<Dto, Doc> updateService = (UpdateService<Dto, Doc>) metaData.getCrudService();

			try {
				Dto existingDto = readService.findOne(model.getId());

				if (existingDto == null) {
					throw new RestException(HttpStatus.BAD_REQUEST, "Invalid id for update");
				}

				preUpdateOne(model, existingDto);

				BeanUtil.copyAllProperties(existingDto, model);
				Dto updatedDto = updateService.update(existingDto);
				F updatedModel = mapper.toModel(updatedDto);

				postUpdateOne(updatedModel, updatedDto);

				return updatedModel;

			} catch (InvocationTargetException | IllegalAccessException e) {
				throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to copy model properties");
			}
		});
	}
}
