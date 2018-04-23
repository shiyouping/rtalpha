package com.rtalpha.base.web.security;

import javax.annotation.Nullable;

/**
 * 
 * @author Ricky
 * @since May 13, 2017
 *
 */
public interface EncryptionService {

	/**
	 * Encrypt the password if it is raw or just return the encoded password
	 * 
	 * @param password
	 *            raw or encoded password
	 * @return password after encryption
	 */
	@Nullable
	String encrypt(@Nullable String password);
}
