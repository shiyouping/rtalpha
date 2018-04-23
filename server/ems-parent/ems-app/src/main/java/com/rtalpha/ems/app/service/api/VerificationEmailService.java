package com.rtalpha.ems.app.service.api;

import javax.annotation.Nonnull;

import com.rtalpha.base.core.exception.EmailException;

/**
 * 
 * Send an email to a specific recipient with the verification code for user
 * activation
 * 
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
public interface VerificationEmailService {

	boolean isAllowedToSend(@Nonnull String recipient);

	void sendVerificationCode(@Nonnull String recipient) throws EmailException;
}