package com.rtalpha.base.email;

import static org.assertj.core.api.Assertions.assertThat;

import javax.mail.Address;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.rtalpha.base.email.EmailSenderTest.EmailTestConfig;
import com.rtalpha.base.kernel.constant.NamedProfile;

/**
 * Created by Ricky on 16/5/15.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(NamedProfile.MODE_TEST)
@TestPropertySource("classpath:application-test.yml")
@ContextConfiguration(classes = { MailSenderAutoConfiguration.class, EmailTestConfig.class })
public class EmailSenderTest {

	private static final String subject = "Hello";
	private static final String content = "Hello world";
	private static final String recipient = "ricky.shih@qq.com";

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private int port;
	@Value("${spring.mail.protocol}")
	private String protocol;
	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private EmailSender emailSender;
	private GreenMail server;

	@Before
	public void startup() {
		long timeout = 1000 * 10;
		ServerSetup config = new ServerSetup(port, host, protocol);
		config.setConnectionTimeout(timeout);
		config.setReadTimeout(timeout);
		config.setServerStartupTimeout(timeout);
		config.setWriteTimeout(timeout);
		server = new GreenMail(config);
		server.start();
	}

	@After
	public void shutdown() {
		server.stop();
	}

	@Test
	public void testSend() throws Exception {
		emailSender.send(subject, content, sender, recipient);

		assertThat(server.waitForIncomingEmail(1000 * 10, 1)).isTrue();

		MimeMessage[] receivedMessages = server.getReceivedMessages();
		assertThat(receivedMessages).isNotNull();
		assertThat(receivedMessages.length).isEqualTo(1);

		MimeMessage message = receivedMessages[0];
		assertThat(message.getSubject()).isEqualTo(subject);
		assertThat((String) message.getContent()).contains(content);

		Address[] from = message.getFrom();
		assertThat(from).isNotNull();
		assertThat(from.length).isEqualTo(1);
		assertThat(from[0].toString()).isEqualTo(this.sender);

		Address[] recipients = message.getAllRecipients();
		assertThat(recipients).isNotNull();
		assertThat(recipients.length).isEqualTo(1);
		assertThat(recipients[0].toString()).isEqualTo(recipient);
	}

	@TestConfiguration
	public static class EmailTestConfig {
		@Bean
		public EmailSender emailSender(JavaMailSender mailSender) {
			EmailSenderImpl emailSender = new EmailSenderImpl();
			emailSender.setMailSender(mailSender);
			return emailSender;
		}
	}
}
