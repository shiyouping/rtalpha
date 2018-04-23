package com.rtalpha.pms.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.pms.client.fallback.PackageTourClientFallbackFactory;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author ricky.shi
 * @since 29 Mar 2018
 */
@FeignClient(name = "${service-name.pms}", fallbackFactory = PackageTourClientFallbackFactory.class)
public interface PackageTourClient extends ProductClient<PackageTourDto>{

	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ALL + "ByDeparture")
	List<PackageTourDto> findAllByDeparture(@RequestParam String departure);

	@RequestMapping(method = RequestMethod.GET, path = RequestPath.METHOD_FIND_ALL + "ByDestination")
	List<PackageTourDto> findAllByDestination(@RequestParam String destination);
}
