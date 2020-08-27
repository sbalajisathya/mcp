/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.config;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


/**
 * The Class MailService.
 */
@Component
public class MailService {

	/** The java mail sender. */
	@Autowired
	private JavaMailSender javaMailSender;

	/** The template processor. */
	@Autowired
	TemplateProcessor templateProcessor;

	/** The crypto util. */
	@Autowired
	private CryptoUtil cryptoUtil;

	/**
	 * Send success acknowledgement method.
	 *
	 * @param data the data
	 * @throws Exception the exception
	 */
	public void sendSuccessAcknowledgement(Map<String, String> data) throws Exception {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		String html = templateProcessor.getEmailContent(data, "success-acknowledement-template.ftl");

		helper.setTo(cryptoUtil.decrypt(data.get("emailId")));
		helper.setText(html, true);
		helper.setSubject("Credit Card Request - Acknowledgement");

		javaMailSender.send(message);

	}

	/**
	 * Send failure acknowledgement method.
	 *
	 * @param data the data
	 * @throws Exception the exception
	 */
	public void sendFailureAcknowledgement(Map<String, String> data) throws Exception {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		String html = templateProcessor.getEmailContent(data, "failure-acknowledement-template.ftl");

		helper.setTo(cryptoUtil.decrypt(data.get("emailId")));
		helper.setText(html, true);
		helper.setSubject("Credit Card Request - Acknowledgement");

		javaMailSender.send(message);

	}
}
