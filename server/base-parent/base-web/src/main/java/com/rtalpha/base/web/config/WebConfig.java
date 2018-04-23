package com.rtalpha.base.web.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rtalpha.base.core.config.BaseCoreConfig;
import com.rtalpha.base.kernel.utility.ConfigLogger;
import com.rtalpha.base.web.config.properties.BaseWebProperties;
import com.rtalpha.base.web.interceptor.HttpLocationInterceptor;
import com.rtalpha.base.web.interceptor.HttpTrafficInterceptor;

/**
 * Configurations for Spring MVC
 * 
 * @author Ricky
 * @since 2016.12.4
 */
@RefreshScope
@Configuration
@ServletComponentScan
@Import(BaseCoreConfig.class)
public class WebConfig {

	private final BaseWebProperties properties;

	@Autowired
	public WebConfig(@Nonnull BaseWebProperties properties) {
		checkNotNull(properties, "properties cannot be null");
		this.properties = properties;
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurerAdapter() {
		ConfigLogger.info("Creating instance of WebMvcConfigurer");
		return new WebMvcAdapter();
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ConfigLogger.info("Creating instance of ThreadPoolTaskExecutor");
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(properties.getHttp().getTaskExecutor().getCorePoolSize());
		taskExecutor.setMaxPoolSize(properties.getHttp().getTaskExecutor().getMaxPoolSize());
		taskExecutor.setQueueCapacity(properties.getHttp().getTaskExecutor().getQueueCapacity());
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return taskExecutor;
	}

	private class WebMvcAdapter extends WebMvcConfigurerAdapter {

		@Override
		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
			ConfigLogger.info("Configurating async support");
			configurer.setTaskExecutor(threadPoolTaskExecutor());
			super.configureAsyncSupport(configurer);
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new HttpLocationInterceptor());
			ConfigLogger.info("{} added.", HttpLocationInterceptor.class.getSimpleName());

			registry.addInterceptor(new HttpTrafficInterceptor());
			ConfigLogger.info("{} added.", HttpTrafficInterceptor.class.getSimpleName());

			super.addInterceptors(registry);
		}
	}
}
