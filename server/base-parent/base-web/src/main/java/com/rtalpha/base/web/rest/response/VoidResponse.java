package com.rtalpha.base.web.rest.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Represents the REST response only containing the HTTP status without specific
 * body content
 * 
 * @author Ricky
 * @since Jun 10, 2017
 *
 */
public abstract class VoidResponse implements Callable<ResponseEntity<?>> {

	private final Callable callable;

	public VoidResponse(@Nonnull Callable callable) {
		checkNotNull(callable, "callable cannot be null");
		this.callable = callable;
	}

	@Override
	public ResponseEntity<?> call() throws Exception {
		callable.call();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Defines how to do the REST response without returning any body content
	 * 
	 * @author Ricky
	 * @since Jun 10, 2017
	 *
	 */
	@FunctionalInterface
	protected static interface Callable {

		void call() throws Exception;
	}
}
