package com.cameramonitor.server;
import java.io.File;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class Alert {
    public static void sendEmail(IniReader config) {
    	//Define
    	String username = config.getValue("username");
    	String password = config.getValue("password");
    	File alertFolder = new File(config.getValue("alertFolder"));
		String host = config.getValue("host");
		String starttls = config.getValue("starttls");
		String port = config.getValue("smtpport");	
		String recipient1 = config.getValue("recipient1");
		String recipient2 = config.getValue("recipient2");
    	File[] files = alertFolder.listFiles();
		String subject = "";
		Properties props = System.getProperties();

		
		//Populate Properties
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
   

		//New Email Session
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
        	
        	//Add From and To
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress (recipient1));
            
            if (!(recipient2.isEmpty())) { 
            	message.addRecipient(Message.RecipientType.BCC, new InternetAddress (recipient2));
            }
            
            
            //Adding Body to Email
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Alert: " + java.util.Calendar.getInstance().getTime());
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
           
         
            
        	for (File file : files)
            {
        		messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(file.getName());
                multipart.addBodyPart(messageBodyPart);
                System.out.println("Sending: "  + file.getAbsolutePath());
               
            }
        

            message.setContent(multipart);
            message.setSubject(subject);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Message Sent..");
            transport.close();
      
        }catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        
    }
    
    }