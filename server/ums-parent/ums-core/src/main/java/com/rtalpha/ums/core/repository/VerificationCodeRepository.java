package com.rtalpha.ums.core.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtalpha.framework.core.mongo.repository.MongoBaseRepository;
import com.rtalpha.ums.core.document.VerificationCode;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Repository
public interface VerificationCodeRepository extends MongoBaseRepository<VerificationCode> {

	@Nonnull
	@Query("{ 'email' : ?0 }")
	List<VerificationCode> findByEmail(@Nullable String email, @Nullable Sort sort);
}
