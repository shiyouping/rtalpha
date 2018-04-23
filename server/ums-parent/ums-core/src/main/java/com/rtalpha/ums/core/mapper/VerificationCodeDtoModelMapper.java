package com.rtalpha.ums.core.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.framework.core.mapper.DtoModelMapper;
import com.rtalpha.ums.core.model.VerificationCodeUpdateModel;
import com.rtalpha.ums.remote.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Mapper
public interface VerificationCodeDtoModelMapper extends DtoModelMapper<VerificationCodeDto, VerificationCodeUpdateModel> {

}
