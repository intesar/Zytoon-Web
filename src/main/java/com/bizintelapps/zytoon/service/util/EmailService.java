package com.bizintelapps.zytoon.service.util;

import javax.mail.Session;

/**
 *
 * @author intesar
 */
public interface EmailService {

    String SMTP_HOST_NAME = "smtp.gmail.com";
    String SMTP_PORT = "465";
    String EMAIL_FROM_ADDRESS = "team@zytoon.me";
    String SEND_FROM_USERNAME = "team@zytoon.me";
    String SEND_FROM_PASSWORD = "Teamu123$";
    String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    String EMAIL_CONTENT_TYPE = "text/html";
    String EMAIL_SIGNATURE = "<br/>"
            + "Thanks, <br/>"
            + "Zytoon.me Team";

    /**
     * 
     * @param toAddress
     * @param car
     * @param comment
     */
    void sendEmail(String toAddress, String subject, String body);

    void sendEmail(String[] to, String subject, String body);

    void sendWelcomeEmail(String[] toAddress, String subject, String message);
    
    Session createSession();
}
