package com.rtalpha.ums.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.ums.app.model.VerificationCodeUpdateModel;
import com.rtalpha.ums.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Mapper
public interface VerificationCodeDtoModelMapper
		extends DtoModelMapper<VerificationCodeDto, VerificationCodeUpdateModel> {

}
