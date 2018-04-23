package com.rtalpha.pms.app.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rtalpha.base.core.cache.AutoNamingCacheResolver;
import com.rtalpha.base.core.constant.CacheName;
import com.rtalpha.base.mongo.service.api.SequenceService;
import com.rtalpha.pms.app.document.PackageTour;
import com.rtalpha.pms.app.mapper.PackageTourDtoDocMapper;
import com.rtalpha.pms.app.repository.PackageTourRepository;
import com.rtalpha.pms.app.service.api.PackageTourService;
import com.rtalpha.pms.app.validator.api.PackageTourValidator;
import com.rtalpha.pms.kernel.dto.PackageTourDto;
import com.rtalpha.pms.kernel.dto.PackageTourDto.RatingDto;

/**
 * @author Ricky Shi
 * @since Aug 16, 2017
 */
@Service
@CacheConfig(cacheNames = CacheName.PACKAGE_TOUR, cacheResolver = AutoNamingCacheResolver.NAME)
public class PackageTourServiceImpl extends ProductServiceImpl<PackageTourDto, PackageTour>
		implements PackageTourService {

	private static final Logger logger = LoggerFactory.getLogger(PackageTourServiceImpl.class);
	private final PackageTourRepository repository;
	private final PackageTourDtoDocMapper mapper;

	@Autowired
	public PackageTourServiceImpl(PackageTourRepository repository, PackageTourDtoDocMapper mapper,
			SequenceService sequenceService, PackageTourValidator validator) {
		super(repository, mapper, sequenceService, validator);
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	@Cacheable
	public List<PackageTourDto> findAllByDeparture(String departure) {
		logger.debug("Finding all package tour by departure={}...", departure);
		checkNotNull(departure, "departure cannot be null");

		List<PackageTour> packageTours = repository.findAllByDeparture(departure);
		return mapper.toDtoList(packageTours);
	}

	@Override
	@Cacheable
	public List<PackageTourDto> findAllByDeparture(String departure, Sort sort) {
		logger.debug("Finding all package tour by departure={}, sort={}...", departure, sort);
		checkNotNull(departure, "departure cannot be null");

		List<PackageTour> packageTours = repository.findAllByDeparture(departure, sort);
		return mapper.toDtoList(packageTours);
	}

	@Override
	@Cacheable
	public List<PackageTourDto> findAllByDestination(String destination) {
		logger.debug("Finding all package tour by destination={}...", destination);
		checkNotNull(destination, "destination cannot be null");

		List<PackageTour> packageTours = repository.findAllByDestination(destination);
		return mapper.toDtoList(packageTours);
	}

	@Override
	@Cacheable
	public List<PackageTourDto> findAllByDestination(String destination, Sort sort) {
		logger.debug("Finding all package tour by destination={}, sort={}...", destination, sort);
		checkNotNull(destination, "destination cannot be null");

		List<PackageTour> packageTours = repository.findAllByDestination(destination, sort);
		return mapper.toDtoList(packageTours);
	}

	@Override
	@Cacheable
	public Page<PackageTourDto> findPageByDeparture(String departure, Pageable pageable) {
		logger.debug("Finding package tour page by departure={}, pageable={}...", departure, pageable);
		checkNotNull(departure, "departure cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		Page<PackageTour> page = repository.findPageByDeparture(departure, pageable);
		return mapper.toDtoPage(page, pageable);
	}

	@Override
	@Cacheable
	public Page<PackageTourDto> findPageByDestination(String destination, Pageable pageable) {
		logger.debug("Finding package tour page by destination={}, pageable={}...", destination, pageable);
		checkNotNull(destination, "destination cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		Page<PackageTour> page = repository.findPageByDestination(destination, pageable);
		return mapper.toDtoPage(page, pageable);
	}

	@Override
	@CacheEvict(allEntries = true)
	public PackageTourDto save(PackageTourDto dto) {
		RatingDto rating = new RatingDto();
		rating.setDescription(0f);
		rating.setFood(0f);
		rating.setGuide(0f);
		rating.setHotel(0f);
		rating.setItinerary(0f);
		rating.setPrice(0f);
		rating.setService(0f);
		rating.setSight(0f);

		dto.setRating(rating);
		return super.save(dto);
	}
}