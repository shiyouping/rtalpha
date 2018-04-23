package com.rtalpha.base.mongo.crud;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.mongo.repository.FullTextSearchRepository;

/**
 * @author Ricky
 * @since Jun 10, 2017
 *
 * @param <Dto>
 *            subclass of {@linkplain BaseDto}
 */
public interface FullTextSearchService<Dto extends BaseDto, Doc extends BaseDocument> extends CrudService<Dto, Doc> {

	/**
	 * Find all {@linkplain BaseDto}s by text matching the given words and sort the
	 * result for the given criteria
	 */
	@Nonnull
	default List<Dto> findAllByText(@Nullable Sort sort, @Nonnull String... words) {
		checkArgument(words != null && words.length > 0, "words cannot be null and its size must > 0");

		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		FullTextSearchRepository<Doc> repository = (FullTextSearchRepository<Doc>) metaData.getRepository();
		TextCriteria criteria = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingAny(words);
		List<Doc> documents = repository.findAllBy(criteria, sort);
		return metaData.getMapper().toDtoList(documents);
	}

	/**
	 * Find page {@linkplain BaseDto}s by text matching the given words and paginate
	 * the result for the given criteria
	 */
	@Nonnull
	default Page<Dto> findPageByText(@Nonnull Pageable pageable, @Nonnull String... words) {
		checkNotNull(pageable, "pageable cannot be null");
		checkArgument(words != null && words.length > 0, "words cannot be null and its size must > 0");

		ServiceMetaData<Dto, Doc> metaData = getServiceMetaData();
		FullTextSearchRepository<Doc> repository = (FullTextSearchRepository<Doc>) metaData.getRepository();
		TextCriteria criteria = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingAny(words);
		Page<Doc> documents = repository.findPageBy(criteria, pageable);
		return metaData.getMapper().toDtoPage(documents, pageable);
	}
}
