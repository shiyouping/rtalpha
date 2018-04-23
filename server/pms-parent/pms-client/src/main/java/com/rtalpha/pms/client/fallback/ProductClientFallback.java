package com.rtalpha.pms.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtalpha.base.kernel.utility.RawTypeDetector;
import com.rtalpha.base.remote.utility.ClientFallbackUtil;
import com.rtalpha.pms.client.ProductClient;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author ricky.shi
 * @since 29 Mar 2018
 */
public class ProductClientFallback<D extends ProductDto> implements ProductClient<D> {

	private static final Logger logger = LoggerFactory.getLogger(ProductClientFallback.class);
	private final Class<D> type = RawTypeDetector.getRawType(getClass());

	@Override
	public D createOne(D dto) {
		return ClientFallbackUtil.createOne(dto, logger);
	}

	@Override
	public void deleteOne(D dto) {
		ClientFallbackUtil.deleteOne(dto, logger);
	}

	@Override
	public void deleteOneById(String id) {
		ClientFallbackUtil.deleteOneById(id, logger);
	}

	@Override
	public List<D> findAll() {
		return ClientFallbackUtil.findAll(logger);
	}

	@Override
	public List<D> findAllByAgent(String agent) {
		return ClientFallbackUtil.createFallbackList(logger,
				"Cannot find all dtos by agent={}. Returning an empty list", agent);
	}

	@Override
	public List<D> findAllByText(String[] texts) {
		return ClientFallbackUtil.findAllByText(texts, logger);
	}

	@Override
	public D findOneById(String id) {
		return ClientFallbackUtil.findOneById(id, type, logger);
	}

	@Override
	public D findOneByNumber(Long number) {
		return ClientFallbackUtil.createFallbackDto(type, logger, "Cannot find dto with number={}.", number);
	}

	@Override
	public D updateOne(D dto) {
		return ClientFallbackUtil.updateOne(dto, logger);
	}
}
