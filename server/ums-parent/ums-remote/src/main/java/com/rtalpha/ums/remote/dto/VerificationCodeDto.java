package com.rtalpha.ums.remote.dto;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.framework.core.dto.BaseDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@AutoProperty
public class VerificationCodeDto extends BaseDto {

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
