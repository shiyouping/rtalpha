package com.rtalpha.base.email.thymeleaf;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * @author Ricky
 * @since Apr 7, 2017
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.yml")
@ContextConfiguration(classes = ThymeleafAutoConfiguration.class)
public class ThymeleafTest {

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Test
	public void shouldProcess() {
		String host = "www.rtalpha.com";
		String template = "template-test";
		Context context = new Context();
		context.setVariable("host", host);

		String compiled = templateEngine.process(template, context);
		assertThat(compiled).contains(host);
		assertThat(compiled).doesNotContain("Hello World");
	}
}
