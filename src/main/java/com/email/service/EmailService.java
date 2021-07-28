package com.email.service;



import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


@Service
public class EmailService
{
    Logger logger= (Logger) LoggerFactory.getLogger(EmailService.class);
    @Value("${userName}")
    private String userName;
    @Value("${password}")
    private String password;
    @Value("${host}")
    private String host;
    @Value("${port}")
    private String port;
    public boolean sendAttach(String subject,String message,String to, MultipartFile images) throws Exception
    {
        boolean f=false;
        String from=userName;
        System.out.println(userName);
        System.out.println(password);
       // String host="smtp.gmail.com";
        String UPLOADED_FOLDER = "G://temp//";
        Properties properties=System.getProperties();
        System.out.println("PROPERTIES"+properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",port);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        });

        session.setDebug(true);
        MimeMessage mimeMessage=new MimeMessage(session);

            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);



            byte[] bytes = images.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + images.getOriginalFilename());
            Files.write(path, bytes);



           // long bytes= (long) Files.size(Paths.get(path));
            System.out.println(bytes);
            MimeMultipart mimeMultipart=new MimeMultipart();
            MimeBodyPart textMime=new MimeBodyPart();
            MimeBodyPart fileMime=new MimeBodyPart();

                textMime.setText(message);
                File file=new File(path.toUri());

                
                fileMime.attachFile(file);
                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);





                logger.trace("Error happened");

            mimeMessage.setContent(mimeMultipart);

            Transport.send(mimeMessage);
            System.out.println("Sent successfully................");
            f=true;
             logger.error("Fatal error");
             logger.trace("Every thing trace");

        return f;
    }

    public boolean sendEmail(String subject,String message,String to)throws Exception
    {
        boolean f=false;
        String from="Raghvanmishra9519@gmail.com";
      String host="smtp.gmail.com";

        Properties properties=System.getProperties();
        System.out.println("PROPERTIES"+properties);



        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Raghvanmishra9519@gmail.com","Raghvan@95");
            }
        });

        session.setDebug(true);
        MimeMessage mimeMessage=new MimeMessage(session);

            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);

            mimeMessage.setText(message);
            Transport.send(mimeMessage);


            System.out.println("Sent successfully................");
            f=true;

        logger.trace("Error happened");
        return f;
    }
}
