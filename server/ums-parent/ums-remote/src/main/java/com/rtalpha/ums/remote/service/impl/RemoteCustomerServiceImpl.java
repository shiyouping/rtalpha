package com.rtalpha.ums.remote.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.rtalpha.framework.core.config.properties.FrameworkCoreProperties;
import com.rtalpha.framework.core.crud.remote.AbstractRemoteCrudService;
import com.rtalpha.framework.core.rest.response.internal.InternalBulkResponse;
import com.rtalpha.framework.core.rest.response.internal.InternalSingleResponse;
import com.rtalpha.kernel.core.config.properties.KernelCoreProperties;
import com.rtalpha.kernel.core.constant.RestPath;
import com.rtalpha.ums.remote.dto.CustomerDto;
import com.rtalpha.ums.remote.service.api.RemoteCustomerService;

/**
 * @author Ricky Shi
 * @since Jul 12, 2017
 */
@Service
@SuppressWarnings("unchecked")
public class RemoteCustomerServiceImpl extends AbstractRemoteCrudService<CustomerDto> implements RemoteCustomerService {

	@Autowired
	public RemoteCustomerServiceImpl(@Nonnull RestTemplateBuilder builder,
			@Nonnull FrameworkCoreProperties frameworkCoreProperties,
			@Nonnull KernelCoreProperties kernelCoreProperties) {
		super(builder, frameworkCoreProperties, kernelCoreProperties.getWebService().getRootUrl().getUms()
				+ RestPath.UMS_INTERNAL_PATH_VERSION_1 + "/customer");
	}

	@Override
	public List<CustomerDto> findAll(boolean isActive) {
		String url = baseUrl + "/" + InternalBulkResponse.REST_PATH_FIND_ALL + "ByIsActive";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("isActive", isActive);
		return restTemplate.getForObject(builder.toUriString(), List.class);
	}

	@Override
	public CustomerDto findOneByEmail(String email) {
		checkNotNull(email, "email cannot be null");
		String url = baseUrl + "/" + InternalSingleResponse.REST_PATH_FIND_ONE + "ByEmail";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("email", email);
		return restTemplate.getForObject(builder.toUriString(), CustomerDto.class);
	}

	@Override
	public CustomerDto findOneByEmail(String email, boolean isActive) {
		checkNotNull(email, "email cannot be null");
		String url = baseUrl + "/" + InternalSingleResponse.REST_PATH_FIND_ONE + "ByEmail";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("email", email)
				.queryParam("isActive", isActive);
		return restTemplate.getForObject(builder.toUriString(), CustomerDto.class);
	}

	@Override
	public CustomerDto findOneById(String id, boolean isActive) {
		checkNotNull(id, "id cannot be null");
		String url = baseUrl + "/" + InternalSingleResponse.REST_PATH_FIND_ONE + "ByIdAndIsActive";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("id", id)
				.queryParam("isActive", isActive);
		return restTemplate.getForObject(builder.toUriString(), CustomerDto.class);
	}
}
