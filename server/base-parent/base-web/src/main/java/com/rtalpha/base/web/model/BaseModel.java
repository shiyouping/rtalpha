package com.rtalpha.base.web.model;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.rtalpha.base.kernel.pojo.BasePojo;

/**
 * Base data transfer object only used in REST requests and responses
 * 
 * @author Ricky Shi
 * @since Jul 10, 2017
 */
@AutoProperty
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "clazz")
public abstract class BaseModel extends BasePojo {

	private static final long serialVersionUID = 1L;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
