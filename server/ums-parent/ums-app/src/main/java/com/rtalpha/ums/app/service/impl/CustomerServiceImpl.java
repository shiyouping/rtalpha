package com.rtalpha.ums.app.service.impl;

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

import com.rtalpha.base.core.constant.CacheName;
import com.rtalpha.base.mongo.crud.AbstractCrudService;
import com.rtalpha.base.mongo.document.Customer;
import com.rtalpha.base.web.security.EncryptionService;
import com.rtalpha.ums.app.mapper.CustomerDtoDocMapper;
import com.rtalpha.ums.app.repository.CustomerRepository;
import com.rtalpha.ums.app.service.api.CustomerService;
import com.rtalpha.ums.app.validator.api.CustomerValidator;
import com.rtalpha.ums.kenel.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Service
@CacheConfig(cacheNames = CacheName.CUSTOMER)
public class CustomerServiceImpl extends AbstractCrudService<CustomerDto, Customer> implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private final CustomerDtoDocMapper mapper;
	private final CustomerValidator validator;
	private final CustomerRepository repository;
	private final EncryptionService encryptionService;

	@Autowired
	public CustomerServiceImpl(@Nonnull CustomerDtoDocMapper mapper, @Nonnull CustomerRepository repository,
			@Nonnull EncryptionService encryptionService, @Nonnull CustomerValidator validator) {

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
		logger.info("Evicting cache customer...");
	}

	@Nullable
	@Override
	@Cacheable
	public List<CustomerDto> findAll() {
		logger.debug("Finding all customers...");
		return CustomerService.super.findAll();
	}

	@Nullable
	@Override
	@Cacheable
	public List<CustomerDto> findAll(boolean isActive) {
		logger.debug("Finding all customers with isActive={}...", isActive);
		List<Customer> customers = repository.findAll(isActive);
		return mapper.toDtoList(customers);
	}

	@Nullable
	@Override
	@Cacheable
	public CustomerDto findOne(@Nonnull String id) {
		logger.debug("Finding customer with id={}", id);
		return CustomerService.super.findOne(id);
	}

	@Nullable
	@Override
	@Cacheable
	public CustomerDto findOneByEmail(@Nonnull String email) {
		logger.debug("Finding customer by email={}", email);
		checkNotNull(email, "email cannot be null.");

		Customer customer = repository.findOneByEmail(email);
		return mapper.toDto(customer);
	}

	@Nullable
	@Override
	@Cacheable
	public CustomerDto findOneByEmail(@Nonnull String email, boolean isActive) {
		logger.debug("Finding customer by email={} and isActive={}", email, isActive);
		checkNotNull(email, "email cannot be null.");

		Customer customer = repository.findOneByEmail(email, isActive);
		return mapper.toDto(customer);
	}

	@Nullable
	@Override
	@Cacheable
	public CustomerDto findOneById(@Nonnull String id, boolean isActive) {
		logger.debug("Finding customer by id={} and isActive={}", id, isActive);
		checkNotNull(id, "id cannot be null.");

		Customer customer = repository.findOneById(id, isActive);
		return mapper.toDto(customer);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public CustomerDto save(@Nonnull CustomerDto dto) {
		logger.debug("Saving CustomerDto={}", dto);

		validator.validateSave(dto);
		String password = encryptionService.encrypt(dto.getPassword());
		dto.setPassword(password);
		dto.setIsActive(false);
		return CustomerService.super.save(dto);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public CustomerDto update(@Nonnull CustomerDto dto) {
		logger.debug("Updating CustomerDto={}", dto);

		validator.validateUpdate(dto);
		String password = encryptionService.encrypt(dto.getPassword());
		dto.setPassword(password);
		return CustomerService.super.update(dto);
	}
}
