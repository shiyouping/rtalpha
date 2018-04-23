package com.rtalpha.ums.core.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.framework.core.mapper.DtoDocumentMapper;
import com.rtalpha.kernel.core.document.Customer;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface CustomerDtoDocMapper extends DtoDocumentMapper<CustomerDto, Customer> {
	
}