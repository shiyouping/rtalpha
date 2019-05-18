package com.rtalpha.ems.app.rest.external;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.rest.controller.external.ExternalController;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.rest.response.external.ExternalVoidResponse;
import com.rtalpha.ems.app.service.api.VerificationEmailService;

/**
 * @author Ricky
 * @since Apr 30, 2017
 *
 */
@RestController
@RequestMapping(RequestPath.API_EXTERNAL_EMS_VERSION_1 + "/verificationCodes")
public class VerificationCodeController implements ExternalController {

	private static final Logger logger = LoggerFactory.getLogger(VerificationCodeController.class);
	private final VerificationEmailService emailService;

	@Autowired
	public VerificationCodeController(@Nonnull VerificationEmailService emailService) {
		checkNotNull(emailService, "emailService cannot be null");
		this.emailService = emailService;
	}

	@PostMapping(path = "/send")
	public ExternalVoidResponse send(@RequestParam(value = "email") final String email) {
		logger.info("Sending a verification code to {}", email);

		return new ExternalVoidResponse(() -> {
			if (!EmailValidator.getInstance(true).isValid(email)) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Invalid Email address");
			}

			if (!emailService.isAllowedToSend(email)) {
				throw new RestException(HttpStatus.BAD_REQUEST, "The pervious code is still valid");
			}

			emailService.sendVerificationCode(email);
		});
	}
	
	@GetMapping(path = "/findLatestOneByEmail")
	public ExternalSingleResponse<VerificationCodeMo>
}
