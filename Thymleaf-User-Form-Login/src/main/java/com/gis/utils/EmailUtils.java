package com.gis.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender emailSender;

	public void sendEmail(String to, String subject, String text) {

		try {
			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message);


			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			emailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}