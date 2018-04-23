package com.rtalpha.base.core.cache;

import org.springframework.cache.annotation.CacheEvict;

/**
 * Evicts the cache used by current implementations
 * 
 * @author Ricky
 * @since Apr 15, 2017
 *
 */
public interface CacheEvictor {

	/**
	 * Implementations could change evict condition (optional) by using Spring
	 * {@link CacheEvict} annotation
	 */
	void evictCurrentCache();
}
