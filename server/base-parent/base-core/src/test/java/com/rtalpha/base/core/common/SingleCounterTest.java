package com.rtalpha.base.core.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author Ricky
 * @since Aug 5, 2017
 *
 */
public class SingleCounterTest {

	@Test
	public void shouldGetCount() {
		String key1 = "key1";
		SingleCounter counter1 = new SingleCounter(key1);
		counter1.count(key1);
		counter1.count(key1);
		assertThat(counter1.getCount()).isEqualTo(2);

		Object key2 = new Object();
		SingleCounter counter2 = new SingleCounter(key2);
		counter2.count(key2);
		counter2.count(new Object());
		assertThat(counter2.getCount()).isEqualTo(1);
	}
}
