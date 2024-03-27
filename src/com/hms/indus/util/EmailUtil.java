package com.hms.indus.util;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail,
			String subject, String body) {
		try {
			final MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("connectmyindus@gmail.com"));
			// msg.setReplyTo(InternetAddress.parse("ehralert@indushealthplus.com"));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			// msg.setText(body, "UTF-8" ,"html");
			msg.setSentDate(new Date());
			msg.setContent(body, "text/html");
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Transport.send(msg);
						System.err.println("Email Sent Successfully...");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendEmailWithAttach(Session session, String toEmail,
			String subject, String body, String attachment) {
		try {
			final MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("connectmyindus@gmail.com"));
			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			
			// Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setText(body);
	         Multipart multipart = new MimeMultipart();
	         multipart.addBodyPart(messageBodyPart);
	         
	         // attachment
	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(attachment);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         String filename = attachment.substring(attachment.lastIndexOf('/')+1,attachment.length());
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart);

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Transport.send(msg);
						System.err.println("Email Sent Successfully...");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMailWithPdfPassword(Session session, String toEmail,
			String subject, String body) {
		try {
			final MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("connectmyindus@gmail.com"));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			msg.setContent(body, "text/html");
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Transport.send(msg);
						System.err.println("Email Password Sent Successfully...");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
