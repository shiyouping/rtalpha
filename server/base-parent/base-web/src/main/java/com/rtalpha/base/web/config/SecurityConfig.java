package com.rtalpha.base.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rtalpha.base.kernel.utility.ConfigLogger;

/**
 * @author Ricky
 * @since Feb 23, 2017
 *
 */
@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		ConfigLogger.info("Creating instance of BCryptPasswordEncoder");
		return new BCryptPasswordEncoder(15);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> configurer = auth
				.userDetailsService(userDetailsService);
		ConfigLogger.info("UserDetailsService configured");

		configurer.passwordEncoder(passwordEncoder());
		ConfigLogger.info("PasswordEncoder was enabled");
	}
}
