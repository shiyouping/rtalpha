package com.rtalpha.ems.app

import org.springframework.cloud.contract.spec.Contract;

Contract.make{
	description "should return HTTP OK when the email address is valid"
	request {
		method GET()
		url("/api/internal/ems/v1/verificationCodes/send") {
			queryParameters { parameter "email": "helloworld@rtalpha.com" }
		}
	}

	response {
		status 200
		async()
		fixedDelayMilliseconds(60000)
	}
};