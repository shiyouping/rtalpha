package com.rtalpha.pms.app.repository;

import javax.annotation.Nullable;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtalpha.base.mongo.repository.MongoBaseRepository;
import com.rtalpha.pms.app.document.Multipart;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Repository
public interface MultipartRepository extends MongoBaseRepository<Multipart> {

	@Nullable
	@Query("{ 'md5': ?0, 'crc32': ?1, 'sha256': ?2 }")
	Multipart findOne(@Nullable String md5, @Nullable String crc32, @Nullable String sha256);
}
