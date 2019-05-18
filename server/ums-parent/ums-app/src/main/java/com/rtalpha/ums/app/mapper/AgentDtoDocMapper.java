package com.rtalpha.ums.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.mongo.document.Agent;
import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.ums.kenel.dto.AgentDto;

/**
 * @author Ricky
 * @since Apr 8, 2017
 *
 */
@Mapper
public interface AgentDtoDocMapper extends DtoDocumentMapper<AgentDto, Agent> {

}