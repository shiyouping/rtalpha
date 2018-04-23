package com.rtalpha.base.core.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MultiCounterTest {

	@Test
	public void shouldCount() {
		Object object1 = new Object();
		Object object2 = new Object();
		Object object3 = new Object();

		MultiCounter counter = new MultiCounter();
		counter.count(object1).count(object1).count(object1);
		counter.count(object2).count(object2);
		counter.count(object3);

		assertThat(counter.getCount(object1)).isEqualByComparingTo(3);
		assertThat(counter.getCount(object2)).isEqualByComparingTo(2);
		assertThat(counter.getCount(object3)).isEqualByComparingTo(1);
	}
}