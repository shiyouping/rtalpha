package com.rtalpha.base.web.rest.response.internal;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.web.rest.response.PageResponse;

/**
 * Used between internal systems
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <D>
 */
public class InternalPageResponse<D extends BaseDto> extends PageResponse<D> {

	public InternalPageResponse(Callable<D> callable) {
		super(callable);
	}

	public static interface Callable<D extends BaseDto> extends PageResponse.Callable<D> {
	}
}