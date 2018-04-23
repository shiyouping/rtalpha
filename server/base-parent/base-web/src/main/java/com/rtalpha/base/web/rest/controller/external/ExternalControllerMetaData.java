package com.rtalpha.base.web.rest.controller.external;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.BaseModel;

/**
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <Dto>
 * @param <Doc>
 * @param <M>
 */
public class ExternalControllerMetaData<Dto extends BaseDto, Doc extends BaseDocument, M extends BaseModel> {

	private CrudService<Dto, Doc> crudService;
	private DtoModelMapper<Dto, M> mapper;

	public ExternalControllerMetaData(@Nonnull CrudService<Dto, Doc> crudService,
			@Nonnull DtoModelMapper<Dto, M> mapper) {
		checkNotNull(crudService, "crudService cannot be null");
		checkNotNull(mapper, "mapper cannot be null");

		this.crudService = crudService;
		this.mapper = mapper;
	}

	public CrudService<Dto, Doc> getCrudService() {
		return crudService;
	}

	public void setCrudService(CrudService<Dto, Doc> crudService) {
		this.crudService = crudService;
	}

	public DtoModelMapper<Dto, M> getMapper() {
		return mapper;
	}

	public void setMapper(DtoModelMapper<Dto, M> mapper) {
		this.mapper = mapper;
	}
}
