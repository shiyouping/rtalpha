package com.rtalpha.ems.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.ems.app.document.VerificationCode;
import com.rtalpha.ems.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Mapper
public interface VerificationCodeDtoDocMapper extends DtoDocumentMapper<VerificationCodeDto, VerificationCode> {

}
