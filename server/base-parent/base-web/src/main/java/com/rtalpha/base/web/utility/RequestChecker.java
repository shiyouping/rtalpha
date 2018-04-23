package com.rtalpha.base.web.utility;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.rtalpha.base.web.exception.RestException;

/**
 * 
 * Check request paths, parameters and body
 * 
 * @author Ricky
 * @since Mar 17, 2018
 *
 */
public class RequestChecker {

	/**
	 * If the object to be checked is null, a {@linkplain RestException} will be
	 * thrown from this checker and later caught by the framework to return a 400
	 * Bad Request response to client
	 * 
	 */
	public static void checkNotNull(@Nullable Object object, @Nonnull String name) {
		if (object == null) {
			throw new RestException(HttpStatus.BAD_REQUEST, name + " cannot be null");
		}
	}

	/**
	 * If the object to be checked is empty (""), null or whitespace only, a
	 * {@linkplain RestException} will be thrown from this checker and later caught
	 * by the framework to return a 400 Bad Request response to client
	 */
	public static void checkNotBlankString(@Nullable String string, @Nonnull String name) {
		if (StringUtils.isBlank(string)) {
			throw new RestException(HttpStatus.BAD_REQUEST, name + " cannot be empty, null or whitespace only");
		}
	}

	/**
	 * If the collection to be checked is empty or null, a
	 * {@linkplain RestException} will be thrown from this checker and later caught
	 * by the framework to return a 400 Bad Request response to client
	 */
	public static <E> void checkNotEmptyCollection(@Nullable Collection<E> collection, @Nonnull String name) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new RestException(HttpStatus.BAD_REQUEST, name + " cannot be empty or null");
		}
	}

	/**
	 * If the request body is null, a {@linkplain RestException} will be thrown from
	 * this checker and later caught by the framework to return a 400 Bad Request
	 * response to client
	 * 
	 */
	public static void checkNotNullBody(@Nullable Object object) {
		checkNotNull(object, "Request body");
	}
}
