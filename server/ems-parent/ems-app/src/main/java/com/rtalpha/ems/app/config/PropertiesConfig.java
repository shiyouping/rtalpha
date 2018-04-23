package com.rtalpha.ems.app.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.rtalpha.ems.app.config.properties.EmsAppProperties;

/**
 * @author Ricky Shi
 * @since Jul 18, 2017
 */
@Configuration("emsPropertiesConfig")
@EnableConfigurationProperties({ EmsAppProperties.class })
public class PropertiesConfig {

}
