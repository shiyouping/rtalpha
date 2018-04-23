package com.rtalpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the main entry of this application, which is required by Spring Boot
 * <p>
 * Created by Ricky on 2016/11/10.
 */
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String... args) {
		new SpringApplicationBuilder(Application.class).build().run(args);
		logger.info("************************* Email Management System Started *************************");
	}
}