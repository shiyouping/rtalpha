package com.rtalpha.ums.core.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtalpha.framework.core.mongo.repository.MongoBaseRepository;
import com.rtalpha.kernel.core.document.Customer;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Repository
public interface CustomerRepository extends MongoBaseRepository<Customer> {

	@Nonnull
	@Query("{ 'isActive': ?0 }")
	List<Customer> findAll(boolean isActive);

	@Nullable
	@Query("{ 'email': ?0 }")
	Customer findOneByEmail(@Nullable String email);

	@Nullable
	@Query("{ 'email': ?0, 'isActive': ?1 }")
	Customer findOneByEmail(@Nullable String email, boolean isActive);

	@Nullable
	@Query("{ 'id': ?0, 'isActive': ?1 }")
	Customer findOneById(@Nullable String id, boolean isActive);
}
