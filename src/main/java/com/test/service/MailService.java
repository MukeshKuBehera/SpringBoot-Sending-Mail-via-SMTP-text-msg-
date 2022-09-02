package com.test.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService {

	public boolean sendMail(String subject, String message, String to) throws IOException {

		boolean f = false;
		String from = "mukeshkubehera1@gmail.com";
//	  String password="Mukesh@1234";rkueicsexlxufpeh
		String password = "riczpurxdaalqymk";

		// String
		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		System.out.println("System details" + properties);

		// setting host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// to get the session object

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, password);
			}
		});

		session.setDebug(true);

		// compose the message [text,multi media]

		MimeMessage m = new MimeMessage(session);

		try {

			// set content type

			// from whom mail sending
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);
			// adding text to message
			m.setText(message);

			//sending message
			Transport.send(m);

			System.out.println("message sent sucessfully");

			f = true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

}
