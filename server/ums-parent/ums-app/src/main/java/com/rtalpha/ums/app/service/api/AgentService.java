package com.rtalpha.ums.app.service.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.base.mongo.crud.CreateService;
import com.rtalpha.base.mongo.crud.ReadService;
import com.rtalpha.base.mongo.crud.UpdateService;
import com.rtalpha.base.mongo.document.Agent;
import com.rtalpha.ums.kenel.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 16, 2017
 *
 */
public interface AgentService
		extends CreateService<AgentDto, Agent>, ReadService<AgentDto, Agent>, UpdateService<AgentDto, Agent> {

	@Nullable
	List<AgentDto> findAll(boolean isActive);

	@Nullable
	AgentDto findOneByCompany(@Nonnull String company);

	@Nullable
	AgentDto findOneByCompany(@Nonnull String company, boolean isActive);

	@Nullable
	AgentDto findOneByEmail(@Nonnull String email);

	@Nullable
	AgentDto findOneByEmail(@Nonnull String email, boolean isActive);

	@Nullable
	AgentDto findOneById(@Nonnull String id, boolean isActive);

	@Nullable
	AgentDto findOneByLicenceNumber(@Nonnull String licenceNumber);

	@Nullable
	AgentDto findOneByLicenceNumber(@Nonnull String licenceNumber, boolean isActive);

	@Nullable
	AgentDto findOneByName(@Nonnull String name);

	@Nullable
	AgentDto findOneByName(@Nonnull String name, boolean isActive);
}
