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
import com.rtalpha.base.mongo.document.Agent;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.rest.controller.external.ExternalControllerMetaData;
import com.rtalpha.base.web.rest.controller.external.ExternalUpdateController;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.base.web.rest.response.external.ExternalVoidResponse;
import com.rtalpha.base.web.security.AuthenticatedUser;
import com.rtalpha.base.web.security.MongoCredentialServiceImpl;
import com.rtalpha.ums.app.mapper.AgentDtoModelMapper;
import com.rtalpha.ums.app.model.AgentFullModel;
import com.rtalpha.ums.app.model.AgentUpdateModel;
import com.rtalpha.ums.app.model.VerificationCodeUpdateModel;
import com.rtalpha.ums.app.service.api.AgentService;
import com.rtalpha.ums.app.service.api.VerificationCodeService;
import com.rtalpha.ums.app.service.api.VerificationEmailService;
import com.rtalpha.ums.kenel.dto.AgentDto;
import com.rtalpha.ums.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@RestController
@RequestMapping(RequestPath.API_EXTERNAL_UMS_VERSION_1 + "/agent")
public class AgentController implements ExternalUpdateController<AgentDto, Agent, AgentFullModel, AgentUpdateModel> {

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

	private final AgentService agentService;
	private final VerificationCodeService codeService;
	private final VerificationEmailService emailService;
	private final AgentDtoModelMapper mapper;
	private final ExternalControllerMetaData<AgentDto, Agent, AgentFullModel> metaData;

	@Autowired
	public AgentController(@Nonnull AgentService agentService, @Nonnull VerificationCodeService codeService,
			@Nonnull VerificationEmailService emailService, @Nonnull AgentDtoModelMapper mapper) {

		checkNotNull(mapper, "mapper cannot be null");
		checkNotNull(agentService, "agentService cannot be null");
		checkNotNull(codeService, "codeService cannot be null");
		checkNotNull(emailService, "emailService cannot be null.");

		this.mapper = mapper;
		this.emailService = emailService;
		this.agentService = agentService;
		this.codeService = codeService;
		this.metaData = new ExternalControllerMetaData<>(agentService, mapper);
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ONE + "ByEmail")
	public ExternalSingleResponse<AgentFullModel> findOneByEmail(@RequestParam(value = "email") final String email,
			@RequestParam(value = "isActive", required = false) final Boolean isActive) {
		logger.debug("Received a request for finding agent by email={}, isActive={}", email, isActive);

		return new ExternalSingleResponse<>(() -> {
			AgentDto agent = null;

			if (isActive == null) {
				agent = agentService.findOneByEmail(email);
			} else {
				agent = agentService.findOneByEmail(email, isActive);
			}

			if (!StringUtils.equals(agent.getEmail(), AuthenticatedUser.getCurrentUser())) {
				throw new RestException(HttpStatus.FORBIDDEN, "Cannot query other customer");
			}

			return mapper.toModel(agent);
		});
	}

	@Override
	public ExternalControllerMetaData<AgentDto, Agent, AgentFullModel> getControllerMetaData() {
		return this.metaData;
	}

	@Override
	public void preUpdateOne(AgentUpdateModel agent, AgentDto existingAgent) {
		if (!StringUtils.equals(existingAgent.getEmail(), AuthenticatedUser.getCurrentUser())) {
			throw new RestException(HttpStatus.FORBIDDEN, "Cannot update other agent");
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
		logger.debug("Received a request for agent signin. agent={}", email);

		return new ExternalVoidResponse(() -> {
			AgentDto agent = agentService.findOneByEmail(email, true);
			agent.setLoginTime(DateTime.now());
			agentService.update(agent);
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
		logger.debug("Received a request for agent signout. agent={}", email);

		return new ExternalVoidResponse(() -> {
			AuthenticatedUser.invalidateCurrentUser(session);
		});
	}

	@PostMapping(path = "/signup")
	public ExternalVoidResponse signup(@Valid @RequestBody final AgentFullModel agent) {
		logger.debug("Received a request for agent signup. agent={}", agent);

		return new ExternalVoidResponse(() -> {
			agentService.save(mapper.toDto(agent));
			emailService.sendVerificationCode(agent.getEmail());
		});
	}

	@PutMapping(path = "/verify")
	public ExternalVoidResponse verify(@Valid @RequestBody final VerificationCodeUpdateModel verificationCode) {
		logger.debug("Received a request for verifying agent. verificationCode={}", verificationCode);

		return new ExternalVoidResponse(() -> {
			AgentDto agent = agentService.findOneByEmail(verificationCode.getEmail());

			if (agent == null) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Invalid email");
			}

			if (agent.getIsActive()) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Agent already activated");
			}

			VerificationCodeDto code = codeService.findLatestOneByEmail(verificationCode.getEmail());
			if (code == null || !StringUtils.equalsIgnoreCase(verificationCode.getCode(), code.getCode())
					|| code.getValidBefore().isBeforeNow()) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Invalid verification code");
			}

			agent.setIsActive(true);
			agentService.update(agent);
		});
	}
}