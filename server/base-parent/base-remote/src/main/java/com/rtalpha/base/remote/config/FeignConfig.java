package com.rtalpha.base.remote.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Enable feign features in the clients
 * 
 * @author ricky.shi
 * @since 28 Mar 2018
 */
@Configuration
@EnableFeignClients
public class FeignConfig {
}