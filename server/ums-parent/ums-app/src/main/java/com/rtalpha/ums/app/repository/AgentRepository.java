package com.rtalpha.ums.app.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtalpha.base.mongo.document.Agent;
import com.rtalpha.base.mongo.repository.MongoBaseRepository;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Repository
public interface AgentRepository extends MongoBaseRepository<Agent> {

	@Nonnull
	@Query("{ 'isActive': ?0 }")
	List<Agent> findAll(boolean isActive);

	@Nullable
	@Query("{ 'company': ?0 }")
	Agent findOneByCompany(@Nullable String company);

	@Nullable
	@Query("{ 'company': ?0, 'isActive': ?1 }")
	Agent findOneByCompany(@Nullable String company, boolean isActive);

	@Nullable
	@Query("{ 'email': ?0 }")
	Agent findOneByEmail(@Nullable String email);

	@Nullable
	@Query("{ 'email': ?0, 'isActive': ?1 }")
	Agent findOneByEmail(@Nullable String email, boolean isActive);

	@Nullable
	@Query("{ 'id': ?0, 'isActive': ?1 }")
	Agent findOneById(@Nullable String id, boolean isActive);

	@Nullable
	@Query("{ 'licenceNumber': ?0 }")
	Agent findOneByLicenceNumber(@Nullable String licenceNumber);

	@Nullable
	@Query("{ 'licenceNumber': ?0, 'isActive': ?1 }")
	Agent findOneByLicenceNumber(@Nullable String licenceNumber, boolean isActive);

	@Nullable
	@Query("{ 'name': ?0 }")
	Agent findOneByName(@Nullable String name);

	@Nullable
	@Query("{ 'name': ?0, 'isActive': ?1 }")
	Agent findOneByName(@Nullable String name, boolean isActive);
}