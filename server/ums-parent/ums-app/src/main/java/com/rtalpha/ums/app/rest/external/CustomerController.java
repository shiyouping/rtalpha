package com.rtalpha.ums.app.rest.external;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.mongo.document.Customer;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.rest.controller.external.ExternalControllerMetaData;
import com.rtalpha.base.web.rest.controller.external.ExternalUpdateController;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.rest.response.external.ExternalVoidResponse;
import com.rtalpha.base.web.security.AuthenticatedUser;
import com.rtalpha.base.web.security.MongoCredentialServiceImpl;
import com.rtalpha.ums.app.mapper.CustomerDtoModelMapper;
import com.rtalpha.ums.app.model.CustomerFullModel;
import com.rtalpha.ums.app.model.CustomerUpdateModel;
import com.rtalpha.ums.app.model.VerificationCodeUpdateModel;
import com.rtalpha.ums.app.service.api.CustomerService;
import com.rtalpha.ums.app.service.api.VerificationCodeService;
import com.rtalpha.ums.app.service.api.VerificationEmailService;
import com.rtalpha.ums.kenel.dto.CustomerDto;
import com.rtalpha.ums.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@RestController
@RequestMapping(RequestPath.API_EXTERNAL_UMS_VERSION_1 + "/customer")
public class CustomerController
		implements ExternalUpdateController<CustomerDto, Customer, CustomerFullModel, CustomerUpdateModel> {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerDtoModelMapper mapper;
	private final CustomerService customerService;
	private final VerificationCodeService codeService;
	private final VerificationEmailService emailService;
	private final ExternalControllerMetaData<CustomerDto, Customer, CustomerFullModel> metaData;

	@Autowired
	public CustomerController(@Nonnull CustomerService customerService, @Nonnull VerificationCodeService codeService,
			@Nonnull VerificationEmailService emailService, @Nonnull CustomerDtoModelMapper mapper) {

		checkNotNull(customerService, "customerService cannot be null");
		checkNotNull(codeService, "codeService cannot be null");
		checkNotNull(emailService, "emailService cannot be null.");
		checkNotNull(mapper, "mapper cannot be null");

		this.mapper = mapper;
		this.emailService = emailService;
		this.customerService = customerService;
		this.codeService = codeService;
		this.metaData = new ExternalControllerMetaData<>(customerService, mapper);
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ONE + "ByEmail")
	public ExternalSingleResponse<CustomerFullModel> findOneByEmail(@RequestParam(value = "email") final String email,
			@RequestParam(value = "isActive", required = false) final Boolean isActive) {
		logger.debug("Received a request for finding customer by email={}, isActive={}", email, isActive);

		return new ExternalSingleResponse<>(() -> {
			CustomerDto customer = null;

			if (isActive == null) {
				customer = customerService.findOneByEmail(email);
			} else {
				customer = customerService.findOneByEmail(email, isActive);
			}

			if (!StringUtils.equals(customer.getEmail(), AuthenticatedUser.getCurrentUser())) {
				throw new RestException(HttpStatus.FORBIDDEN, "Cannot query other customer");
			}

			return mapper.toModel(customer);
		});
	}

	@Override
	public ExternalControllerMetaData<CustomerDto, Customer, CustomerFullModel> getControllerMetaData() {
		return metaData;
	}

	@Override
	public void preUpdateOne(CustomerUpdateModel customer, CustomerDto existingCustomer) {
		if (!StringUtils.equals(existingCustomer.getEmail(), AuthenticatedUser.getCurrentUser())) {
			throw new RestException(HttpStatus.FORBIDDEN, "Cannot update other customer");
		}
	}

	/**
	 * This controller is secured, so authentication will be done in
	 * {@link MongoCredentialServiceImpl}. This controller will only update the
	 * login time of the authenticated user
	 */
	@GetMapping(path = "/signin")
	public ExternalVoidResponse signin() {
		final String email = AuthenticatedUser.getCurrentUser();
		logger.debug("Received a request for customer signin. customer={}", email);

		return new ExternalVoidResponse(() -> {
			CustomerDto customer = customerService.findOneByEmail(email, true);
			customer.setLoginTime(DateTime.now());
			customerService.update(customer);
		});
	}

	/**
	 * This controller is secured, so authentication will be done in
	 * {@link MongoCredentialServiceImpl}. This controller will only invalidate the
	 * session for the current user
	 */
	@GetMapping(path = "/signout")
	public ExternalVoidResponse signout(final HttpSession session) {
		final String email = AuthenticatedUser.getCurrentUser();
		logger.debug("Received a request for customer signout. customer={}", email);

		return new ExternalVoidResponse(() -> {
			AuthenticatedUser.invalidateCurrentUser(session);
		});
	}

	@PostMapping(path = "/signup")
	public ExternalVoidResponse signup(@Valid @RequestBody final CustomerFullModel customer) {
		logger.debug("Received a request for customer signup. customer={}", customer);

		return new ExternalVoidResponse(() -> {
			customerService.save(mapper.toDto(customer));
			emailService.sendVerificationCode(customer.getEmail());
		});
	}

	@PutMapping(path = "/verify")
	public ExternalVoidResponse verify(@Valid @RequestBody final VerificationCodeUpdateModel verificationCode) {
		logger.debug("Received a request for verifying customer. VerificationCodeDto={}", verificationCode);

		return new ExternalVoidResponse(() -> {
			CustomerDto customer = customerService.findOneByEmail(verificationCode.getEmail());

			if (customer == null) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Invalid email");
			}

			if (customer.getIsActive()) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Customer already activated");
			}

			VerificationCodeDto code = codeService.findLatestOneByEmail(verificationCode.getEmail());
			if (code == null || !StringUtils.equalsIgnoreCase(verificationCode.getCode(), code.getCode())
					|| code.getValidBefore().isBeforeNow()) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Invalid verification code");
			}

			customer.setIsActive(true);
			customerService.update(customer);
		});
	}
}