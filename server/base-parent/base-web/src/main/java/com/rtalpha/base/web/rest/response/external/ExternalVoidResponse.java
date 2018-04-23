package com.rtalpha.base.web.rest.response.external;

import com.rtalpha.base.web.rest.response.VoidResponse;

/**
 * Used between external clients and internal server
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 */
public class ExternalVoidResponse extends VoidResponse {

	public ExternalVoidResponse(Callable callable) {
		super(callable);
	}

	@FunctionalInterface
	public static interface Callable extends VoidResponse.Callable {
	}
}
