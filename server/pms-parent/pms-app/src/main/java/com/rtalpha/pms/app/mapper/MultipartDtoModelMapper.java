package com.rtalpha.pms.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.pms.app.model.MultipartFullModel;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author Ricky Shi
 * @since Jul 31, 2017
 */
@Mapper
public interface MultipartDtoModelMapper extends DtoModelMapper<MultipartDto, MultipartFullModel> {
}
