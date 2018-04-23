package com.rtalpha.base.email;

import javax.annotation.Nonnull;

import com.rtalpha.base.core.exception.EmailException;

/**
 * Defines the methods of sending email message
 * <p>
 * <p>
 * Created by Ricky on 16/5/15.
 */
public interface EmailSender {

	/**
	 * Send an email to the given recipient with the specified subject and
	 * content
	 */
	void send(@Nonnull String subject, @Nonnull String content, @Nonnull String sender, @Nonnull String recipient)
			throws EmailException;

}
