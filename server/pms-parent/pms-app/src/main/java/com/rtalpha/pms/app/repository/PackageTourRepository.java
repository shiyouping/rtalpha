package com.rtalpha.pms.app.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtalpha.pms.app.document.PackageTour;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@Repository
public interface PackageTourRepository extends ProductRepository<PackageTour> {

	@Nonnull
	@Query("{ 'departure': ?0 }")
	List<PackageTour> findAllByDeparture(@Nullable String departure);

	@Nonnull
	@Query("{ 'departure': ?0 }")
	List<PackageTour> findAllByDeparture(@Nullable String departure, @Nullable Sort sort);

	@Nonnull
	@Query("{ 'departure': ?0 }")
	Page<PackageTour> findPageByDeparture(@Nullable String departure, @Nonnull Pageable pageable);

	@Nonnull
	@Query("{ 'destination': ?0 }")
	List<PackageTour> findAllByDestination(@Nullable String destination);

	@Nonnull
	@Query("{ 'destination': ?0 }")
	List<PackageTour> findAllByDestination(@Nullable String destination, @Nullable Sort sort);

	@Nonnull
	@Query("{ 'destination': ?0 }")
	Page<PackageTour> findPageByDestination(@Nullable String destination, @Nonnull Pageable pageable);
}
