package com.rtalpha.base.web.rest.response.external;

import com.rtalpha.base.web.model.BaseModel;
import com.rtalpha.base.web.rest.response.SingleResponse;

/**
 * Used between external clients and internal server
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <M>
 */
public class ExternalSingleResponse<M extends BaseModel> extends SingleResponse<M> {

	public ExternalSingleResponse(Callable<M> callable) {
		super(callable);
	}

	@FunctionalInterface
	public static interface Callable<M extends BaseModel> extends SingleResponse.Callable<M> {
	}
}
