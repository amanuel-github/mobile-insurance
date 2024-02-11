package com.medcoanalytics.lemobileyeservice.shared.email;


import com.medcoanalytics.lemobileyeservice.payload.request.users.SendEmailRequest;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class SendEmail {
	@Value("${mail.Username}")
    private String username;
	
	@Value("${mail.Password}")
    private String password;
	
	@Value("${mail.HostDomain}") private String hostDomain;
	 
	
	public String sendMail(SendEmailRequest sendMail) throws AddressException, MessagingException, IOException {
		
		String returnValue = "";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", hostDomain);
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, 
		 new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username, false));
			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendMail.getToAddress()));
			msg.setSubject(sendMail.getSubject());
			msg.setContent(sendMail.getBody(),"text/html");
			msg.setSentDate(new Date());
			
			MimeBodyPart messaBodyPart = new MimeBodyPart();
			messaBodyPart.setContent(sendMail.getBody(), "text/html");
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(messaBodyPart);
			MimeBodyPart attachment = new MimeBodyPart();
			
			
			/*TO SEND ATTACHEMENT
			 * 
			 * attachment.attachFile("C:\\users\\logo.jpg");
			 * multipart.addBodyPart(attachment); msg.setContent(multipart);
			 */
			
			// Send the email			
			Transport.send(msg);
			returnValue = "Mail Sent";
			
		}catch (MessagingException  e) {
			returnValue = "Mail Not Sent";
		}
		
		return returnValue;
	}
}
