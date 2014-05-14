package com.awesomesoft.tzt.service;

/**
 * Created by student on 5/13/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class SSLMailer {

    private static final Logger logger = LoggerFactory.getLogger(SSLMailer.class);

    public static void send(String recipient, String subject, String body) {

        logger.info("New email");
        logger.info("   {}", recipient);
        logger.info("   {}", subject);
        logger.info("   {}", body);

        // todo add header and footer to body using Person.getFirstname()

        final String username = "tztmailer@gmail.com";//todo fill in email account
        final String password = "masterkeytotzt";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );

        logger.info("Sending...");

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "ISC Library"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            logger.info("Email send to {}", recipient);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}