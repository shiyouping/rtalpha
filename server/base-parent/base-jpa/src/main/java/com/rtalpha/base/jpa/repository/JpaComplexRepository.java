package com.rtalpha.base.jpa.repository;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.PersistenceProvider;

import com.querydsl.jpa.EclipseLinkTemplates;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.OpenJPATemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rtalpha.base.jpa.entity.BaseEntity;

/**
 * The superclass of all JPA repositories that involve more than on
 * {@linkplain BaseEntity}
 *
 * @author Ricky
 * @since Nov 25, 2016
 */
public abstract class JpaComplexRepository {

	@Autowired
	private EntityManager entityManager;
	private PersistenceProvider provider;
	private JPAQueryFactory queryFactory;

	/**
	 * Do NOT call this method
	 */
	@PostConstruct
	public void init() {
		this.queryFactory = new JPAQueryFactory(entityManager);
		this.provider = PersistenceProvider.fromEntityManager(entityManager);
	}

	@Nonnull
	protected EntityManager entityManager() {
		return entityManager;
	}

	/**
	 * Get an instance of {@link JPAQuery}
	 */
	@Nonnull
	protected JPAQuery<?> query() {
		switch (provider) {
		case ECLIPSELINK:
			return new JPAQuery<Void>(entityManager, EclipseLinkTemplates.DEFAULT);
		case HIBERNATE:
			return new JPAQuery<Void>(entityManager, HQLTemplates.DEFAULT);
		case OPEN_JPA:
			return new JPAQuery<Void>(entityManager, OpenJPATemplates.DEFAULT);
		case GENERIC_JPA:
		default:
			return new JPAQuery<Void>(entityManager);
		}
	}

	/**
	 * Get an instance of {@link JPAQueryFactory}
	 */
	@Nonnull
	protected JPAQueryFactory queryFactory() {
		return queryFactory;
	}
}
