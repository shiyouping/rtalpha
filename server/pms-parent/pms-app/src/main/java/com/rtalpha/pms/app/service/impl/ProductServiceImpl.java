package com.rtalpha.pms.app.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rtalpha.base.core.cache.AutoNamingCacheResolver;
import com.rtalpha.base.core.constant.SequenceName;
import com.rtalpha.base.mongo.crud.AbstractCrudService;
import com.rtalpha.base.mongo.service.api.SequenceService;
import com.rtalpha.base.web.security.AuthenticatedUser;
import com.rtalpha.pms.app.document.Product;
import com.rtalpha.pms.app.mapper.ProductDtoDocMapper;
import com.rtalpha.pms.app.repository.ProductRepository;
import com.rtalpha.pms.app.service.api.ProductService;
import com.rtalpha.pms.app.validator.api.ProductValidator;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@CacheConfig(cacheResolver = AutoNamingCacheResolver.NAME)
public abstract class ProductServiceImpl<Dto extends ProductDto, Doc extends Product>
		extends AbstractCrudService<Dto, Doc> implements ProductService<Dto, Doc> {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductDtoDocMapper<Dto, Doc> mapper;
	private final ProductRepository<Doc> repository;
	private final SequenceService sequenceService;
	private final ProductValidator<Dto> validator;

	public ProductServiceImpl(ProductRepository<Doc> repository, ProductDtoDocMapper<Dto, Doc> mapper,
			@Nonnull SequenceService sequenceService, @Nonnull ProductValidator<Dto> validator) {

		super(repository, mapper);

		checkNotNull(sequenceService, "sequenceService cannot be null");
		checkNotNull(validator, "validator cannot be null");

		this.mapper = mapper;
		this.validator = validator;
		this.repository = repository;
		this.sequenceService = sequenceService;
	}

	@Override
	@CacheEvict(allEntries = true)
	public void delete(Dto dto) {
		logger.debug("Deleting dto={}", dto);
		ProductService.super.delete(dto);
	}

	@Override
	@CacheEvict(allEntries = true)
	public void delete(String id) {
		logger.debug("Deleting product with id={}", id);
		ProductService.super.delete(id);
	}

	@Override
	@CacheEvict(allEntries = true)
	public void evictCurrentCache() {
		logger.info("Evicting cache product...");
	}

	@Override
	@Cacheable
	public List<Dto> findAll() {
		logger.debug("Finding all products...");
		return ProductService.super.findAll();
	}

	@Override
	@Cacheable
	public List<Dto> findAllByAgent(String agent) {
		logger.debug("Finding all by agent={}", agent);
		checkNotNull(agent, "agent cannot be null");

		List<Doc> products = repository.findAllByAgent(agent);
		return mapper.toDtoList(products);
	}

	@Override
	public List<Dto> findAllByAgent(String agent, Sort sort) {
		logger.debug("Finding all by agent={}, sort={}", agent, sort);
		checkNotNull(agent, "agent cannot be null");

		List<Doc> products = repository.findAllByAgent(agent, sort);
		return mapper.toDtoList(products);
	}

	@Override
	@Cacheable
	public List<Dto> findAllByText(Sort sort, String... words) {
		logger.debug("Finding products by sort={}, and words={}", sort, words);
		return ProductService.super.findAllByText(sort, words);
	}

	@Override
	@Cacheable
	public Dto findOne(String id) {
		logger.debug("Finding product with id={}", id);
		return ProductService.super.findOne(id);
	}

	@Override
	@Cacheable
	public Dto findOneByNumber(Long number) {
		logger.debug("Finding product by number={}", number);
		checkNotNull(number, "number cannot be null");
		Doc product = repository.findOneByNumber(number);
		return mapper.toDto(product);
	}

	@Override
	@Cacheable
	public Page<Dto> findPageByAgent(String agent, Pageable pageable) {
		logger.debug("Finding all by agent={}, pageable={}", agent, pageable);
		checkNotNull(agent, "agent cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		Page<Doc> products = repository.findPageByAgent(agent, pageable);
		return mapper.toDtoPage(products, pageable);
	}

	@Override
	@Cacheable
	public Page<Dto> findPageByText(Pageable pageable, String... words) {
		logger.debug("Finding products by pageable={}, and words={}", pageable, words);
		return ProductService.super.findPageByText(pageable, words);
	}

	@Override
	@CacheEvict(allEntries = true)
	public Dto save(Dto dto) {
		logger.debug("Saving product={}", dto);
		checkNotNull(dto, "dto cannot be null");

		dto.setNumber(sequenceService.getNextSequence(SequenceName.PRODUCT));
		dto.setAgent(AuthenticatedUser.getCurrentUser());
		dto.setNumberOfDeal(0);

		validator.validateSave(dto);
		return ProductService.super.save(dto);
	}

	@Override
	@CacheEvict(allEntries = true)
	public Dto update(Dto dto) {
		logger.debug("Updating product={}", dto);
		validator.validateUpdate(dto);
		return ProductService.super.update(dto);
	}
}