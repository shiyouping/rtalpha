package com.rtalpha.ems.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;
import com.rtalpha.ems.app.document.VerificationCode;
import com.rtalpha.ems.app.mapper.VerificationCodeDtoDocMapper;
import com.rtalpha.ems.app.repository.VerificationCodeRepository;
import com.rtalpha.ems.app.service.api.VerificationCodeService;
import com.rtalpha.ems.app.service.impl.VerificationCodeServiceImpl;
import com.rtalpha.ems.kenel.dto.VerificationCodeDto;

public class VerificationCodeServiceTest {

	private static VerificationCodeDtoDocMapper mapper;
	private static VerificationCodeRepository repository;
	private static VerificationCodeService service;

	@BeforeClass
	public static void setup() {
		// given
		mapper = getMockMapper();
		repository = getMockRepository();
		service = new VerificationCodeServiceImpl(mapper, repository);
	}

	@Test
	public void shouldFindAll() {
		// when
		List<VerificationCodeDto> all = service.findAll();
		
		// then
		assertThat(all).isNotNull();
		assertThat(all.size()).isEqualTo(1);
		assertThat(all.get(0).getCode()).isEqualTo("code");
		verify(repository).findAll();
	}

	@Test
	public void shouldFindLatestOneByEmail() {
		// when
		VerificationCodeDto code = service.findLatestOneByEmail("email");
		
		// then
		assertThat(code).isNotNull();
		assertThat(code.getCode()).isEqualTo("code");
		verify(repository).findByEmail(anyString(), any(Sort.class));
	}

	@Test
	public void shouldFindOne() {
		// when
		VerificationCodeDto code = service.findOne("id");
		
		// then
		assertThat(code).isNotNull();
		assertThat(code.getCode()).isEqualTo("code");
		verify(repository).findOne(anyString());
	}

	@Test
	public void shouldSave() {
		// given
		VerificationCodeDto code = getDto();
		
		// when
		service.save(code);
		
		// then
		verify(repository).save(any(VerificationCode.class));
	}

	private static VerificationCode getDocument() {
		VerificationCode code = new VerificationCode();
		code.setCode("code");
		return code;
	}

	private static List<VerificationCode> getDocumentList() {
		return Lists.newArrayList(getDocument());
	}

	private static VerificationCodeDto getDto() {
		VerificationCodeDto code = new VerificationCodeDto();
		code.setCode("code");
		return code;
	}

	private static List<VerificationCodeDto> getDtoList() {
		return Lists.newArrayList(getDto());
	}

	private static VerificationCodeDtoDocMapper getMockMapper() {
		VerificationCodeDtoDocMapper mapper = mock(VerificationCodeDtoDocMapper.class);
		given(mapper.toDtoList(anyListOf(VerificationCode.class))).willReturn(getDtoList());
		given(mapper.toDto(any(VerificationCode.class))).willReturn(getDto());
		return mapper;
	}

	private static VerificationCodeRepository getMockRepository() {
		VerificationCodeRepository repository = mock(VerificationCodeRepository.class);
		given(repository.findOne(anyString())).willReturn(getDocument());
		given(repository.findAll()).willReturn(getDocumentList());
		given(repository.findByEmail(anyString(), any(Sort.class))).willReturn(getDocumentList());
		given(repository.save(any(VerificationCode.class))).willReturn(getDocument());
		return repository;
	}
}