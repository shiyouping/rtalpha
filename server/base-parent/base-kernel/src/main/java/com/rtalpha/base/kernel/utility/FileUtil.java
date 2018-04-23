package com.rtalpha.base.kernel.utility;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

/**
 * @author Ricky
 * @since May 17, 2017
 */
public class FileUtil {

	private FileUtil() {
	}

	/**
	 * @param inputStream
	 *            The data to be written to the given location
	 * @param directory
	 *            The file directory where the given bytes will be written
	 * @param fileName
	 *            The file name with file extension if necessary
	 * @throws IOException
	 *             Failed to write the bytes to the given location
	 */
	public static void write(@Nonnull InputStream inputStream, @Nonnull String directory, @Nonnull String fileName)
			throws IOException {
		checkNotNull(inputStream, "inputStream cannot be null");
		write(ByteStreams.toByteArray(inputStream), directory, fileName);
	}

	/**
	 * @param data
	 *            The bytes to be written to the given location
	 * @param directory
	 *            The file directory where the given bytes will be written
	 * @param fileName
	 *            The file name with file extension if necessary
	 * @throws IOException
	 *             Failed to write the bytes to the given location
	 */
	public static void write(@Nonnull byte[] data, @Nonnull String directory, @Nonnull String fileName)
			throws IOException {
		checkNotNull(data, "data cannot be null");
		checkNotNull(directory, "directory cannot be null");
		checkNotNull(fileName, "fileName cannot be null");

		File folder = new File(directory);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		String fullName = directory + File.separator + fileName;
		File file = new File(fullName);

		if (!file.exists()) {
			Files.write(data, file);
		}
	}

	/**
	 * Check if the given file exists or not
	 * 
	 * @param path
	 *            the path of a file or directory
	 * @return true if and only if the file or directory denoted by this abstract
	 *         pathname exists; false otherwise
	 */
	public static boolean exists(@Nonnull String path) {
		checkNotNull(path, "path cannot be null");
		File file = new File(path);
		return file.exists();
	}
}
