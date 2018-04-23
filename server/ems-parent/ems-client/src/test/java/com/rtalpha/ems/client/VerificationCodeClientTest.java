package com.rtalpha.ems.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.rtalpha.ems.client.VerificationCodeClientTest.LocalRibbonClientConfiguration;

/**
 * @author ricky.shi
 * @since 12 Apr 2018
 */
@DirtiesContext
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = { "com.rtalpha:ems-app:stubs:+:8080" })
@SpringBootTest(classes = { TestApplication.class, LocalRibbonClientConfiguration.class }, properties = {
		"eureka.client.enabled=false", "spring.cloud.config.failFast", "feign.hystrix.enabled=false",
		"service-name.ems=http://localhost:8080", "hystrix.command.default.execution.timeout.enabled=false",
		"ribbon.ReadTimeout=60000", "ribbon.ConnectTimeout=60000" })
public class VerificationCodeClientTest {

	@Autowired
	private VerificationCodeClient verificationCodeClient;

	@Test
	public void shouldReturnSuccess() {
		String validEmail = "helloworld@rtalpha.com";
		verificationCodeClient.send(validEmail);
	}

	@Test
	public void shouldReturnReject() {
		String invalidEmail = "helloworld";
		verificationCodeClient.send(invalidEmail);
	}

	@TestConfiguration
	public static class LocalRibbonClientConfiguration {
		@Bean
		public ServerList<Server> ribbonServerList() {
			return new StaticServerList<>(new Server("localhost", 8080));
		}
	}
}
