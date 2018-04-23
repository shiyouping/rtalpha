package com.rtalpha.pms.app.service.api;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.base.mongo.crud.CreateService;
import com.rtalpha.base.mongo.crud.UpdateService;
import com.rtalpha.pms.app.document.Multipart;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author Ricky
 * @since May 18, 2017
 */
public interface MultipartService
		extends CreateService<MultipartDto, Multipart>, UpdateService<MultipartDto, Multipart> {

	@Nullable
	MultipartDto findOne(@Nonnull String md5, @Nonnull String crc32, @Nonnull String sha256);

	/**
	 * Write the data to the predefined location, save the metedata into database,
	 * and get the path relative to the base location specified in the configuration
	 * file, e.g.
	 * /txt/2017161/56b1174ab10a8db164e0754105b7a99be72e3fe5a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e.txt
	 */
	@Nonnull
	MultipartDto save(@Nonnull byte[] data, @Nonnull String fileType) throws IOException;
}
