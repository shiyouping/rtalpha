package com.rtalpha.base.web.model;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

/**
 * @author Ricky Shi
 * @since Jul 31, 2017
 */
@AutoProperty
public abstract class ImpermanentModel extends FullModel {

	private static final long serialVersionUID = 1L;

	@NotNull
	private DateTime effectiveTime;
	@NotNull
	private DateTime expiryTime;

	public DateTime getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(DateTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public DateTime getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(DateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
}
