package com.rtalpha.ums.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.ums.app.model.CustomerFullModel;
import com.rtalpha.ums.kenel.dto.CustomerDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface CustomerDtoModelMapper extends DtoModelMapper<CustomerDto, CustomerFullModel> {

}