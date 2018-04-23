package com.rtalpha.base.web.security;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Encrypts string by using Bcrypt
 * 
 * @author Ricky
 * @since May 13, 2017
 *
 */
@Service
public class BcryptServiceImpl implements EncryptionService {

	private static Pattern bcryptPattern = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

	private final PasswordEncoder encoder;

	@Autowired
	public BcryptServiceImpl(@Nonnull PasswordEncoder encoder) {
		checkNotNull(encoder, "encoder cannot be null");

		if (!(encoder instanceof BCryptPasswordEncoder)) {
			throw new IllegalArgumentException("BCryptPasswordEncoder required.");
		}

		this.encoder = encoder;
	}

	@Nullable
	@Override
	public String encrypt(@Nullable String password) {
		if (password == null) {
			return null;
		}

		if (isEncrypted(password)) {
			return password;
		}

		return encoder.encode(password);
	}

	/**
	 * Check if the input has been encrypted by Bcrypt
	 */
	private boolean isEncrypted(@Nonnull String input) {
		checkNotNull(input, "input cannot be null");
		return bcryptPattern.matcher(input).matches();
	}
}
