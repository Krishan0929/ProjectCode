package com.email.service;


import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService
{
    public boolean sendAttach(String subject,String message,String to)
    {
        boolean f=false;
        String from="Raghvanmishra@gmail.com";
        String host="smtp.gmail.com";

        Properties properties=System.getProperties();
        System.out.println("PROPERTIES"+properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Raghvanmishra9519@gmail.com","Raghvan@95");
            }
        });

        session.setDebug(true);
        MimeMessage mimeMessage=new MimeMessage(session);
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);
            String path="C:\\Users\\kandhway\\Desktop\\aa.PNG";
            MimeMultipart mimeMultipart=new MimeMultipart();
            MimeBodyPart textMime=new MimeBodyPart();
            MimeBodyPart fileMime=new MimeBodyPart();
            try{
                textMime.setText(message);
                File file=new File(path);
                fileMime.attachFile(file);
                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            mimeMessage.setContent(mimeMultipart);

            Transport.send(mimeMessage);
            System.out.println("Sent successfully................");
            f=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    public boolean sendEmail(String subject,String message,String to)
    {
        boolean f=false;
        String from="Raghvanmishra@gmail.com";
        String host="smtp.gmail.com";

        Properties properties=System.getProperties();
        System.out.println("PROPERTIES"+properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Raghvanmishra9519@gmail.com","Raghvan@95");
            }
        });

        session.setDebug(true);
        MimeMessage mimeMessage=new MimeMessage(session);
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);
            /*String path="";
            MimeMultipart mimeMultipart=new MimeMultipart();
            MimeBodyPart textMime=new MimeBodyPart();
            MimeBodyPart fileMime=new MimeBodyPart();
            try{
                textMime.setText(message);
                File file=new File(path);
                fileMime.attachFile(file);
                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);

            }
            catch (Exception e)
            {
            e.printStackTrace();
            }
            mimeMessage.setContent(mimeMultipart);*/
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            System.out.println("Sent successfully................");
            f=true;
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return f;
    }
}
