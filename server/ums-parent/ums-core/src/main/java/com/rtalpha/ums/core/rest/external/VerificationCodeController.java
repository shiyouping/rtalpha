package com.rtalpha.ums.core.rest.external;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.framework.core.exception.RestException;
import com.rtalpha.framework.core.rest.controller.external.ExternalController;
import com.rtalpha.framework.core.rest.response.external.ExternalVoidResponse;
import com.rtalpha.kernel.core.constant.RestPath;
import com.rtalpha.ums.core.service.api.VerificationEmailService;

/**
 * @author Ricky
 * @since Apr 30, 2017
 *
 */
@RestController
@RequestMapping(RestPath.UMS_EXTERNAL_PATH_VERSION_1 + "/verificationCode")
public class VerificationCodeController implements ExternalController {

	private static final Logger logger = LoggerFactory.getLogger(VerificationCodeController.class);
	private final VerificationEmailService emailService;

	@Autowired
	public VerificationCodeController(@Nonnull VerificationEmailService emailService) {
		checkNotNull(emailService, "emailService cannot be null");
		this.emailService = emailService;
	}

	@GetMapping(path = "/send")
	public ExternalVoidResponse send(@RequestParam(value = "email") final String email) {
		logger.info("Sending a verification code to {}", email);

		return new ExternalVoidResponse(() -> {
			if (!emailService.isAllowedToSend(email)) {
				throw new RestException(HttpStatus.BAD_REQUEST, "The pervious code is still valid");
			}

			emailService.sendVerificationCode(email);
		});
	}
}
