package com.rtalpha.base.mongo.document;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.rtalpha.base.kernel.pojo.BasePojo;

/**
 * This is the super class of all Mongodb document class
 * 
 * @author Ricky
 * @since Dec 24, 2016
 *
 */
@AutoProperty
public abstract class BaseDocument extends BasePojo {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
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
