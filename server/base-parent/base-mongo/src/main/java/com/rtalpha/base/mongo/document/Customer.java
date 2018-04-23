package com.rtalpha.base.mongo.document;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@AutoProperty
@Document(collection = "Customer")
public class Customer extends BaseDocument {

	@Transient
	private static final long serialVersionUID = 1L;

	@Indexed(unique = true)
	private String email;
	private String name;
	private String mobile;
	private String password;
	private Boolean isActive = false;
	private DateTime loginTime;

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
}
