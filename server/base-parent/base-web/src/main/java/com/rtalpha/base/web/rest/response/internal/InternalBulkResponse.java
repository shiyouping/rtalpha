package com.rtalpha.base.web.rest.response.internal;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.web.rest.response.BulkResponse;

/**
 * Used between internal systems
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <D>
 */
public class InternalBulkResponse<D extends BaseDto> extends BulkResponse<D> {

	public InternalBulkResponse(Callable<D> callable) {
		super(callable);
	}

	public static interface Callable<D extends BaseDto> extends BulkResponse.Callable<D> {
	}
}
