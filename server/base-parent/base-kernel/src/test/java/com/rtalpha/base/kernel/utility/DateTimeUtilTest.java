package com.rtalpha.base.kernel.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @author Ricky
 * @since Jun 17, 2017
 *
 */
public class DateTimeUtilTest {

	@Test
	public void shouldFormatInUtc() {
		String dateAndTimeString = DateTimeUtil.toDateAndTimeString(DateTime.now());
		assertThat(dateAndTimeString).isNotNull();
		assertThat(dateAndTimeString).contains("+0000");

		String dateOnlyString = DateTimeUtil.toDateOnlyString(DateTime.now());
		assertThat(dateOnlyString).isNotNull();
		assertThat(dateOnlyString).contains("+0000");

		String timeOnlyString = DateTimeUtil.toTimeOnlyString(DateTime.now());
		assertThat(timeOnlyString).isNotNull();
		assertThat(timeOnlyString).contains("+0000");
	}
}