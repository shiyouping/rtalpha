package com.rtalpha.base.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.rtalpha.base.mongo.document.BaseDocument;

/**
 * 
 * Super class of all mongo repositories
 * 
 * @author Ricky
 * @since Dec 24, 2016
 *
 * @param <D>
 *            concrete document class that extends from
 *            {@linkplain BaseDocument}
 */
@NoRepositoryBean
public interface MongoBaseRepository<D extends BaseDocument>
		extends MongoRepository<D, String>, QueryDslPredicateExecutor<D> {
}