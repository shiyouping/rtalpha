package com.rtalpha.base.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.rtalpha.base.web.config.properties.BaseWebProperties;

/**
 * @author Ricky Shi
 * @since Jul 18, 2017
 */
@Configuration("baseWebPropertiesConfig")
@EnableConfigurationProperties({ BaseWebProperties.class })
public class PropertiesConfig {
}
