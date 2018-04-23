package com.rtalpha.ums.core.service.api;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.framework.core.crud.CreateService;
import com.rtalpha.framework.core.crud.ReadService;
import com.rtalpha.framework.core.crud.UpdateService;
import com.rtalpha.kernel.core.document.Customer;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 16, 2017
 *
 */
public interface CustomerService extends CreateService<CustomerDto, Customer>, ReadService<CustomerDto, Customer>,
		UpdateService<CustomerDto, Customer> {

	@Nullable
	List<CustomerDto> findAll(boolean isActive);

	@Nullable
	CustomerDto findOneByEmail(@Nonnull String email);

	@Nullable
	CustomerDto findOneByEmail(@Nonnull String email, boolean isActive);

	@Nullable
	CustomerDto findOneById(@Nonnull String id, boolean isActive);
}
