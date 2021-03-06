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
public class AgentDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private String name;
	private String landline;
	private String mobile;
	private String company;
	private String description;
	private String licenceNumber;
	private Boolean isActive = false;
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
