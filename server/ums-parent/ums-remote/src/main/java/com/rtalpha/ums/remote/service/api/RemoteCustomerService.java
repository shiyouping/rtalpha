package com.rtalpha.ums.remote.service.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.framework.core.crud.remote.RemoteCreateService;
import com.rtalpha.framework.core.crud.remote.RemoteReadService;
import com.rtalpha.framework.core.crud.remote.RemoteUpdateService;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky Shi
 * @since Jul 12, 2017
 */
public interface RemoteCustomerService
		extends RemoteCreateService<CustomerDto>, RemoteReadService<CustomerDto>, RemoteUpdateService<CustomerDto> {

	@Nullable
	List<CustomerDto> findAll(boolean isActive);

	@Nullable
	CustomerDto findOneByEmail(@Nonnull String email);

	@Nullable
	CustomerDto findOneByEmail(@Nonnull String email, boolean isActive);

	@Nullable
	CustomerDto findOneById(@Nonnull String id, boolean isActive);
}
