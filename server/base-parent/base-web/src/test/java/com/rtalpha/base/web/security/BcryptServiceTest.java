package com.rtalpha.base.web.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ricky
 * @since Jun 25, 2017
 *
 */
public class BcryptServiceTest {

	private static final String encoded = "$2a$15$2BKY4DAafJJWqnNtqy/GoObdhEXF65gXsTS5N1ZSlm7D7oWy2r72G";
	private static final String raw = "password";

	@Test
	public void shouldEncrypt() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		EncryptionService encryptionService = new BcryptServiceImpl(encoder);

		String one = encryptionService.encrypt(encoded);
		assertThat(one).isEqualTo(encoded);

		String two = encryptionService.encrypt(raw);
		assertThat(two).isNotEqualTo(raw);
	}
}
