package com.rtalpha.ums.core.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.framework.core.mapper.DtoModelMapper;
import com.rtalpha.ums.core.model.AgentFullModel;
import com.rtalpha.ums.remote.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface AgentDtoModelMapper extends DtoModelMapper<AgentDto, AgentFullModel> {

}