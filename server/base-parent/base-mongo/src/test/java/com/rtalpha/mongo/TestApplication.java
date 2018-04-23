package com.rtalpha.mongo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Ricky
 * @since Mar 4, 2018
 *
 */
@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(TestApplication.class).run(args);
	}
}
