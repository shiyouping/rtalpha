package com.rtalpha.base.mongo.crud;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.base.mongo.repository.MongoBaseRepository;

/**
 * Implement the default method in its super interface
 * 
 * @author Ricky Shi
 * @since Jul 13, 2017
 * @param <Dto>
 * @param <Doc>
 */
public abstract class AbstractCrudService<Dto extends BaseDto, Doc extends BaseDocument>
		implements CrudService<Dto, Doc> {

	private final ServiceMetaData<Dto, Doc> metaData;

	public AbstractCrudService(@Nonnull MongoBaseRepository<Doc> repository,
			@Nonnull DtoDocumentMapper<Dto, Doc> mapper) {
		this.metaData = new ServiceMetaData<>(repository, mapper);
	}

	@Override
	public ServiceMetaData<Dto, Doc> getServiceMetaData() {
		return metaData;
	}
}
