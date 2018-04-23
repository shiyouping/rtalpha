package com.rtalpha.ums.core.service.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rtalpha.framework.core.crud.CreateService;
import com.rtalpha.framework.core.crud.ReadService;
import com.rtalpha.ums.core.document.VerificationCode;
import com.rtalpha.ums.remote.dto.VerificationCodeDto;

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