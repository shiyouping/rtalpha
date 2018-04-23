package com.rtalpha.ums.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.rtalpha.ums.core.config.properties.UmsCoreProperties;

/**
 * @author Ricky Shi
 * @since Jul 18, 2017
 */
@Configuration("umsPropertiesConfig")
@EnableConfigurationProperties({ UmsCoreProperties.class })
public class PropertiesConfig {

}
