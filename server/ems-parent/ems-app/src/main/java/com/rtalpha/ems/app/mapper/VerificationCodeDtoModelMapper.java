package com.rtalpha.ems.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.ems.app.model.VerificationCodeUpdateModel;
import com.rtalpha.ems.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Mapper
public interface VerificationCodeDtoModelMapper
		extends DtoModelMapper<VerificationCodeDto, VerificationCodeUpdateModel> {

}
