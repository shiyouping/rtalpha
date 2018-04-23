package com.rtalpha.base.kernel.utility;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Create date/time or Convert between Joda date/time and {@linkplain String}
 * 
 * @author Ricky
 * @since Feb 23, 2017
 */
public class DateTimeUtil {

	private static final String PATTERN_DATE_AND_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final String PATTERN_DATE_ONLY = "yyyy-MM-ddZ";
	private static final String PATTERN_TIME_ONLY = "HH:mm:ss.SSSZ";
	private static final String PATTERN_YEAR_MONTH_DAY = "yyyyMMdd";
	private static final String PATTERN_YEAR_MONTH = "yyyyMM";
	private static final String PATTERN_DAY = "dd";

	/**
	 * yyyy-MM-dd HH:mm:ss.SSS Z
	 */
	public static final DateTimeFormatter FORMATTER_DATE_AND_TIME = DateTimeFormat.forPattern(PATTERN_DATE_AND_TIME)
			.withZoneUTC();
	/**
	 * yyyy-MM-dd Z
	 */
	public static final DateTimeFormatter FORMATTER_DATE_ONLY = DateTimeFormat.forPattern(PATTERN_DATE_ONLY)
			.withZoneUTC();
	/**
	 * HH:mm:ss.SSS Z
	 */
	public static final DateTimeFormatter FORMATTER_TIME_ONLY = DateTimeFormat.forPattern(PATTERN_TIME_ONLY)
			.withZoneUTC();
	/**
	 * yyyyMMdd
	 */
	public static final DateTimeFormatter FORMATTER_YEAR_MONTH_DAY = DateTimeFormat.forPattern(PATTERN_YEAR_MONTH_DAY)
			.withZoneUTC();
	/**
	 * yyyyMM
	 */
	public static final DateTimeFormatter FORMATTER_YEAR_MONTH = DateTimeFormat.forPattern(PATTERN_YEAR_MONTH)
			.withZoneUTC();
	/**
	 * dd
	 */
	public static final DateTimeFormatter FORMATTER_DAY = DateTimeFormat.forPattern(PATTERN_DAY).withZoneUTC();

	private DateTimeUtil() {
	}

	/**
	 * Convert {@linkplain DateTime} to String like "2017-11-23 11:34:23.123"
	 * 
	 */
	@Nonnull
	public static String toDateAndTimeString(@Nonnull DateTime dateTime) {
		checkNotNull(dateTime, "dateTime cannot be null.");
		return FORMATTER_DATE_AND_TIME.print(dateTime);
	}

	/**
	 * Convert {@linkplain DateTime} to String like "2017-11-23"
	 * 
	 */
	@Nonnull
	public static String toDateOnlyString(@Nonnull DateTime dateTime) {
		checkNotNull(dateTime, "dateTime cannot be null.");
		return FORMATTER_DATE_ONLY.print(dateTime);
	}

	/**
	 * Convert {@linkplain DateTime} to String like "11:34:23.123"
	 * 
	 */
	@Nonnull
	public static String toTimeOnlyString(@Nonnull DateTime dateTime) {
		checkNotNull(dateTime, "dateTime cannot be null.");
		return FORMATTER_TIME_ONLY.print(dateTime);
	}

	@Nonnull
	public static String toSpecificFormatString(@Nonnull DateTime dateTime, @Nonnull DateTimeFormatter formatter) {
		checkNotNull(dateTime, "dateTime cannot be null.");
		checkNotNull(formatter, "formatter cannot be null.");
		return formatter.print(dateTime);
	}
}
