package com.rtalpha.ums.remote.service.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.framework.core.crud.remote.RemoteCreateService;
import com.rtalpha.framework.core.crud.remote.RemoteReadService;
import com.rtalpha.framework.core.crud.remote.RemoteUpdateService;
import com.rtalpha.ums.remote.dto.AgentDto;

/**
 * @author Ricky
 * @since Jun 28, 2017
 */
public interface RemoteAgentService
		extends RemoteCreateService<AgentDto>, RemoteReadService<AgentDto>, RemoteUpdateService<AgentDto> {

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
