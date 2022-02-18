package model;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public String getRandom()
	{
		Random rand = new Random();
		int number = rand.nextInt(999999);
		return String.format("%06d", number);
	}
	
	public boolean sendEmail(User user)
	{
		boolean test = false;
		
		String toEmail = user.getEmail();
		String fromEmail = "khushisingla2899@gmail.com";
		String password = "2830@412Ks";
		
		try
		{
			Properties props = new Properties();    
	        props.put("mail.smtp.host", "smtp.gmail.com");    
	        props.put("mail.smtp.socketFactory.port", "465");    
	        props.put("mail.smtp.socketFactory.class",    
	                    "javax.net.ssl.SSLSocketFactory");    
	        props.put("mail.smtp.auth", "true");    
	        props.put("mail.smtp.port", "465"); 
			
	        Session session = Session.getDefaultInstance(props,    
	                new javax.mail.Authenticator() {    
	                protected PasswordAuthentication getPasswordAuthentication() {    
	                return new PasswordAuthentication(fromEmail,password);  
	                }    
	               });  
			
			Message mess = new MimeMessage(session);
			
			mess.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mess.setSubject("User Email Verification");
			mess.setText("Registered successfully. Kindly verify your account using this code: " + user.getCode());
			Transport.send(mess);
			
			test = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return test;
	}

}
