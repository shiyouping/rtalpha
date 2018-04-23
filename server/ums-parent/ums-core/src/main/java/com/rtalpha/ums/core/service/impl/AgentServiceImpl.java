package com.rtalpha.ums.core.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rtalpha.framework.core.crud.AbstractCrudService;
import com.rtalpha.framework.core.security.EncryptionService;
import com.rtalpha.kernel.core.constant.CacheName;
import com.rtalpha.kernel.core.document.Agent;
import com.rtalpha.ums.core.mapper.AgentDtoDocMapper;
import com.rtalpha.ums.core.repository.AgentRepository;
import com.rtalpha.ums.core.service.api.AgentService;
import com.rtalpha.ums.core.validator.api.AgentValidator;
import com.rtalpha.ums.remote.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Service
@CacheConfig(cacheNames = CacheName.AGENT)
public class AgentServiceImpl extends AbstractCrudService<AgentDto, Agent> implements AgentService {

	private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);

	private final AgentDtoDocMapper mapper;
	private final AgentValidator validator;
	private final AgentRepository repository;
	private final EncryptionService encryptionService;

	@Autowired
	public AgentServiceImpl(@Nonnull AgentDtoDocMapper mapper, @Nonnull AgentRepository repository,
			@Nonnull EncryptionService encryptionService, @Nonnull AgentValidator validator) {

		super(repository, mapper);

		checkNotNull(encryptionService, "encryptionService cannot be null");
		checkNotNull(validator, "validator cannot be null");

		this.mapper = mapper;
		this.validator = validator;
		this.repository = repository;
		this.encryptionService = encryptionService;
	}

	@Override
	@CacheEvict(allEntries = true)
	public void evictCurrentCache() {
		logger.info("Evicting cache agent...");
	}

	@Nullable
	@Override
	@Cacheable
	public List<AgentDto> findAll() {
		logger.debug("Finding all Agents...");
		return AgentService.super.findAll();
	}

	@Nullable
	@Override
	@Cacheable
	public List<AgentDto> findAll(boolean isActive) {
		logger.debug("Finding all Agents with isActive={}...", isActive);
		List<Agent> agents = repository.findAll(isActive);
		return mapper.toDtoList(agents);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOne(@Nonnull String id) {
		logger.debug("Finding Agent with id={}", id);
		return AgentService.super.findOne(id);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByCompany(@Nonnull String company) {
		logger.debug("Finding agent with company={}", company);
		checkNotNull(company, "company cannot be null");

		Agent agent = repository.findOneByCompany(company);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByCompany(@Nonnull String company, boolean isActive) {
		logger.debug("Finding agent with company={} and isActive={}", company, isActive);
		checkNotNull(company, "company cannot be null");

		Agent agent = repository.findOneByCompany(company, isActive);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByEmail(@Nonnull String email) {
		logger.debug("Finding Agent by email={}", email);
		checkNotNull(email, "email cannot be null.");

		Agent agent = repository.findOneByEmail(email);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByEmail(@Nonnull String email, boolean isActive) {
		logger.debug("Finding Agent by email={} and isActive={}", email, isActive);
		checkNotNull(email, "email cannot be null.");

		Agent agent = repository.findOneByEmail(email, isActive);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneById(@Nonnull String id, boolean isActive) {
		logger.debug("Finding Agent by id={} and isActive={}", id, isActive);
		checkNotNull(id, "id cannot be null.");

		Agent agent = repository.findOneById(id, isActive);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByLicenceNumber(@Nonnull String licenceNumber) {
		logger.debug("Finding agent by licenceNumber={}", licenceNumber);
		checkNotNull(licenceNumber, "licenceNumber cannot be null");

		Agent agent = repository.findOneByLicenceNumber(licenceNumber);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByLicenceNumber(@Nonnull String licenceNumber, boolean isActive) {
		logger.debug("Finding agent by licenceNumber={} and isActive={}", licenceNumber, isActive);
		checkNotNull(licenceNumber, "licenceNumber cannot be null");

		Agent agent = repository.findOneByLicenceNumber(licenceNumber, isActive);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByName(@Nonnull String name) {
		logger.debug("Finding agent by name={}", name);
		checkNotNull(name, "name cannot be null");

		Agent agent = repository.findOneByName(name);
		return mapper.toDto(agent);
	}

	@Nullable
	@Override
	@Cacheable
	public AgentDto findOneByName(@Nonnull String name, boolean isActive) {
		logger.debug("Finding agent by name={} and isActive", name, isActive);
		checkNotNull(name, "name cannot be null");

		Agent agent = repository.findOneByName(name, isActive);
		return mapper.toDto(agent);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public AgentDto save(@Nonnull AgentDto dto) {
		logger.debug("Saving AgentDto={}", dto);

		validator.validateSave(dto);
		String password = encryptionService.encrypt(dto.getPassword());
		dto.setPassword(password);
		dto.setIsActive(false);
		return AgentService.super.save(dto);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public AgentDto update(@Nonnull AgentDto dto) {
		logger.debug("Updating AgentDto={}", dto);

		validator.validateUpdate(dto);
		String password = encryptionService.encrypt(dto.getPassword());
		dto.setPassword(password);
		return AgentService.super.update(dto);
	}
}
