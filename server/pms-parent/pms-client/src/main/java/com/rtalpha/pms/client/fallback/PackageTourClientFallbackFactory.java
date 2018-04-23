package com.rtalpha.pms.client.fallback;

import org.springframework.stereotype.Component;

import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky
 * @since Apr 1, 2018
 *
 */
@Component
public class PackageTourClientFallbackFactory extends ProductClientFallbackFactory<PackageTourDto>{

	public PackageTourClientFallbackFactory(PackageTourClientFallback fallback) {
		super(fallback);
	}
}
