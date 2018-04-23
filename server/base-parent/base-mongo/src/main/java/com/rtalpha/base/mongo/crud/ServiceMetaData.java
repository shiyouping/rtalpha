package com.rtalpha.base.mongo.crud;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.base.mongo.repository.MongoBaseRepository;

/**
 * @author Ricky Shi
 * @param <D>
 * @since Jul 13, 2017
 */
public class ServiceMetaData<Dto extends BaseDto, Doc extends BaseDocument> {

	private final MongoBaseRepository<Doc> repository;
	private final DtoDocumentMapper<Dto, Doc> mapper;

	public ServiceMetaData(@Nonnull MongoBaseRepository<Doc> repository, @Nonnull DtoDocumentMapper<Dto, Doc> mapper) {
		checkNotNull(repository, "repository cannot be null");
		checkNotNull(mapper, "mapper cannot be null");

		this.repository = repository;
		this.mapper = mapper;
	}

	public DtoDocumentMapper<Dto, Doc> getMapper() {
		return mapper;
	}

	public MongoBaseRepository<Doc> getRepository() {
		return repository;
	}
}
