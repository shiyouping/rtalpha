package com.rtalpha.base.web.rest.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rtalpha.base.kernel.pojo.BasePojo;

/**
 * 
 * A response wrapper for a {@linkplain BasePojo}. Return 404 if the list is
 * empty.
 * 
 * @author Ricky
 * @since Jun 10, 2017
 *
 * @param <P>
 *            subclass of {@linkplain BasePojo}
 */
public abstract class SingleResponse<P extends BasePojo> implements Callable<ResponseEntity<P>> {

	private final Callable<P> callable;

	public SingleResponse(@Nonnull Callable<P> callable) {
		checkNotNull(callable, "callable cannot be null");
		this.callable = callable;
	}

	@Override
	public ResponseEntity<P> call() throws Exception {
		P body = callable.call();

		if (body == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * Defines how to get the {@linkplain BasePojo}
	 * 
	 * @author Ricky
	 * @since Jun 10, 2017
	 *
	 * @param <P>
	 *            subclass of {@linkplain BasePojo}
	 */
	@FunctionalInterface
	protected static interface Callable<P extends BasePojo> {

		@Nullable
		P call() throws Exception;
	}
}
