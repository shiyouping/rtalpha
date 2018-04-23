package com.rtalpha.base.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.rtalpha.base.kernel.utility.ConfigLogger;

/**
 * The core configurations controlled by Base Core
 * <p>
 * Created by Ricky on 16/10/19.
 */
@Configuration
public class BaseCoreConfig {

	/**
	 * Enable reading values from environment via annotation
	 * {@linkplain org.springframework.beans.factory.annotation.Value}
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		ConfigLogger.info("Creating instance of PropertySourcesPlaceholderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}
}
