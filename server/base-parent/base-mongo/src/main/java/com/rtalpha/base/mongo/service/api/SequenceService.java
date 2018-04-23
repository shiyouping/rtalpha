package com.rtalpha.base.mongo.service.api;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * @author Ricky
 * @since May 17, 2017
 */
public interface SequenceService {

	/**
	 * Get a non-negative sequence number for the given sequence name
	 */
	@Nonnegative
	long getNextSequence(@Nonnull String sequenceName);
}
