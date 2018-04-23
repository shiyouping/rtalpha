package com.rtalpha.base.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ricky
 * @since Mar 22, 2017
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BaseCoreConfig.class)
@TestPropertySource(properties = { "driver=test" })
public class BaseCoreConfigTest {

	@Value("${driver}")
	private String driver;

	@Test
	public void shouldEqual() {
		assertThat(driver).isEqualTo("test");
	}
}
