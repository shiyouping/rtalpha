package com.rtalpha.ums.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.ums.app.model.AgentFullModel;
import com.rtalpha.ums.kenel.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface AgentDtoModelMapper extends DtoModelMapper<AgentDto, AgentFullModel> {

}