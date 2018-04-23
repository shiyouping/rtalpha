package com.rtalpha.ums.core.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.rtalpha.framework.core.constant.NamedTestGroup;
import com.rtalpha.kernel.core.document.Customer;
import com.rtalpha.ums.core.repository.CustomerRepository;

/**
 * @author Ricky
 * @since Apr 15, 2017
 *
 */
@DataMongoTest
@Test(groups = NamedTestGroup.MANUAL_TEST)
public class CustomerRepositoryTest extends AbstractTestNGSpringContextTests {

	private static final String NAME = "ricky";
	private static final String EMAIL = "ricky.shih@foxmail.com";
	private static final DateTime NOW = DateTime.now();

	@Autowired
	private CustomerRepository repository;

	private String id;

	public void shouldCreate() {
		Customer newCustomer = aCustomer();
		Customer savedCustomer = repository.save(newCustomer);
		id = savedCustomer.getId();

		assertThat(savedCustomer.getId()).isNotNull();
		assertThat(savedCustomer.getCreatedTime()).isEqualTo(NOW);
		assertThat(savedCustomer.getEmail()).isEqualTo(EMAIL);
		assertThat(savedCustomer.getName()).isEqualTo(NAME);
		assertThat(savedCustomer.getUpdatedTime()).isEqualTo(NOW);
	}

	@Test(dependsOnMethods = "shouldCreate")
	public void shouldRead() {
		Customer customer = repository.findOne(id);
		assertThat(customer.getId()).isNotNull();
		assertThat(customer.getCreatedTime()).isEqualTo(NOW);
		assertThat(customer.getEmail()).isEqualTo(EMAIL);
		assertThat(customer.getName()).isEqualTo(NAME);
		assertThat(customer.getUpdatedTime()).isEqualTo(NOW);
	}

	@Test(dependsOnMethods = "shouldRead")
	public void shouldUpdate() {
		String newEmail = "ricky@gmail.com";
		Customer oldCustomer = repository.findOne(id);
		oldCustomer.setEmail(newEmail);
		Customer newCustomer = repository.save(oldCustomer);
		assertThat(newCustomer.getEmail()).isEqualTo(newEmail);
	}

	@Test(dependsOnMethods = "shouldUpdate")
	public void shouldDelete() {
		repository.delete(id);
		Customer customer = repository.findOne(id);
		assertThat(customer).isNull();
	}

	@Test(dependsOnMethods = "shouldCreate")
	public void shouldFindOneByEmail() {
		Customer customer = repository.findOneByEmail(EMAIL);
		assertThat(customer).isNotNull();
		assertThat(customer.getEmail()).isEqualTo(EMAIL);
	}

	@Test(dependsOnMethods = "shouldCreate")
	public void shouldNotFindActiveOneByEmail() {
		Customer customer = repository.findOneByEmail(EMAIL, true);
		assertThat(customer).isNull();
	}

	@Test(dependsOnMethods = "shouldCreate")
	public void shouldNotFindActiveOneById() {
		Customer customer = repository.findOneById(id, true);
		assertThat(customer).isNull();
	}

	@Test(dependsOnMethods = "shouldCreate")
	public void shouldFindActiveAll() {
		List<Customer> all = repository.findAll(true);
		assertThat(all).isEmpty();
	}

	private Customer aCustomer() {
		Customer customer = new Customer();
		customer.setCreatedTime(NOW);
		customer.setEmail(EMAIL);
		customer.setName(NAME);
		customer.setUpdatedTime(NOW);
		customer.setIsActive(false);
		return customer;
	}
}
