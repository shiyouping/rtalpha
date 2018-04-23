package com.rtalpha.ums.core.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.framework.core.mapper.DtoModelMapper;
import com.rtalpha.ums.core.model.CustomerFullModel;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface CustomerDtoModelMapper extends DtoModelMapper<CustomerDto, CustomerFullModel> {

}