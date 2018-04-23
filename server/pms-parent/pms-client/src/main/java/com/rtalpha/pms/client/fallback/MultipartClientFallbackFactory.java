package com.rtalpha.pms.client.fallback;

import org.springframework.stereotype.Component;

import com.rtalpha.base.remote.fallback.DefaultFallbackFactory;

/**
 * @author ricky.shi
 * @since 28 Mar 2018
 */
@Component
public class MultipartClientFallbackFactory extends DefaultFallbackFactory<MultipartClientFallback> {

	public MultipartClientFallbackFactory() {
		super(new MultipartClientFallback());
	}
}