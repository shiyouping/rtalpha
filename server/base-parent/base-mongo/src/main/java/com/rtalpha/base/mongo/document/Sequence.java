package com.rtalpha.base.mongo.document;

import org.pojomatic.annotations.AutoProperty;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Keep the sequence numbers. DO NOT use it manually. This is intended for
 * system use
 * 
 * @author Ricky
 * @since May 17, 2017
 */
@AutoProperty
@Document(collection = "Sequence")
public class Sequence extends BaseDocument {
	private static final long serialVersionUID = 1L;

	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
