package com.rtalpha.base.mongo.document;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

/**
 * @author Ricky
 * @since Jun 14, 2017
 */
@AutoProperty
public abstract class ImpermanentDocument extends BaseDocument {

	private static final long serialVersionUID = 1L;

	private DateTime effectiveTime;
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
