package com.rtalpha.base.web.rest.response.internal;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.web.rest.response.SingleResponse;

/**
 * Used between internal systems
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <D>
 */
public class InternalSingleResponse<D extends BaseDto> extends SingleResponse<D> {

	public InternalSingleResponse(Callable<D> callable) {
		super(callable);
	}

	public static interface Callable<D extends BaseDto> extends SingleResponse.Callable<D> {
	}
}
