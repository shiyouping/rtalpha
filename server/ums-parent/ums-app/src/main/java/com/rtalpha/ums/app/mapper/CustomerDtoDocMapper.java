package com.rtalpha.ums.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.mongo.document.Customer;
import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.ums.kenel.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface CustomerDtoDocMapper extends DtoDocumentMapper<CustomerDto, Customer> {

}