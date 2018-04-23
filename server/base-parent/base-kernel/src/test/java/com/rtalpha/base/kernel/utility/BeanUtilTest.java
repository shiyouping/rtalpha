package com.rtalpha.base.kernel.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author Ricky Shi
 * @since Jul 24, 2017
 */
public class BeanUtilTest {

	@Test
	public void shouldCopyNonnullProperties() throws Exception {
		TestDto source = source();
		TestModel destination = destination();

		assertThat(source.name).isNull();
		assertThat(destination.getAddress()).isNull();

		BeanUtil.copyNonnullProperties(destination, source);
		assertThat(destination.getAddress()).isEqualTo(source.getAddress());
		assertThat(destination.getAge()).isEqualTo(source.getAge());
		assertThat(destination.getName()).isNotNull();
	}

	private TestDto source() {
		TestDto dto = new TestDto();
		dto.setAddress("add");
		dto.setAge(11);
		return dto;
	}

	private TestModel destination() {
		TestModel model = new TestModel();
		model.setName("name");
		return model;
	}

	public static class TestDto {
		private String name;
		private String address;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

	public static class TestModel {
		private String name;
		private String address;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
