package com.rtalpha.base.remote.fallback;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.hystrix.FallbackFactory;

/**
 * Default fallback factory to get the exception cause which makes fallback
 * trigger when using feign clients, and provide the fallback methods for
 * corresponding feign clients
 * 
 * @author ricky.shi
 * @since 29 Mar 2018
 * @param <F>
 *            the feign interface type
 * @param <D>
 */
public class DefaultFallbackFactory<F> implements FallbackFactory<F> {

	private static final Logger logger = LoggerFactory.getLogger(DefaultFallbackFactory.class);
	private final F fallback;

	public DefaultFallbackFactory(@Nonnull F fallback) {
		checkNotNull(fallback, "fallback cannot be null");
		this.fallback = fallback;
	}

	@Override
	public F create(Throwable cause) {
		logger.error(String.format("Feign client fallback due to %s", cause.getMessage()), cause);
		return fallback;
	}
}