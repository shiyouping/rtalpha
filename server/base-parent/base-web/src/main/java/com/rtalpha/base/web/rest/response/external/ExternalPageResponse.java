package com.rtalpha.base.web.rest.response.external;

import com.rtalpha.base.web.model.BaseModel;
import com.rtalpha.base.web.rest.response.PageResponse;

/**
 * Used between external clients and internal server
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 * @param <M>
 */
public class ExternalPageResponse<M extends BaseModel> extends PageResponse<M> {

	public ExternalPageResponse(Callable<M> callable) {
		super(callable);
	}

	@FunctionalInterface
	public static interface Callable<M extends BaseModel> extends PageResponse.Callable<M> {
	}
}
