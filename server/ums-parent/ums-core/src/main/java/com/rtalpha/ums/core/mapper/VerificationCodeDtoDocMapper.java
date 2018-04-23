package com.rtalpha.ums.core.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.framework.core.mapper.DtoDocumentMapper;
import com.rtalpha.ums.core.document.VerificationCode;
import com.rtalpha.ums.remote.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Mapper
public interface VerificationCodeDtoDocMapper extends DtoDocumentMapper<VerificationCodeDto, VerificationCode> {

}
