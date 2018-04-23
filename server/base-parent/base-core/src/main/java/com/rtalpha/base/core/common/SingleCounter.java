package com.rtalpha.base.core.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Count the occurrence for a given Object using the implementation of
 * {@linkplain Objects#deepEquals(Object, Object)}
 * 
 * @author Ricky
 * @since Aug 5, 2017
 *
 */
public class SingleCounter {

	private int count;
	private final Object key;

	public SingleCounter(@Nonnull Object key) {
		checkNotNull(key, "key cannot be null");
		this.key = key;
		count = 0;
	}

	/**
	 * If the given object is deep equal to the key, the counter is increased by 1.
	 * This method supports chain operation.
	 * 
	 * @param object
	 *            to be compared to the key
	 */
	public SingleCounter count(@Nullable Object object) {
		if (Objects.deepEquals(key, object)) {
			count++;
		}

		return this;
	}

	/**
	 * @return the count for the given object
	 */
	@Nonnegative
	public int getCount() {
		return count;
	}

	public Object getKey() {
		return key;
	}

	/**
	 * Manually increase the count
	 */
	public void increase() {
		count++;
	}
}
