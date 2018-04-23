package com.rtalpha.base.web.rest.response.internal;

import com.rtalpha.base.web.rest.response.VoidResponse;

/**
 * Used between internal systems
 * 
 * @author Ricky
 * @since Jul 29, 2017
 *
 */
public class InternalVoidResponse extends VoidResponse {

	public InternalVoidResponse(Callable callable) {
		super(callable);
	}

	public static interface Callable extends VoidResponse.Callable {
	}
}
