package com.rtalpha.base.kernel.utility;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;

/**
 * Generate hash code for byte array and {@linkplain InputStream}
 * 
 * @author Ricky
 * @since May 18, 2017
 */
public class HashUtil {

	private HashUtil() {
	}

	/**
	 * Returns MD5 string as a two-digit unsigned hexadecimal number in lower case.
	 */
	@Nonnull
	public static String getMd5(@Nonnull InputStream inputStream) throws IOException {
		checkNotNull(inputStream, "inputStream cannot be null");
		return getMd5(ByteStreams.toByteArray(inputStream));
	}

	/**
	 * Returns MD5 string as a two-digit unsigned hexadecimal number in lower case.
	 */
	@Nonnull
	@SuppressWarnings("deprecation")
	public static String getMd5(@Nonnull byte[] data) {
		checkNotNull(data, "data cannot be null");
		return getHashCode(data, Hashing.md5());
	}

	/**
	 * Returns CRC32 string as a two-digit unsigned hexadecimal number in lower
	 * case.
	 */
	@Nonnull
	public static String getCrc32(@Nonnull InputStream inputStream) throws IOException {
		checkNotNull(inputStream, "inputStream cannot be null");
		return getCrc32(ByteStreams.toByteArray(inputStream));
	}

	/**
	 * Returns CRC32 string as a two-digit unsigned hexadecimal number in lower
	 * case.
	 */
	@Nonnull
	public static String getCrc32(@Nonnull byte[] data) {
		checkNotNull(data, "data cannot be null");
		return getHashCode(data, Hashing.crc32());
	}

	/**
	 * Returns SHA256 string as a two-digit unsigned hexadecimal number in lower
	 * case.
	 */
	@Nonnull
	public static String getSha256(@Nonnull InputStream inputStream) throws IOException {
		checkNotNull(inputStream, "inputStream cannot be null");
		return getSha256(ByteStreams.toByteArray(inputStream));
	}

	/**
	 * Returns SHA256 string as a two-digit unsigned hexadecimal number in lower
	 * case.
	 */
	@Nonnull
	public static String getSha256(@Nonnull byte[] data) {
		checkNotNull(data, "data cannot be null");
		return getHashCode(data, Hashing.sha256());
	}

	private static String getHashCode(byte[] data, HashFunction function) {
		Hasher hasher = function.newHasher(data.length);
		HashCode hashCode = hasher.putBytes(data).hash();
		return hashCode.toString();
	}
}
