package com.rtalpha.pms.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.rtalpha.base.web.config.properties.BaseWebProperties;
import com.rtalpha.pms.app.document.Multipart;
import com.rtalpha.pms.app.mapper.MultipartDtoDocMapper;
import com.rtalpha.pms.app.mapper.MultipartDtoDocMapperImpl;
import com.rtalpha.pms.app.repository.MultipartRepository;
import com.rtalpha.pms.app.service.impl.MultipartServiceImpl;
import com.rtalpha.pms.kernel.dto.MultipartDto;

public class MultipartServiceTest {

	private MultipartRepository repository;
	private MultipartServiceImpl service;
	private String baseLocation;

	@Before
	public void startup() {
		repository = mock(MultipartRepository.class);
		baseLocation = getClass().getClassLoader().getResource("").getPath();

		BaseWebProperties properties = new BaseWebProperties();
		properties.getHttp().getMultipart().setBaseLocation(baseLocation);

		MultipartDtoDocMapper mapper = new MultipartDtoDocMapperImpl();
		service = new MultipartServiceImpl(mapper, repository, properties);

		ReflectionTestUtils.setField(service, "baseLocation", baseLocation);

		given(repository.findOne(anyString(), anyString(), anyString())).willReturn(null);
		given(repository.save(any(Multipart.class)))
				.willAnswer(invocation -> invocation.getArgumentAt(0, Multipart.class));
	}

	@Test
	public void shouldSave() throws IOException {
		MultipartDto multipart = service.save("Hello World".getBytes(), "txt");
		assertThat(multipart).isNotNull();
		assertThat(multipart.getCrc32()).isNotNull();
		assertThat(multipart.getMd5()).isNotNull();
		assertThat(multipart.getSha256()).isNotNull();
		assertThat(multipart.getLocation()).isNotNull();

		File file = new File(baseLocation + multipart.getLocation());
		assertThat(file.exists()).isTrue();
	}
}
