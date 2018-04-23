package com.rtalpha.ems.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.ems.client.fallback.VerificationCodeClientFallbackFactory;

/**
 * @author ricky.shi
 * @since 9 Apr 2018
 */
@FeignClient(name = "${service-name.ems}", path = "/api/internal/ems/v1/verificationCodes", fallbackFactory = VerificationCodeClientFallbackFactory.class)
public interface VerificationCodeClient {

	@RequestMapping(method = RequestMethod.GET, path = "/send")
	public void send(@RequestParam(value = "email") final String email);
}
