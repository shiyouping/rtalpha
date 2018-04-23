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
import com.rtalpha.framework.core.rest.response.SingleResponse;
import com.rtalpha.framework.core.rest.response.internal.InternalBulkResponse;
import com.rtalpha.framework.core.rest.response.internal.InternalSingleResponse;
import com.rtalpha.kernel.core.constant.RestPath;
import com.rtalpha.kernel.core.document.Agent;
import com.rtalpha.ums.core.service.api.AgentService;
import com.rtalpha.ums.remote.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@RestController("internalAgentController")
@RequestMapping(RestPath.UMS_INTERNAL_PATH_VERSION_1 + "/agent")
public class AgentController implements InternalCreateController<AgentDto, Agent>,
		InternalReadController<AgentDto, Agent>, InternalUpdateController<AgentDto, Agent> {

	private final AgentService service;

	@Autowired
	public AgentController(@Nonnull AgentService service) {
		checkNotNull(service, "service cannot be null");
		this.service = service;
	}

	@GetMapping(path = InternalBulkResponse.REST_PATH_FIND_ALL + "ByIsActive")
	public InternalBulkResponse<AgentDto> findAll(@RequestParam(value = "isActive") boolean isActive) {
		return new InternalBulkResponse<>(() -> {
			return service.findAll(isActive);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByCompany")
	public InternalSingleResponse<AgentDto> findOneByCompany(@RequestParam(value = "company") String company,
			@RequestParam(value = "isActive", required = false) Boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			if (isActive != null) {
				return service.findOneByCompany(company, isActive);
			}
			return service.findOneByCompany(company);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByEmail")
	public InternalSingleResponse<AgentDto> findOneByEmail(@RequestParam(value = "email") String email,
			@RequestParam(value = "isActive", required = false) Boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			if (isActive != null) {
				return service.findOneByEmail(email, isActive);
			}
			return service.findOneByEmail(email);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByIdAndIsActive")
	public InternalSingleResponse<AgentDto> findOneById(@RequestParam(value = "id") String id,
			@RequestParam(value = "isActive") boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			return service.findOneById(id, isActive);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByLicenceNumber")
	public InternalSingleResponse<AgentDto> findOneByLicenceNumber(
			@RequestParam(value = "licenceNumber") String licenceNumber,
			@RequestParam(value = "isActive", required = false) Boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			if (isActive != null) {
				return service.findOneByLicenceNumber(licenceNumber, isActive);
			}
			return service.findOneByLicenceNumber(licenceNumber);
		});
	}

	@GetMapping(path = InternalSingleResponse.REST_PATH_FIND_ONE + "ByName")
	public SingleResponse<AgentDto> findOneByName(@RequestParam(value = "name") String name,
			@RequestParam(value = "isActive", required = false) Boolean isActive) {
		return new InternalSingleResponse<>(() -> {
			if (isActive != null) {
				return service.findOneByName(name, isActive);
			}
			return service.findOneByName(name);
		});
	}

	@Override
	public CrudService<AgentDto, Agent> getCrudService() {
		return service;
	}
}