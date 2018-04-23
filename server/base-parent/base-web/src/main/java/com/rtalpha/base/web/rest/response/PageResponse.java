package com.rtalpha.base.web.rest.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rtalpha.base.kernel.pojo.BasePojo;

/**
 * A response wrapper for a {@linkplain Page} of {@linkplain BasePojo}s.
 * 
 * @author Ricky
 * @param <P>
 *            subclass of {@linkplain BasePojo}
 * @since Jun 10, 2017
 *
 */
public abstract class PageResponse<P extends BasePojo> implements Callable<ResponseEntity<Page<P>>> {

	private final Callable<P> callable;

	public PageResponse(@Nonnull Callable<P> callable) {
		checkNotNull(callable, "callable cannot be null");
		this.callable = callable;
	}

	@Override
	public ResponseEntity<Page<P>> call() throws Exception {
		Page<P> body = callable.call();
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * 
	 * Defines how to get the {@linkplain Page} of {@linkplain BasePojo}
	 * 
	 * @author Ricky
	 * @since Jun 10, 2017
	 *
	 * @param <P>
	 *            subclass of {@linkplain BasePojo}
	 */
	@FunctionalInterface
	protected static interface Callable<P extends BasePojo> {

		@Nonnull
		Page<P> call() throws Exception;
	}
}
