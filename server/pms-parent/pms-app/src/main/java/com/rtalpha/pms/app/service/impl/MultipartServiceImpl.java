package com.rtalpha.pms.app.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.rtalpha.base.core.constant.CacheName;
import com.rtalpha.base.kernel.utility.FileUtil;
import com.rtalpha.base.kernel.utility.HashUtil;
import com.rtalpha.base.mongo.crud.AbstractCrudService;
import com.rtalpha.base.web.config.properties.BaseWebProperties;
import com.rtalpha.pms.app.document.Multipart;
import com.rtalpha.pms.app.mapper.MultipartDtoDocMapper;
import com.rtalpha.pms.app.repository.MultipartRepository;
import com.rtalpha.pms.app.service.api.MultipartService;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author Ricky
 * @since May 18, 2017
 */
@Service
@RefreshScope
@CacheConfig(cacheNames = CacheName.MULTIPART)
public class MultipartServiceImpl extends AbstractCrudService<MultipartDto, Multipart> implements MultipartService {

	private static final Logger logger = LoggerFactory.getLogger(MultipartServiceImpl.class);

	private final MultipartDtoDocMapper mapper;
	private final MultipartRepository repository;
	private final String baseLocation;

	@Autowired
	public MultipartServiceImpl(@Nonnull MultipartDtoDocMapper mapper, @Nonnull MultipartRepository repository,
			@Nonnull BaseWebProperties properties) {
		super(repository, mapper);
		checkNotNull(properties, "properties cannot be null");

		this.mapper = mapper;
		this.repository = repository;
		this.baseLocation = properties.getHttp().getMultipart().getBaseLocation();
	}

	@Override
	@CacheEvict(allEntries = true)
	public void evictCurrentCache() {
		logger.info("Evicting cache multipart...");
	}

	@Override
	@Nullable
	@Cacheable
	public MultipartDto findOne(@Nonnull String md5, @Nonnull String crc32, @Nonnull String sha256) {
		logger.debug("Finding Multipart. md5={}, crc32={}, sha256={}", md5, crc32, sha256);
		checkNotNull(md5, "md5 cannot be null");
		checkNotNull(crc32, "crc32 cannot be null");
		checkNotNull(sha256, "sha256 cannot be null");

		Multipart multipart = repository.findOne(md5, crc32, sha256);
		return mapper.toDto(multipart);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public MultipartDto save(byte[] data, String fileType) throws IOException {
		logger.debug("Saving data with fileType={}...", fileType);
		checkNotNull(data, "data cannot be null");
		checkNotNull(fileType, "fileType cannot be null");

		String crc32 = HashUtil.getCrc32(data);
		String md5 = HashUtil.getMd5(data);
		String sha256 = HashUtil.getSha256(data);

		MultipartDto multipart = findOne(md5, crc32, sha256);

		if (multipart != null) {
			String location = multipart.getLocation();
			if (!FileUtil.exists(baseLocation + location)) {
				String newLocation = saveFile(data, md5, crc32, sha256, fileType);
				multipart.setLocation(newLocation);
				multipart = update(multipart);
			}

			return multipart;
		}

		String location = saveFile(data, md5, crc32, sha256, fileType);
		return persist(crc32, md5, sha256, location);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public MultipartDto save(@Nonnull MultipartDto dto) {
		logger.debug("Saving multipart dto={} ...", dto);
		return MultipartService.super.save(dto);
	}

	@Nonnull
	@Override
	@CacheEvict(allEntries = true)
	public MultipartDto update(@Nonnull MultipartDto dto) {
		logger.debug("Updating multipart dto={} ...", dto);
		return MultipartService.super.update(dto);
	}

	private MultipartDto persist(String crc32, String md5, String sha256, String multipartLocation) {
		MultipartDto newMultipart = new MultipartDto();
		newMultipart.setCrc32(crc32);
		newMultipart.setMd5(md5);
		newMultipart.setSha256(sha256);
		newMultipart.setLocation(multipartLocation);
		return save(newMultipart);
	}

	private String saveFile(byte[] data, String md5, String crc32, String sha256, String fileType) throws IOException {
		DateTime now = DateTime.now();
		StringBuilder time = new StringBuilder().append(now.getYear()).append(now.getDayOfYear());

		StringBuilder fileNameBuilder = new StringBuilder(crc32).append(md5).append(sha256).append(".")
				.append(fileType);
		StringBuilder relativeDirectoryBuilder = new StringBuilder(File.separator).append(fileType)
				.append(File.separator).append(time);
		StringBuilder fullDirectoryBuilder = new StringBuilder(baseLocation).append(relativeDirectoryBuilder);

		FileUtil.write(data, fullDirectoryBuilder.toString(), fileNameBuilder.toString());

		return relativeDirectoryBuilder.append(File.separator).append(fileNameBuilder).toString();
	}
}