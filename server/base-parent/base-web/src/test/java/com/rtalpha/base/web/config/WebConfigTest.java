package com.rtalpha.base.web.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rtalpha.base.kernel.constant.NamedProfile;

/**
 * @author Ricky
 * @since Dec 20, 2016
 *
 */
@WebMvcTest
@RunWith(SpringRunner.class)
@ActiveProfiles(NamedProfile.MODE_TEST)
@TestPropertySource("classpath:application-test.yml")
public class WebConfigTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldWork() throws Exception {
		mockMvc.perform(get("/test")).andExpect(status().isOk());
	}
}