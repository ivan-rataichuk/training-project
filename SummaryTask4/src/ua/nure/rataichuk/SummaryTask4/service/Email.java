package ua.nure.rataichuk.SummaryTask4.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.entities.Emessage;

/**
 * email distribution service
 * 
 * @author Ivan Rataichuk
 *
 */

public class Email extends Authenticator{
	
	private static final Logger LOG = Logger.getLogger(Email.class);
	
	private static final String EMAIL_USER_NAME = "rataichuktestapp";
	
	private static final String EMAIL = "rataichuktestapp@gmail.com";
	
	private static final String EMAIL_PASSWORD = "air051088";
	
	private static final String ADMIN_EMAIL = "rockarolla6666@gmail.com";
	
	/**
	 * Send message via SSL
	 * 
	 * @param mes
	 */
	public void SendMailSSL (Emessage mes) {
		
		Validation vld = new Validation();
		if (!vld.emailMessage(mes)) {
			return;
		}
		
		Properties props = new Properties();
		props.put("mail.smtp.user", EMAIL);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(EMAIL, EMAIL_PASSWORD);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mes.getMailFrom()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mes.getMailTo()));
			message.setSubject(mes.getSubject());
			message.setText(mes.getText());

//			Transport.send(message);
			
			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", 465, EMAIL_USER_NAME, EMAIL_PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			LOG.error(e.getMessage());
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Send email to administrator
	 * 
	 * @param mailFrom
	 * @param subject
	 * @param text
	 */
	public void sendMessageToAdmins(String mailFrom, String subject, String text) {
		Emessage mes = new Emessage();
		mes.setMailFrom(mailFrom);
		mes.setSubject(subject);
		mes.setText(text);
		mes.setMailTo(ADMIN_EMAIL);
		
		Validation vld = new Validation();
		if (!vld.emailMessage(mes)) {
			return;
		}
		SendMailSSL(mes);
	}
}
