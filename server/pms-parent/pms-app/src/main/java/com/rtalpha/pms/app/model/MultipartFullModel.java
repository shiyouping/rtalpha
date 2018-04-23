package com.rtalpha.pms.app.model;

import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.web.model.FullModel;

/**
 * @author Ricky Shi
 * @since Jul 31, 2017
 */
@AutoProperty
public class MultipartFullModel extends FullModel {

	private static final long serialVersionUID = 1L;

	private String md5;
	private String crc32;
	private String sha256;
	private String location;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getCrc32() {
		return crc32;
	}

	public void setCrc32(String crc32) {
		this.crc32 = crc32;
	}

	public String getSha256() {
		return sha256;
	}

	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
