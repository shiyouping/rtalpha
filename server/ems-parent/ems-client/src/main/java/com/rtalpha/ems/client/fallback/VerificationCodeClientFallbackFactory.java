package com.rtalpha.ems.client.fallback;

import org.springframework.stereotype.Component;

import com.rtalpha.base.remote.fallback.DefaultFallbackFactory;

/**
 * @author ricky.shi
 * @since 9 Apr 2018
 */
@Component
public class VerificationCodeClientFallbackFactory extends DefaultFallbackFactory<VerificationCodeClientFallback> {

	public VerificationCodeClientFallbackFactory() {
		super(new VerificationCodeClientFallback());
	}
}
