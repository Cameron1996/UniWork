//Created by Cameron for the Budgee application 04/2017
//Must ensure that the correct jars are in and installed, also currently not working on my pc at least, this could be down to my wifi however
//Example used for reference found at : https://www.tutorialspoint.com/java/java_sending_email.htm
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email 
{
	

	
	public static void main(String[] args) 
	{
		String emailRecipient = "smityboy100@gmail.com";//testing 
		String BudgeesEmailAddress = "Cameron_smith1996@outlook.com"; // Testing 
		String host = "localHost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
	    Session session = Session.getDefaultInstance(properties);

		try{
			MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(BudgeesEmailAddress));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
	        message.setSubject("This is the Subject Line!");
	        message.setText("This is actual message");
	        Transport.send(message);
	        System.out.println("Sent message successfully....");
		}
		catch(MessagingException errorInEmailSending){
			errorInEmailSending.printStackTrace();

		}
	}

	
}
