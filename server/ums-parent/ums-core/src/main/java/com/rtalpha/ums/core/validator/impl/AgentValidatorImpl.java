package com.rtalpha.ums.core.validator.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rtalpha.framework.core.security.AuthenticatedUser;
import com.rtalpha.kernel.core.document.Agent;
import com.rtalpha.kernel.core.document.Customer;
import com.rtalpha.ums.core.repository.AgentRepository;
import com.rtalpha.ums.core.repository.CustomerRepository;
import com.rtalpha.ums.core.validator.api.AgentValidator;
import com.rtalpha.ums.remote.dto.AgentDto;

/**
 * @author Ricky
 * @since Jun 17, 2017
 *
 */
@Component
public class AgentValidatorImpl implements AgentValidator {

	private final AgentRepository agentRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public AgentValidatorImpl(@Nonnull AgentRepository agentRepository,
			@Nonnull CustomerRepository customerRepository) {
		checkNotNull(agentRepository, "agentRepository cannot be null");
		checkNotNull(customerRepository, "customerRepository cannot be null");

		this.agentRepository = agentRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void validateSave(@Nonnull AgentDto agent) {
		checkNotNull(agent, "agent cannot be null");

		validate((errors) -> {
			Customer customer = customerRepository.findOneByEmail(agent.getEmail());
			if (customer != null) {
				errors.add("email already registered");
			}
		});
	}

	@Override
	public void validateUpdate(AgentDto dto) {
		checkNotNull(dto, "agent cannot be null");

		validate((errors) -> {
			if (dto.getId() == null) {
				errors.add("id cannot be null");
				return;
			}

			Agent agent = agentRepository.findOne(dto.getId());
			if (agent == null) {
				errors.add("invalid id");
				return;
			}

			if (AuthenticatedUser.getCurrentUser() == null && agent.getIsActive()) {
				errors.add("Illegal access");
				return;
			}

			if (!StringUtils.equalsIgnoreCase(agent.getEmail(), dto.getEmail())) {
				errors.add("cannot update email");
			}

			if (!StringUtils.equalsIgnoreCase(agent.getName(), dto.getName())) {
				errors.add("cannot update name");
			}
		});
	}
}
