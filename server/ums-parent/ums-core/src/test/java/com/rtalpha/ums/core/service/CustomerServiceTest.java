package com.rtalpha.ums.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.rtalpha.framework.core.constant.NamedProfile;
import com.rtalpha.framework.core.constant.NamedTestGroup;
import com.rtalpha.ums.core.service.CustomerServiceTest.CustomerServiceTestConfig;
import com.rtalpha.ums.core.service.api.CustomerService;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky
 * @since Jun 25, 2017
 *
 */
@SpringBootTest
@ActiveProfiles(NamedProfile.MODE_TEST)
@Test(groups = NamedTestGroup.MANUAL_TEST)
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = { CustomerServiceTestConfig.class })
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {

	private static final String NAME = "ricky";
	private static final String EMAIL = "ricky.shih@gmail.com";

	@Autowired
	private CustomerService service;
	private String id;

	public void shouldSave() {
		CustomerDto customer = aCustomer();
		CustomerDto saved = service.save(customer);
		id = saved.getId();

		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getCreatedTime()).isNotNull();
		assertThat(saved.getEmail()).isEqualTo(EMAIL);
		assertThat(saved.getName()).isEqualTo(NAME);
		assertThat(saved.getUpdatedTime()).isNotNull();
	}

	@Test(dependsOnMethods = "shouldSave")
	public void shouldFindOneByEmail() {
		CustomerDto customer = service.findOneByEmail(EMAIL);
		assertThat(customer.getId()).isEqualTo(id);
		assertThat(customer.getCreatedTime()).isNotNull();
		assertThat(customer.getEmail()).isEqualTo(EMAIL);
		assertThat(customer.getName()).isEqualTo(NAME);
	}

	@Test(dependsOnMethods = "shouldFindOneByEmail")
	public void shouldFindOne() {
		CustomerDto customer = service.findOne(id);
		assertThat(customer.getId()).isEqualTo(id);
		assertThat(customer.getCreatedTime()).isNotNull();
		assertThat(customer.getEmail()).isEqualTo(EMAIL);
		assertThat(customer.getName()).isEqualTo(NAME);
	}

	@Test(dependsOnMethods = "shouldFindOne")
	public void shouldFindAll() {
		List<CustomerDto> all = service.findAll();
		assertThat(all).isNotNull();
		assertThat(all.size()).isEqualTo(1);

		CustomerDto customer = all.get(0);
		assertThat(customer.getId()).isEqualTo(id);
		assertThat(customer.getCreatedTime()).isNotNull();
		assertThat(customer.getEmail()).isEqualTo(EMAIL);
		assertThat(customer.getName()).isEqualTo(NAME);
	}

	@Test(dependsOnMethods = "shouldFindAll")
	public void shouldCache() {
		System.out.println("===== shouldCache(): should not be invoked =====");
		service.findOne(id);
		service.findAll();
		System.out.println("===== shouldCache() done =====");
	}

	@Test(dependsOnMethods = "shouldCache")
	public void shouldNotCacheOne() {
		System.out.println("===== shouldNotCacheOne(): should be invoked =====");
		service.evictCurrentCache();
		service.findOne(id);
		service.findAll();
		System.out.println("===== shouldNotCacheOne(): done =====");
	}

	@Test(dependsOnMethods = { "shouldNotCacheOne" })
	public void shouldUpdate() {
		String newName = "tracy";
		CustomerDto customer = service.findOne(id);
		customer.setName(newName);

		CustomerDto newCustomer = service.update(customer);
		assertThat(newCustomer.getId()).isEqualTo(id);
		assertThat(newCustomer.getName()).isEqualTo(newName);
	}

	@Test(dependsOnMethods = { "shouldUpdate" })
	public void shouldNotCacheTwo() {
		System.out.println("===== shouldNotCacheTwo(): should be invoked =====");
		service.findOne(id);
		System.out.println("===== shouldNotCacheTwo(): done =====");
	}

	private CustomerDto aCustomer() {
		CustomerDto customer = new CustomerDto();
		customer.setEmail(EMAIL);
		customer.setName(NAME);
		return customer;
	}

	@TestConfiguration
	public static class CustomerServiceTestConfig {

		@Bean
		public JavaMailSender mailSender() {
			return new JavaMailSenderImpl();
		}
	}
}
