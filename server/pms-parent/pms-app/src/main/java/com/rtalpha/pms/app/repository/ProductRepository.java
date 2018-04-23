package com.rtalpha.pms.app.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.mongo.repository.FullTextSearchRepository;

/**
 * @author Ricky Shi
 * @since Aug 16, 2017
 */
@NoRepositoryBean
public interface ProductRepository<P extends BaseDocument> extends FullTextSearchRepository<P> {

	@Nonnull
	@Query("{ 'agent': ?0 }")
	List<P> findAllByAgent(@Nullable String agent);

	@Nonnull
	@Query("{ 'agent': ?0 }")
	List<P> findAllByAgent(@Nullable String agent, @Nullable Sort sort);

	@Nullable
	@Query("{ 'number': ?0 }")
	P findOneByNumber(@Nullable Long number);

	@Nonnull
	@Query("{ 'agent': ?0 }")
	Page<P> findPageByAgent(@Nullable String agent, @Nonnull Pageable pageable);
}
