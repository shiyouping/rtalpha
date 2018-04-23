package com.rtalpha.ems.app

import org.springframework.cloud.contract.spec.Contract;

Contract.make{
	description "should return HTTP BAD_REQUEST when the email address is invalid"
	request {
		method GET()
		url("/api/internal/ems/v1/verificationCodes/send") {
			queryParameters { parameter "email": "helloworld" }
		}
	}

	response {
		status 400
		async()
	}
};