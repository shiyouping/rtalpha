package com.rtalpha.ums.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.ums.app.document.VerificationCode;
import com.rtalpha.ums.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Mapper
public interface VerificationCodeDtoDocMapper extends DtoDocumentMapper<VerificationCodeDto, VerificationCode> {

}
