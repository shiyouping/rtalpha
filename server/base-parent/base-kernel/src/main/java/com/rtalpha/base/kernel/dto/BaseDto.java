package com.rtalpha.base.kernel.dto;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.kernel.pojo.BasePojo;

/**
 * The superclass of all DTOs
 * <p>
 * Created by Ricky on 16/10/18.
 */
@AutoProperty
public abstract class BaseDto extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String id;
	private DateTime createdTime;
	private DateTime updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(DateTime createdTime) {
		this.createdTime = createdTime;
	}

	public DateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(DateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
}
