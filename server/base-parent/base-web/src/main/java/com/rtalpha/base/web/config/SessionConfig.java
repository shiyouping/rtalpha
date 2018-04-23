package com.rtalpha.base.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.rtalpha.base.kernel.utility.ConfigLogger;

/**
 * @author Ricky
 * @since Mar 27, 2017
 */
@Configuration
@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 60 * 30, collectionName = "HttpSession")
public class SessionConfig {

	/**
	 * Customize Spring Session’s HttpSession integration to use HTTP headers to
	 * convey the current session information instead of cookies.
	 */
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		ConfigLogger.info("Creating instance of HttpSessionStrategy");
		return new HeaderHttpSessionStrategy();
	}

	/**
	 * Explicitly configure JdkMongoSessionConverter since Spring Security’s objects
	 * cannot be automatically persisted using Jackson (the default if Jackson is on
	 * the classpath).
	 * 
	 */
	@Bean
	public JdkMongoSessionConverter jdkMongoSessionConverter() {
		ConfigLogger.info("Creating instance of JdkMongoSessionConverter");
		return new JdkMongoSessionConverter();
	}
}