package com.rtalpha.base.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.kernel.pojo.BasePojo;
import com.rtalpha.base.kernel.utility.UniqueId;

/**
 * The superclass of all entities representing tables
 * <p>
 * Created by Ricky on 16/10/18.
 */
@AutoProperty
@MappedSuperclass
public abstract class BaseEntity extends BasePojo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "CREATED_TIME")
	private DateTime createdTime;
	@Column(name = "UPDATED_TIME")
	private DateTime updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@PrePersist
	void setUniqueId() {
		id = UniqueId.get();
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
