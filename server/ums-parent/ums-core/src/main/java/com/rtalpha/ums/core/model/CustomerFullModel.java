package com.rtalpha.ums.core.model;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.framework.core.model.FullModel;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@AutoProperty
public class CustomerFullModel extends FullModel {

	private static final long serialVersionUID = 1L;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(max = 25)
	private String name;

	@NotBlank
	@Size(min = 6)
	private String password;

	@Null
	private Boolean isActive = false;
	@Null
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
