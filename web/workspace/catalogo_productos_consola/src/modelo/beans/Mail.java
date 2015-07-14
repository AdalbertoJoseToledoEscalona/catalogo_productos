/**
 * 
 */
package modelo.beans;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author adalberto
 *
 */
public class Mail {
	private final String username = "adalberto.toledo@gmail.com";
	private final String password = "nuevo.2816";
	
	private boolean auth = true;
	private boolean starttls = true;
	private String host = "smtp.gmail.com";
	private int port = 587/* TLS */; //465 SSL
	private boolean useHTML = true;
	
	private String from = "adalberto.toledo@gmail.com";
	private String to = "";
	
	private String subject = "";
	private String body = "";
	/**
	 * 
	 */
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public void setFrom(String from) {
		this.from = from;
	}




	public void setTo(String to) {
		this.to = to;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}


	public void setBody(String body) {
		this.body = body;
	}



	public void sendMail(boolean usarTLS) throws AddressException, MessagingException{
		if(usarTLS){
			sendMailTLS();
		}else{
			sendMailSSL();
		}
	}
	
	private void sendMailTLS() throws AddressException, MessagingException{
		//final String username = "username@gmail.com";
		//final String password = "password";
 
		Properties props = new Properties();
		//props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.auth", Boolean.toString(auth));
		//props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.enable", Boolean.toString(starttls));
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", host);
		//props.put("mail.smtp.port", "587");
		props.put("mail.smtp.port", Integer.toString(port));
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		//try {
 
			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
				//InternetAddress.parse("to-email@gmail.com"));
					InternetAddress.parse(to));

			//message.setSubject("Testing Subject");
			message.setSubject(subject);
			
			if(useHTML){
				System.out.println("USO HTML");
				message.setContent(body, "text/html; charset=utf-8");
			}else{
				//message.setText("Dear Mail Crawler," +
				//		"\n\n No spam to my email, please!");
				message.setText(body);
			}
 
			Transport.send(message);
 
			System.out.println("Done");
 
		/*} catch (MessagingException e) {
			throw new RuntimeException(e);
		}*/
	}
	
	private void sendMailSSL() throws AddressException, MessagingException{
		Properties props = new Properties();
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", host);
		//props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.port", Integer.toString(port));
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		//props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.auth", Boolean.toString(auth));
		//props.put("mail.smtp.port", "465");
		props.put("mail.smtp.port", Integer.toString(port));
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					//return new PasswordAuthentication("username","password");
					return new PasswordAuthentication(username,password);
				}
			});
 
		//try {
 
			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					//InternetAddress.parse("to@no-spam.com"));
					InternetAddress.parse(to));
			
			//message.setSubject("Testing Subject");
			message.setSubject(subject);
			
			if(useHTML){
				System.out.println("USO HTML");
				message.setContent(body, "text/html; charset=utf-8");
			}else{
				//message.setText("Dear Mail Crawler," +
				//		"\n\n No spam to my email, please!");
				message.setText(body);
			}
 
			Transport.send(message);
 
			System.out.println("Done");
 
		/*} catch (MessagingException e) {
			throw new RuntimeException(e);
		}*/
	}

}
