package com.rtalpha.ums.core.rest.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.framework.core.crud.CrudService;
import com.rtalpha.framework.core.rest.controller.internal.InternalCreateController;
import com.rtalpha.framework.core.rest.controller.internal.InternalReadController;
import com.rtalpha.framework.core.rest.controller.internal.InternalUpdateController;
import com.rtalpha.framework.core.rest.response.internal.InternalBulkResponse;
import com.rtalpha.framework.core.rest.response.internal.InternalSingleResponse;
import com.rtalpha.kernel.core.constant.RestPath;
import com.rtalpha.kernel.core.document.Customer;
import com.rtalpha.ums.core.service.api.CustomerService;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@RestController("internalCustomerController")
@RequestMapping(RestPath.UMS_INTERNAL_PATH_VERSION_1 + "/customer")
public class CustomerController implements InternalCreateController<CustomerDto, Customer>,
		InternalReadController<CustomerDto, Customer>, InternalUpdateController<CustomerDto, Customer> {

	private final CustomerService service;

	@Autowired
	public CustomerController(@Nonnull CustomerService service) {
		checkNotNull(service, "service cannot be null");
		this.service = service;
	}

	@GetMapping(path = InternalBulkResponse.REST_PATH_FIND_ALL + "ByIsActive")
	public InternalBulkResponse<CustomerDto> findAll(@RequestParam(value = "isActive") boolean isActive) {
		return new InternalBulkResponse<>(() -> {
			return service.findAll(isActive);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByEmail")
	public InternalSingleResponse<CustomerDto> findOneByEmail(@RequestParam(value = "email") String email,
			@RequestParam(value = "isActive", required = false) Boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			if (isActive != null) {
				return service.findOneByEmail(email, isActive);
			}

			return service.findOneByEmail(email);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByIdAndIsActive")
	public InternalSingleResponse<CustomerDto> findOneById(@RequestParam(value = "id") String id,
			@RequestParam(value = "isActive") boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			return service.findOneById(id, isActive);
		});
	}

	@Override
	public CrudService<CustomerDto, Customer> getCrudService() {
		return service;
	}
}