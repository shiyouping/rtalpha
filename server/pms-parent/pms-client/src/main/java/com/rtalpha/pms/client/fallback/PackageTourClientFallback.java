package com.rtalpha.pms.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtalpha.base.remote.utility.ClientFallbackUtil;
import com.rtalpha.pms.client.PackageTourClient;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky
 * @since Apr 1, 2018
 *
 */
public class PackageTourClientFallback extends ProductClientFallback<PackageTourDto> implements PackageTourClient {

	private static final Logger logger = LoggerFactory.getLogger(PackageTourClientFallback.class);

	@Override
	public List<PackageTourDto> findAllByDeparture(String departure) {
		return ClientFallbackUtil.createFallbackList(logger, "Cannot find all PackageTourDto by departure", departure);
	}

	@Override
	public List<PackageTourDto> findAllByDestination(String destination) {
		return ClientFallbackUtil.createFallbackList(logger, "Cannot find all PackageTourDto by destination",
				destination);
	}
}
