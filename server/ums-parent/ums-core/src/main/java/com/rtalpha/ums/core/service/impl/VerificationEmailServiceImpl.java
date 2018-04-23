package com.rtalpha.ums.core.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.google.common.collect.Maps;
import com.rtalpha.framework.core.email.EmailSender;
import com.rtalpha.framework.core.exception.EmailException;
import com.rtalpha.framework.core.utility.RandomString;
import com.rtalpha.ums.core.config.properties.UmsCoreProperties;
import com.rtalpha.ums.core.service.api.VerificationCodeService;
import com.rtalpha.ums.core.service.api.VerificationEmailService;
import com.rtalpha.ums.remote.dto.VerificationCodeDto;

/**
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
@Service
public class VerificationEmailServiceImpl implements VerificationEmailService {

	private static final Logger logger = LoggerFactory.getLogger(VerificationEmailServiceImpl.class);
	private static final Map<String, Long> verificationRecords = Maps.newConcurrentMap();

	private final EmailSender emailSender;
	private final VerificationCodeService codeService;
	private final SpringTemplateEngine templateEngine;
	private final UmsCoreProperties properties;
	private final String sender;

	@Autowired
	public VerificationEmailServiceImpl(@Nonnull EmailSender emailSender, @Nonnull SpringTemplateEngine templateEngine,
			@Nonnull VerificationCodeService codeService, @Nonnull UmsCoreProperties properties,
			@Nonnull @Value("${spring.mail.username}") String sender) {
		checkNotNull(emailSender, "emailSender cannot be null");
		checkNotNull(templateEngine, "templateEngine cannot be null");
		checkNotNull(codeService, "codeService cannot be null.");
		checkNotNull(properties, "properties cannot be null");
		checkNotNull(sender, "sender cannot be null");

		this.emailSender = emailSender;
		this.templateEngine = templateEngine;
		this.codeService = codeService;
		this.properties = properties;
		this.sender = sender;
	}

	@Scheduled(fixedRate = 1000L * 60L * 5L)
	public void clearVerificationRecords() {
		logger.debug("Clearing verification records...");

		for (Entry<String, Long> entry : verificationRecords.entrySet()) {
			Long lastSent = entry.getValue();
			if (System.currentTimeMillis() - lastSent > properties.getUserVerification().getCodeLifespanInMinute()
					* 1000L * 60L) {
				verificationRecords.remove(entry.getKey());
			}
		}
	}

	@Override
	public boolean isAllowedToSend(String recipient) {
		checkNotNull(recipient, "recipient cannot be null");

		if (!verificationRecords.containsKey(recipient)) {
			return true;
		}

		Long lastSent = verificationRecords.get(recipient);
		if (System.currentTimeMillis()
				- lastSent > Long.valueOf(properties.getUserVerification().getCodeLifespanInMinute()) * 1000L * 60L) {
			return true;
		}

		return false;
	}

	@Override
	public void sendVerificationCode(@Nonnull String recipient) throws EmailException {
		logger.info("Sending verification code email to {}", recipient);
		checkNotNull(recipient, "recipient cannot be null.");

		String verificationCode = RandomString.generate(properties.getUserVerification().getCodeLength(), true, true);
		saveVerificationCode(recipient, verificationCode);
		String content = generateContent(verificationCode);
		emailSender.send(properties.getUserVerification().getEmailSubject(), content, sender, recipient);

		verificationRecords.put(recipient, System.currentTimeMillis());
	}

	private String generateContent(String verificationCode) {
		Context context = new Context();
		context.setVariable("verificationCode", verificationCode);
		return templateEngine.process(properties.getUserVerification().getTemplate(), context);
	}

	private void saveVerificationCode(String recipient, String code) {
		VerificationCodeDto verificationCode = new VerificationCodeDto();
		verificationCode.setCode(code);
		verificationCode.setEmail(recipient);
		verificationCode
				.setValidBefore(DateTime.now().plusMinutes(properties.getUserVerification().getCodeLifespanInMinute()));
		codeService.save(verificationCode);
	}
}
