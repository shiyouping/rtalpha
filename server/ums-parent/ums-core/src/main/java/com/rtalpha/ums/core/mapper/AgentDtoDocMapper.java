package com.rtalpha.ums.core.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.framework.core.mapper.DtoDocumentMapper;
import com.rtalpha.kernel.core.document.Agent;
import com.rtalpha.ums.remote.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface AgentDtoDocMapper extends DtoDocumentMapper<AgentDto, Agent> {
	
}