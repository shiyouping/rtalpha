package com.rtalpha.base.web.utility;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ricky
 * @since Aug 5, 2017
 *
 */
public class PageableUtil {

	private PageableUtil() {
	}

	/**
	 * Converts a {@linkplain Pageable} to a {@linkplain MultiValueMap} used in
	 * {@linkplain RestTemplate}
	 * 
	 */
	@Nonnull
	public static MultiValueMap<String, String> toParameterMap(@Nonnull Pageable pageable) {
		checkNotNull(pageable, "pageable cannot be null");

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("page", String.valueOf(pageable.getPageNumber()));
		map.add("size", String.valueOf(pageable.getPageSize()));

		pageable.getSort().forEach((order) -> {
			map.add("sort", order.getProperty() + "," + order.getDirection().toString());
		});

		return map;
	}

	/**
	 * Converts a {@linkplain Pageable} to a plain string containing the pageable
	 * parameters, e.g. &page=0&size=3&sort=name,desc
	 * 
	 * @return the returned value already contains the sign of &
	 */
	@Nullable
	public static String toParameterString(@Nullable Pageable pageable) {
		if (pageable == null) {
			return StringUtils.EMPTY;
		}

		StringBuilder builder = new StringBuilder();
		MultiValueMap<String, String> map = toParameterMap(pageable);

		map.forEach((key, value) -> {
			value.forEach((item) -> {
				builder.append("&").append(key).append("=").append(item);
			});
		});

		return builder.toString();
	}
}
