package com.hms.indus.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.hms.indus.bo.EmailRecord;
import com.hms.indus.controller.ClientController;

public class SSLEmail {
	
	ClientController clientController = (ApplicationContextUtil.getApplicationContext())
			.getBean(ClientController.class);
	
	/*final String fromEmail = "testersorca@gmail.com";
    final String password = "orcasys123*";*/
	
	final String fromEmail = "connectmyindus@gmail.com";
    final String password = "indus@123";

	public void sendMail(String toEmail, String mailData, String mailSubject, EmailRecord emailRecord)
			throws MessagingException {

		toEmail = "kishor.lokhande@orcasys.co";//For testing
        System.out.println("TLSEmail Start");
        Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		//props.put("mail.smtp.from", "info@indushealthplus.com");
		props.put("java.net.preferIPv4Stack" , "true");
		//props.put("mail.smtp.localhost", "mail.indushealthplus.com");
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
		/*final String fromEmail = "ehralert@indushealthplus.com";
        final String password = "Alert&978";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.indushealthplus.com");
        props.put("mail.smtp.port", "25");*/
		
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		StringBuffer buffer=new StringBuffer();
		buffer.append("<html><body><a href="+mailData+">"+mailData+"</a></body></html>");
		
		try
		{
			EmailUtil.sendEmail(session, toEmail, mailSubject, mailData);
			
			//Save Email log on database
			clientController.saveEmail(emailRecord);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int sendMailWithAttach(String toEmail, String mailData, String mailSubject, String attachment, EmailRecord emailRecord)
			throws MessagingException {

		int result=0;
		toEmail = "kishor.lokhande@orcasys.co";//For testing
        System.err.println("TLSEmail Start");
        Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("java.net.preferIPv4Stack" , "true");
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);
		StringBuffer buffer=new StringBuffer();
		buffer.append("<html><body><a href="+mailData+">"+mailData+"</a></body></html>");
		try
		{
			EmailUtil.sendEmailWithAttach(session, toEmail, mailSubject, mailData, attachment);
			
			//Save Email log on database
			clientController.saveEmail(emailRecord);
			 result=1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			 result=0;
		}
		return result;
	}
	
	public int sendMailWithPdfPassword(String toEmail, String mailData, String mailSubject, EmailRecord emailRecord)
			throws MessagingException {
		int result=0;
		toEmail = "kishor.lokhande@orcasys.co";//For testing
        System.out.println("TLSEmail Start");
        Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("java.net.preferIPv4Stack" , "true");
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		StringBuffer buffer=new StringBuffer();
		buffer.append("<html><body><a href="+mailData+">"+mailData+"</a></body></html>");
		
		try
		{
			EmailUtil.sendMailWithPdfPassword(session, toEmail, mailSubject, mailData);
			
			//Save Email log on database
			clientController.saveEmail(emailRecord);
			result=1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result=0;
		}
		return result;
	}
	
}
