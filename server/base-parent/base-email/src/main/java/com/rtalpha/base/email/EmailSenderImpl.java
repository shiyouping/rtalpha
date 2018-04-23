package com.rtalpha.base.email;

import static com.google.common.base.Preconditions.checkArgument;

import javax.annotation.Nonnull;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.rtalpha.base.core.exception.EmailException;

/**
 * Default implementation of {@linkplain EmailSender}
 * <p>
 * Created by Ricky on 16/5/15.
 */
@Service
public class EmailSenderImpl implements EmailSender {

	private static final Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);

	private JavaMailSender mailSender;

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void send(@Nonnull String subject, @Nonnull String content, @Nonnull String sender,
			@Nonnull String recipient) throws EmailException {
		logger.debug("Sending email to recipient: {} with subject: {} and content: {}", recipient, subject, content);
		checkArgument(!Strings.isNullOrEmpty(subject), "subject cannot be null");
		checkArgument(!Strings.isNullOrEmpty(content), "content cannot be null");
		checkArgument(!Strings.isNullOrEmpty(recipient), "recipient cannot be null");

		try {
			MimeMessagePreparator preparator = new MimeMessagePreparatorImpl(sender, recipient, subject, content);
			mailSender.send(preparator);
		} catch (Exception e) {
			throw new EmailException(e);
		}
	}

	private class MimeMessagePreparatorImpl implements MimeMessagePreparator {

		private final String sender;
		private final String recipient;
		private final String subject;
		private final String text;

		public MimeMessagePreparatorImpl(String sender, String recipient, String subject, String text) {
			this.sender = sender;
			this.recipient = recipient;
			this.subject = subject;
			this.text = text;
		}

		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(recipient);
			helper.setFrom(sender);
			helper.setText(text, true);
			helper.setSubject(subject);
		}
	}
}
