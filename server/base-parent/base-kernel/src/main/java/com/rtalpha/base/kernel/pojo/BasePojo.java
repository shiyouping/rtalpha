package com.rtalpha.base.kernel.pojo;

import java.io.Serializable;

import org.pojomatic.Pojomatic;

/**
 * The superclass of all entities and DTOs
 * <p>
 * Created by Ricky on 16/10/18.
 */
public abstract class BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
}
