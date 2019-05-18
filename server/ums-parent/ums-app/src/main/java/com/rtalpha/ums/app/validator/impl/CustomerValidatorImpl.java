package com.rtalpha.ums.app.validator.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rtalpha.base.mongo.document.Agent;
import com.rtalpha.base.mongo.document.Customer;
import com.rtalpha.base.web.security.AuthenticatedUser;
import com.rtalpha.ums.app.repository.AgentRepository;
import com.rtalpha.ums.app.repository.CustomerRepository;
import com.rtalpha.ums.app.validator.api.CustomerValidator;
import com.rtalpha.ums.kenel.dto.CustomerDto;

/**
 * @author Ricky
 * @since Jun 17, 2017
 *
 */
@Component
public class CustomerValidatorImpl implements CustomerValidator {

	private final AgentRepository agentRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerValidatorImpl(@Nonnull AgentRepository agentRepository,
			@Nonnull CustomerRepository customerRepository) {
		checkNotNull(agentRepository, "agentRepository cannot be null");
		checkNotNull(customerRepository, "customerRepository cannot be null");

		this.agentRepository = agentRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void validateSave(CustomerDto dto) {
		checkNotNull(dto, "dto cannot be null");

		validate((errors) -> {
			Agent agent = agentRepository.findOneByEmail(dto.getEmail());
			if (agent != null) {
				errors.add("email already registered");
			}
		});
	}

	@Override
	public void validateUpdate(CustomerDto dto) {
		checkNotNull(dto, "customer cannot be null");

		validate((errors) -> {
			if (dto.getId() == null) {
				errors.add("id cannot be null");
				return;
			}

			Customer customer = customerRepository.findOne(dto.getId());
			if (customer == null) {
				errors.add("invalid id");
				return;
			}

			if (AuthenticatedUser.getCurrentUser() == null && customer.getIsActive()) {
				errors.add("illegal access");
				return;
			}

			if (!StringUtils.equalsIgnoreCase(customer.getEmail(), dto.getEmail())) {
				errors.add("cannot update email");
			}
		});
	}
}
