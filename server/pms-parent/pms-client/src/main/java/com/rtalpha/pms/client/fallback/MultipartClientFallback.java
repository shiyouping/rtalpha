package com.rtalpha.pms.client.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtalpha.base.remote.utility.ClientFallbackUtil;
import com.rtalpha.pms.client.MultipartClient;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author ricky.shi
 * @since 29 Mar 2018
 */
public class MultipartClientFallback implements MultipartClient {

	private static final Logger logger = LoggerFactory.getLogger(MultipartClientFallback.class);

	@Override
	public MultipartDto createOne(byte[] data, String fileType) {
		return ClientFallbackUtil.createFallbackDto(MultipartDto.class, logger,
				"Cannot create a MultipartDto(fileType={})", fileType);
	}

	@Override
	public MultipartDto createOne(MultipartDto dto) {
		return ClientFallbackUtil.createOne(dto, logger);
	}

	@Override
	public MultipartDto findOne(String md5, String crc32, String sha256) {
		return ClientFallbackUtil.createFallbackDto(MultipartDto.class, logger,
				"Cannot create a MultipartDto(md5={}, crc32={}, sha256={})", md5, crc32, sha256);
	}

	@Override
	public MultipartDto updateOne(MultipartDto dto) {
		return ClientFallbackUtil.updateOne(dto, logger);
	}
}
