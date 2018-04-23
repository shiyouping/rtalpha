package com.rtalpha.ems.client.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rtalpha.base.remote.utility.ClientFallbackUtil;
import com.rtalpha.ems.client.VerificationCodeClient;

/**
 * @author ricky.shi
 * @since 9 Apr 2018
 */
public class VerificationCodeClientFallback implements VerificationCodeClient {

	private static final Logger logger = LoggerFactory.getLogger(VerificationCodeClientFallback.class);

	@Override
	public void send(String email) {
		logger.error(ClientFallbackUtil.MESSAGE_PREFIX + "Cannot send email={}", email);
	}
}
