package com.rtalpha.base.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.rtalpha.base.jpa.entity.BaseEntity;

/**
 * The base interface for JPA CRUD operations on an {@link BaseEntity}.
 * <p>
 * Created by Ricky on 2016/11/26.
 */
@NoRepositoryBean
public interface JpaSimpleRepository<E extends BaseEntity>
		extends JpaRepository<E, String>, QueryDslPredicateExecutor<E> {
}
