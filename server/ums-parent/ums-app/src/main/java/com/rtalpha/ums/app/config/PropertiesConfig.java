package com.rtalpha.ums.app.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.rtalpha.ums.app.config.properties.UmsAppProperties;

/**
 * @author Ricky Shi
 * @since Jul 18, 2017
 */
@Configuration("umsPropertiesConfig")
@EnableConfigurationProperties({ UmsAppProperties.class })
public class PropertiesConfig {

}
