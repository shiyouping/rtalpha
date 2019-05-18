package com.rtalpha.ums.app.config.properties;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author Ricky Shi
 * @since Jul 18, 2017
 */
@Validated
@ConfigurationProperties("umsApp")
public class UmsAppProperties {

	@Valid
	private UserVerification userVerification = new UserVerification();

	public UserVerification getUserVerification() {
		return userVerification;
	}

	public void setUserVerification(UserVerification userVerification) {
		this.userVerification = userVerification;
	}

	public static class UserVerification {
		@NotEmpty
		private String emailSubject;
		@NotEmpty
		private String template;
		@Min(6)
		private int codeLength = 8;
		@Min(10)
		private int codeLifespanInMinute = 30;

		public int getCodeLength() {
			return codeLength;
		}

		public int getCodeLifespanInMinute() {
			return codeLifespanInMinute;
		}

		public String getEmailSubject() {
			return emailSubject;
		}

		public String getTemplate() {
			return template;
		}

		public void setCodeLength(int codeLength) {
			this.codeLength = codeLength;
		}

		public void setCodeLifespanInMinute(int codeLifespanInMinute) {
			this.codeLifespanInMinute = codeLifespanInMinute;
		}

		public void setEmailSubject(String emailSubject) {
			this.emailSubject = emailSubject;
		}

		public void setTemplate(String template) {
			this.template = template;
		}
	}
}
