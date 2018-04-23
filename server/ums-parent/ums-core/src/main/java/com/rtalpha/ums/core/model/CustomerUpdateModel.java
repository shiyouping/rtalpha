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
public class CustomerUpdateModel extends PartialModel {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 25)
	private String name;
	@NotBlank
	@Size(min = 6)
	private String password;
	private String mobile;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
