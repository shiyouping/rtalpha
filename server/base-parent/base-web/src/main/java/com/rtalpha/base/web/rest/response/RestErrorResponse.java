package com.rtalpha.base.web.rest.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.http.HttpStatus;

import com.rtalpha.base.kernel.pojo.BasePojo;
import com.rtalpha.base.kernel.utility.DateTimeUtil;

/**
 * The only and universal error response returned to client when something wrong
 * happens
 * 
 * @author Ricky
 * @since Dec 18, 2016
 *
 */
@AutoProperty
public class RestErrorResponse extends BasePojo {

	public static final String MESSAGE_KEY_DETAIL = "detail";
	public static final String MESSAGE_KEY_EXCEPTION_TYPE = "exceptionType";
	public static final String MESSAGE_KEY_SUPPORTED_MEDIA_TYPES = "supportedMediaTypes";

	private static final long serialVersionUID = 1L;

	private final String timestamp;
	private final int status;
	private final String error;
	private final String path;
	private final Map<String, ?> messages;
	private final String code;

	public RestErrorResponse(@Nonnull HttpStatus status, @Nonnull Map<String, ?> messages, @Nullable String path,
			@Nullable String code) {
		checkNotNull(status, "status cannot be null");
		checkNotNull(messages, "messages cannot be null");

		this.timestamp = DateTimeUtil.toDateAndTimeString(DateTime.now());
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.messages = messages;
		this.path = path;
		this.code = code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getCode() {
		return code;
	}

	public Map<String, ?> getMessages() {
		return messages;
	}

	public String getPath() {
		return path;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}
}