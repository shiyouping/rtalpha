package com.rtalpha.ums.core.document;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rtalpha.framework.core.mongo.document.BaseDocument;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@AutoProperty
@Document(collection = "VerificationCode")
public class VerificationCode extends BaseDocument {

	private static final long serialVersionUID = 1L;

	private String email;
	private String code;
	private DateTime validBefore;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DateTime getValidBefore() {
		return validBefore;
	}

	public void setValidBefore(DateTime validBefore) {
		this.validBefore = validBefore;
	}
}
