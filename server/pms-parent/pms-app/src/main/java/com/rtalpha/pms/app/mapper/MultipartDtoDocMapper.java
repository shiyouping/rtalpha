package com.rtalpha.pms.app.mapper;

import org.mapstruct.Mapper;

import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.pms.app.document.Multipart;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author Ricky
 * @since May 18, 2017
 */
@Mapper
public interface MultipartDtoDocMapper extends DtoDocumentMapper<MultipartDto, Multipart> {
}
