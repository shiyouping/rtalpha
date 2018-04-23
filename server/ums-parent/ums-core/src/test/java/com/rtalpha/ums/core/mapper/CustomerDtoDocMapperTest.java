package com.rtalpha.ums.core.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.rtalpha.kernel.core.document.Customer;
import com.rtalpha.ums.remote.dto.CustomerDto;

@Test
public class CustomerDtoDocMapperTest {

	private static final CustomerDtoDocMapper MAPPER = new CustomerDtoDocMapperImpl();
	private static final DateTime NOW = DateTime.now();
	private static final String EMAIL = "email.com";

	public void shouldMapFromDocument() {
		Customer doc = aDocument();
		CustomerDto dto = MAPPER.toDto(doc);
		assertThat(dto.getCreatedTime()).isEqualTo(NOW);
		assertThat(dto.getEmail()).isEqualTo(EMAIL);
	}

	public void shouldMapFromDto() {
		CustomerDto dto = aDto();
		Customer doc = MAPPER.toDocument(dto);
		assertThat(doc.getCreatedTime()).isEqualTo(NOW);
		assertThat(doc.getEmail()).isEqualTo(EMAIL);
	}

	private CustomerDto aDto() {
		CustomerDto dto = new CustomerDto();
		dto.setCreatedTime(NOW);
		dto.setEmail(EMAIL);
		return dto;
	}

	private Customer aDocument() {
		Customer doc = new Customer();
		doc.setCreatedTime(NOW);
		doc.setEmail(EMAIL);
		return doc;
	}
}
