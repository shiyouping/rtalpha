package com.rtalpha.base.web.rest.controller.external;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.ReadService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.FullModel;
import com.rtalpha.base.web.rest.response.external.ExternalBulkResponse;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.utility.RequestChecker;

/**
 * 
 * The internal rest controller for read operation, and delegates
 * {@linkplain ReadService}
 * 
 * @author Ricky
 * @since Jun 29, 2017
 */
public interface ExternalReadController<Dto extends BaseDto, Doc extends BaseDocument, M extends FullModel>
		extends ExternalCrudController<Dto, Doc, M> {

	/**
	 * Delegate the read operation to its corresponding {@linkplain ReadService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_ONE + "ById")
	default ExternalSingleResponse<M> findOneById(@Nonnull @RequestParam(value = "id") String id) {
		RequestChecker.checkNotBlankString(id, "id");

		return new ExternalSingleResponse<>(() -> {
			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			ReadService<Dto, Doc> service = (ReadService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, M> mapper = metaData.getMapper();

			Dto dto = service.findOne(id);
			return mapper.toModel(dto);
		});
	}

	/**
	 * Delegate the read operation to its corresponding {@linkplain ReadService}
	 */
	@GetMapping(path = RequestPath.METHOD_FIND_ALL)
	default ExternalBulkResponse<M> findAll() {

		return new ExternalBulkResponse<>(() -> {
			ExternalControllerMetaData<Dto, Doc, M> metaData = getControllerMetaData();
			ReadService<Dto, Doc> service = (ReadService<Dto, Doc>) metaData.getCrudService();
			DtoModelMapper<Dto, M> mapper = metaData.getMapper();

			List<Dto> dtos = service.findAll();
			return mapper.toModelList(dtos);
		});
	}
}
