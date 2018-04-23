package com.rtalpha.base.core.common;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * Count the occurrences for given Objects using the implementation of
 * {@linkplain Objects#deepEquals(Object, Object)}
 * 
 * @author Ricky
 * @since Aug 5, 2017
 *
 */
public class MultiCounter {

	private List<SingleCounter> counters = Lists.newArrayList();

	/**
	 * If the given object is deep equal to the key, its corresponding counter is
	 * increased by 1. This method supports chain operation.
	 * 
	 * @param object
	 *            to be compared to the key
	 */
	public MultiCounter count(@Nullable Object object) {
		if (object == null) {
			return this;
		}

		boolean exists = false;

		for (SingleCounter counter : counters) {
			if (Objects.deepEquals(counter.getKey(), object)) {
				exists = true;
				counter.increase();
			}
		}

		if (!exists) {
			SingleCounter singleCounter = new SingleCounter(object);
			singleCounter.increase();
			counters.add(singleCounter);
		}

		return this;
	}

	/**
	 * @return the count for the given object
	 */
	@Nonnegative
	public int getCount(@Nullable Object object) {
		if (object == null) {
			return 0;
		}

		for (SingleCounter counter : counters) {
			if (Objects.deepEquals(counter.getKey(), object)) {
				return counter.getCount();
			}
		}

		return 0;
	}

	/**
	 * @return the Objects and their corresponding counts
	 */
	@Nonnull
	public Map<Object, Integer> getCounts() {
		Map<Object, Integer> counts = Maps.newHashMapWithExpectedSize(counters.size());
		counters.forEach((counter) -> {
			counts.put(counter.getKey(), counter.getCount());
		});

		return counts;
	}

	/**
	 * @return true if one of the counts is greater than the given count
	 */
	public boolean isCountMoreThan(@Nonnegative int givenCount) {
		checkArgument(givenCount >= 0, "givenCount must >= 0");

		for (SingleCounter counter : counters) {
			if (counter.getCount() > givenCount) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return true if the count of the given Object is greater than the given count
	 */
	public boolean isCountMoreThan(@Nonnegative int givenCount, @Nonnull Object object) {
		checkArgument(givenCount >= 0, "givenCount must >= 0");
		checkNotNull(object, "object cannot be null");

		for (SingleCounter counter : counters) {
			if (Objects.deepEquals(counter.getKey(), object) && counter.getCount() > givenCount) {
				return true;
			}
		}

		return false;
	}
}
