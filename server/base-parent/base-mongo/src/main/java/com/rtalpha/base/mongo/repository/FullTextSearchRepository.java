package com.rtalpha.base.mongo.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.NoRepositoryBean;

import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * 
 * Defines the common methods for full text search support
 * 
 * @author Ricky
 * @since Jun 8, 2017
 * @param <D>
 *            concrete document class that extends from
 *            {@linkplain BaseDocument}
 */
@NoRepositoryBean
public interface FullTextSearchRepository<D extends BaseDocument> extends MongoBaseRepository<D> {

	/**
	 * Execute a full-text search and define sorting dynamically
	 */
	@Nonnull
	List<D> findAllBy(@Nonnull TextCriteria criteria, @Nullable Sort sort);

	/**
	 * Paginate over a full-text search result
	 */
	@Nonnull
	Page<D> findPageBy(@Nonnull TextCriteria criteria, @Nonnull Pageable pageable);
}
