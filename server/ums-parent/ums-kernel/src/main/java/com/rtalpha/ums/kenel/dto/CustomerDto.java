package com.rtalpha.ums.kenel.dto;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@AutoProperty
public class CustomerDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String email;
	private String name;
	private String password;
	private Boolean isActive = false;
	private DateTime loginTime;
	private String mobile;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public DateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(DateTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
