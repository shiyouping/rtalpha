package com.rtalpha.base.mongo.crud;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.joda.time.DateTime;

import com.rtalpha.base.core.cache.CacheEvictor;
import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.base.mongo.repository.MongoBaseRepository;

/**
 * Defines the minimum methods for local DTO creation. Extra methods should be
 * defined in the sub-interfaces if necessary
 * <p>
 * Created by Ricky on 2016/11/13.
 */
public interface CreateService<Dto extends BaseDto, Doc extends BaseDocument>
		extends CrudService<Dto, Doc>, CacheEvictor {

	/**
	 * Saves the underlying entity associated with the given dto. Use the returned
	 * instance for further operations as the operation might have changed the dto
	 * instance completely.
	 *
	 * @param dto
	 *            the associated entity will be saved
	 * @return the dto with new states
	 */
	@Nonnull
	default Dto save(@Nonnull Dto dto) {
		checkNotNull(dto, "dto cannot be null");
		checkArgument(dto.getId() == null, "Cannot specify id for save");

		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		DtoDocumentMapper<Dto, Doc> mapper = metaData.getMapper();
		MongoBaseRepository<Doc> repository = metaData.getRepository();

		DateTime now = DateTime.now();
		dto.setCreatedTime(now);
		dto.setUpdatedTime(now);

		Doc document = mapper.toDocument(dto);
		return mapper.toDto(repository.save(document));
	}
}
