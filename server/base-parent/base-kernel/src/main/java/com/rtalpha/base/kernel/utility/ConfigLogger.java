package com.rtalpha.base.kernel.utility;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log the configuration information at the INFO level, and add the prefix to
 * the message
 * 
 * @author Ricky
 * @since 2016.12.11
 */
public final class ConfigLogger {

	private static final Logger logger = LoggerFactory.getLogger(ConfigLogger.class);
	private static final String indicator = "*****";

	private ConfigLogger() {
	}

	/**
	 * Log a message at the INFO level according to the specified format and
	 * arguments.
	 * 
	 * @param message
	 *            configuration message to be logged
	 * @param arguments
	 *            a list of 3 or more arguments
	 */
	public static void info(@Nullable String message, Object... arguments) {
		logger.info(String.format("%s %s %s", indicator, message, indicator), arguments);
	}
}
