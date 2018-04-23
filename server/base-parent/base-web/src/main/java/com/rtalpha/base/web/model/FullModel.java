package com.rtalpha.base.web.model;

import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * 
 * Base model used in HTTP POST request method and has full access to the
 * corresponding underlying {@linkplain BaseDto}
 * 
 * @author Ricky Shi
 * @since Jul 10, 2017
 */
@AutoProperty
public abstract class FullModel extends BaseModel {

	private static final long serialVersionUID = 1L;

}
