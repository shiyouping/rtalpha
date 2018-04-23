package com.rtalpha.base.remote.utility;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import org.joda.time.DateTime;
import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * @author Ricky
 * @since Mar 31, 2018
 *
 */
@SuppressWarnings("unchecked")
public class ClientFallbackUtil {

	public static final String MESSAGE_PREFIX = "Something wrong with remote service. ";

	public static <D extends BaseDto> D createFallbackDto(@Nonnull Class<D> clazz, @Nonnull Logger logger,
			@Nonnull String format, Object... arguments) {
		checkNotNull(logger, "logger cannot be null");
		checkNotNull(format, "format cannot be null");

		logger.error(MESSAGE_PREFIX + format, arguments);
		return createFallbackDto(clazz, logger);
	}

	public static <D extends BaseDto> List<D> createFallbackList(@Nonnull Logger logger, @Nonnull String format,
			Object... arguments) {
		checkNotNull(logger, "logger cannot be null");
		checkNotNull(format, "format cannot be null");

		logger.error(MESSAGE_PREFIX + format, arguments);
		return Lists.newArrayListWithExpectedSize(0);
	}

	public static <D extends BaseDto> D createOne(@Nonnull D dto, @Nonnull Logger logger) {
		checkNotNull(dto, "dto cannot be null");
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot save {}.", dto);

		return (D) createFallbackDto(dto.getClass(), logger);
	}

	public static <D extends BaseDto> void deleteOne(@Nonnull D dto, @Nonnull Logger logger) {
		checkNotNull(dto, "dto cannot be null");
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot delete {}.", dto);
	}

	public static void deleteOneById(@Nonnull String id, @Nonnull Logger logger) {
		checkNotNull(id, "id cannot be null");
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot delete dto with id={}.", id);
	}

	public static <D extends BaseDto> List<D> findAll(@Nonnull Logger logger) {
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot find all dtos. Returning an empty list");
		return Lists.newArrayListWithExpectedSize(0);
	}

	public static <D extends BaseDto> List<D> findAllByText(@Nonnull String[] texts, @Nonnull Logger logger) {
		checkNotNull(texts, "texts cannot be null");
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot find dtos by text={}. Returning an empty list",
				Lists.newArrayList(texts));
		return Lists.newArrayListWithExpectedSize(0);
	}

	public static <D extends BaseDto> D findOneById(@Nonnull String id, @Nonnull Class<D> clazz,
			@Nonnull Logger logger) {
		checkNotNull(id, "id cannot be null");
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot find dto with id={}. ", id);
		return createFallbackDto(clazz, logger);
	}

	public static <D extends BaseDto> D updateOne(@Nonnull D dto, @Nonnull Logger logger) {
		checkNotNull(dto, "dto cannot be null");
		checkNotNull(logger, "logger cannot be null");
		logger.error(MESSAGE_PREFIX + "Cannot update {}.", dto);
		return (D) createFallbackDto(dto.getClass(), logger);
	}

	private static <D extends BaseDto> D createFallbackDto(@Nonnull Class<D> clazz, @Nonnull Logger logger) {
		checkNotNull(clazz, "clazz cannot be null");
		checkNotNull(logger, "logger cannot be null");

		try {
			DateTime time = new DateTime(0, 1, 1, 0, 0);
			D dto = clazz.newInstance();

			dto.setId("Dummy ID from client fallback");
			dto.setCreatedTime(time);
			dto.setUpdatedTime(time);

			logger.error("Returning a dummy dto for fallback. Dummy dto = {}", dto);
			return dto;
		} catch (Exception e) {
			logger.error("Failed to create a dummy dto, return null instead", e);
			return null;
		}
	}
}
