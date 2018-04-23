package com.rtalpha.ums.core.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.framework.core.model.PartialModel;

/**
 * @author Ricky Shi
 * @since Jul 24, 2017
 */
@AutoProperty
public class AgentUpdateModel extends PartialModel {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 6)
	private String password;
	private String landline;
	@NotBlank
	private String mobile;
	@NotBlank
	private String company;
	private String description;
	@NotBlank
	private String licenceNumber;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
