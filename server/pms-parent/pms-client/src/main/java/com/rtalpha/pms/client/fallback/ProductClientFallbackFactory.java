package com.rtalpha.pms.client.fallback;

import com.rtalpha.base.remote.fallback.DefaultFallbackFactory;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author ricky.shi
 * @param <D>
 * @since 28 Mar 2018
 */
public abstract class ProductClientFallbackFactory<D extends ProductDto>
		extends DefaultFallbackFactory<ProductClientFallback<D>> {

	public ProductClientFallbackFactory(ProductClientFallback<D> fallback) {
		super(fallback);
	}
}