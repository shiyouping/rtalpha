package com.rtalpha.base.jpa.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

/**
 * @author Ricky
 * @since Jun 14, 2017
 */
@AutoProperty
@MappedSuperclass
public abstract class ImpermanentEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "EFFECTIVE_TIME")
	private DateTime effectiveTime;
	@Column(name = "EXPIRY_TIME")
	private DateTime expiryTime;

	/**
	 * The effective time inclusive
	 */
	public DateTime getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * The effective time inclusive
	 */
	public void setEffectiveTime(DateTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * The expiry time exclusive
	 */
	public DateTime getExpiryTime() {
		return expiryTime;
	}

	/**
	 * The expiry time exclusive
	 */
	public void setExpiryTime(DateTime expiryTime) {
		this.expiryTime = expiryTime;
	}

}
