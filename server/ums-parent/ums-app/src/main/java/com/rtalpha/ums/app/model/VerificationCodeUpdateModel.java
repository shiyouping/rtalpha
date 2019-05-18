package com.rtalpha.ums.app.model;

import javax.validation.constraints.NotNull;

import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.web.model.PartialModel;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@AutoProperty
public class VerificationCodeUpdateModel extends PartialModel {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String email;
	@NotNull
	private String code;

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
}
