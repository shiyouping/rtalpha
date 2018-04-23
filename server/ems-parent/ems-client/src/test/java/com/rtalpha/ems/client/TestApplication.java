package com.rtalpha.ems.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ricky
 * @since Mar 4, 2018
 *
 */
@Configuration
@EnableFeignClients
@EnableAutoConfiguration
public class TestApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(TestApplication.class).run(args);
	}
}
