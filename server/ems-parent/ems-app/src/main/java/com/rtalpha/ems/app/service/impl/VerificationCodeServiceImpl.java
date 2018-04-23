package com.rtalpha.ems.app.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rtalpha.base.core.constant.CacheName;
import com.rtalpha.base.mongo.crud.AbstractCrudService;
import com.rtalpha.ems.app.document.VerificationCode;
import com.rtalpha.ems.app.mapper.VerificationCodeDtoDocMapper;
import com.rtalpha.ems.app.repository.VerificationCodeRepository;
import com.rtalpha.ems.app.service.api.VerificationCodeService;
import com.rtalpha.ems.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Service
@CacheConfig(cacheNames = CacheName.VERIFICATION_CODE)
public class VerificationCodeServiceImpl extends AbstractCrudService<VerificationCodeDto, VerificationCode>
		implements VerificationCodeService {

	private static final Logger logger = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

	private final VerificationCodeDtoDocMapper mapper;
	private final VerificationCodeRepository repository;

	@Autowired
	public VerificationCodeServiceImpl(@Nonnull VerificationCodeDtoDocMapper mapper,
			@Nonnull VerificationCodeRepository repository) {
		super(repository, mapper);
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	@CacheEvict(allEntries = true)
	public void evictCurrentCache() {
		logger.info("Evicting cache verificationCode...");
	}

	@Override
	@Nullable
	@Cacheable
	public List<VerificationCodeDto> findAll() {
		logger.debug("Finding all VerificationCodeDto...");
		return VerificationCodeService.super.findAll();
	}

	@Override
	@Nullable
	@Cacheable
	public VerificationCodeDto findLatestOneByEmail(@Nonnull String email) {
		logger.debug("Finding latest verification code by email={}", email);
		checkNotNull(email, "email cannnot be null");

		List<VerificationCode> codes = repository.findByEmail(email, new Sort(Direction.DESC, "createdTime"));
		if (CollectionUtils.isEmpty(codes)) {
			return null;
		}

		VerificationCode code = codes.get(0);
		return mapper.toDto(code);
	}

	@Override
	@Nullable
	@Cacheable
	public VerificationCodeDto findOne(@Nonnull String id) {
		checkNotNull(id, "id cannot be null.");
		return VerificationCodeService.super.findOne(id);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public VerificationCodeDto save(@Nonnull VerificationCodeDto dto) {
		logger.debug("Saving VerificationCodeDto={}", dto);
		return VerificationCodeService.super.save(dto);
	}
}