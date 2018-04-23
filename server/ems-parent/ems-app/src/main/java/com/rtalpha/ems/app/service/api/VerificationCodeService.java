package com.rtalpha.ems.app.service.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.base.mongo.crud.CreateService;
import com.rtalpha.base.mongo.crud.ReadService;
import com.rtalpha.ems.app.document.VerificationCode;
import com.rtalpha.ems.kenel.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
public interface VerificationCodeService extends CreateService<VerificationCodeDto, VerificationCode>,
		ReadService<VerificationCodeDto, VerificationCode> {

	@Nullable
	VerificationCodeDto findLatestOneByEmail(@Nonnull String email);
}