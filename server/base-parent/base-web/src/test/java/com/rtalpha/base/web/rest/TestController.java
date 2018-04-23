package com.rtalpha.base.web.rest;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.base.core.exception.ApplicationException;
import com.rtalpha.base.core.exception.ApplicationException.ExceptionType;

/**
 * @author Ricky
 * @since Dec 14, 2016
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping(method = RequestMethod.GET)
	public Callable<String> getSuccess() {
		return () -> {
			return "success";
		};
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public Callable<String> getError() {
		return () -> {
			throw new ApplicationException("error", ExceptionType.GENERIC);
		};
	}
}
