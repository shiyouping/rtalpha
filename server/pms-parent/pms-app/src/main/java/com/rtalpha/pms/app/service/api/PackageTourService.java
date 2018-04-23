package com.rtalpha.pms.app.service.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rtalpha.pms.app.document.PackageTour;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky Shi
 * @since Aug 16, 2017
 */
public interface PackageTourService extends ProductService<PackageTourDto, PackageTour> {

	@Nullable
	List<PackageTourDto> findAllByDeparture(@Nonnull String departure);

	@Nullable
	List<PackageTourDto> findAllByDeparture(@Nonnull String departure, @Nullable Sort sort);

	@Nullable
	List<PackageTourDto> findAllByDestination(@Nonnull String destination);

	@Nullable
	List<PackageTourDto> findAllByDestination(@Nonnull String destination, @Nullable Sort sort);

	@Nonnull
	Page<PackageTourDto> findPageByDeparture(@Nonnull String departure, @Nonnull Pageable pageable);

	@Nonnull
	Page<PackageTourDto> findPageByDestination(@Nonnull String destination, @Nonnull Pageable pageable);
}
