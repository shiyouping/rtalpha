package com.rtalpha.ums.app.model;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.web.model.FullModel;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@AutoProperty
public class AgentFullModel extends FullModel {

	private static final long serialVersionUID = 1L;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	@NotBlank
	@Size(max = 50)
	private String name;

	private String landline;

	@NotBlank
	private String mobile;

	@NotBlank
	private String company;
	private String description;

	@NotBlank
	private String licenceNumber;
	@Null
	private Boolean isActive = false;
	@Null
	private DateTime loginTime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
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
