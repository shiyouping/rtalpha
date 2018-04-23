package com.rtalpha.ems.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.rtalpha.Application;
import com.rtalpha.base.kernel.constant.NamedProfile;
import com.rtalpha.base.web.handler.GenericExceptionHandler;
import com.rtalpha.base.web.handler.SpecificExceptionHandler;
import com.rtalpha.ems.app.VerificationCodeBase.EmailTestConfig;
import com.rtalpha.ems.app.rest.internal.VerificationCodeController;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * @author ricky.shi
 * @since 11 Apr 2018
 */
@DirtiesContext
@RunWith(SpringRunner.class)
@AutoConfigureMessageVerifier
@ActiveProfiles(NamedProfile.MODE_TEST)
@TestExecutionListeners(VerificationCodeBase.class)
@SpringBootTest(classes = { Application.class, EmailTestConfig.class })
@TestPropertySource(properties = { "spring.cloud.discovery.enabled=false", "spring.cloud.config.enabled=false",
		"spring.cloud.config.discovery.enabled=false", "eureka.client.registerWithEureka=false",
		"eureka.client.fetchRegistry=false" }, locations = "classpath:/application-test.yml")
public class VerificationCodeBase extends AbstractTestExecutionListener {

	private static GreenMail server;

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		ApplicationContext context = testContext.getApplicationContext();
		configMailSender(context);
		startMailServer(context);
		startMockMvc(context);
	}

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		server.stop();
	}

	private void configMailSender(ApplicationContext context) {
		Environment environment = context.getEnvironment();
		String host = environment.getProperty("spring.mail.host");
		int port = Integer.parseInt(environment.getProperty("spring.mail.port"));
		String protocol = environment.getProperty("spring.mail.protocol");

		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) context.getBean(JavaMailSender.class);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
	}

	private void startMailServer(ApplicationContext context) {
		Environment environment = context.getEnvironment();
		String host = environment.getProperty("spring.mail.host");
		int port = Integer.parseInt(environment.getProperty("spring.mail.port"));
		String protocol = environment.getProperty("spring.mail.protocol");
		long timeout = 1000 * 10;

		ServerSetup config = new ServerSetup(port, host, protocol);
		config.setConnectionTimeout(timeout);
		config.setReadTimeout(timeout);
		config.setServerStartupTimeout(timeout);
		config.setWriteTimeout(timeout);
		server = new GreenMail(config);
		server.start();
	}

	private void startMockMvc(ApplicationContext context) {
		VerificationCodeController controller = context.getBean(VerificationCodeController.class);
		StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(controller).setAsyncRequestTimeout(60000)
				.setControllerAdvice(new GenericExceptionHandler(), new SpecificExceptionHandler());
		RestAssuredMockMvc.standaloneSetup(mvcBuilder);
	}

	@TestConfiguration
	public static class EmailTestConfig {
		@Bean
		public JavaMailSender javaMailSender() {
			return new JavaMailSenderImpl();
		}
	}
}