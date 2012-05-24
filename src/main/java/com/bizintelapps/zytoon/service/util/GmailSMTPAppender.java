package com.bizintelapps.zytoon.service.util;

import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;

public class GmailSMTPAppender extends SMTPAppender {

    protected Session session;
    private boolean startTLS = false;

    //protected static final EmailService emailService = (EmailService) SpringApplicationContextFactory.getContext().getBean(EmailService.class);
    public GmailSMTPAppender() {
        super();
    }

    public EmailService getEmailService() {

        if (SpringApplicationContextFactory.getContext() != null) {
            EmailService emailService = (EmailService) SpringApplicationContextFactory.getContext().getBean(EmailService.class);
            if (emailService != null) {
                return emailService;
            }
        }
        return new EmailServiceImpl();
    }

    /**
     * Create mail session.
     * 
     * @return mail session, may not be null.
     */
    @Override
    protected Session createSession() {
        System.out.println("createSession()");
        return getEmailService().createSession();
    }

    /**
     * Send the contents of the cyclic buffer as an e-mail message.
     */
    @Override
    protected void sendBuffer() {
        System.out.println("sendBuffer()");
        try {
            MimeBodyPart part = new MimeBodyPart();

            StringBuilder sbuf = new StringBuilder();
            String t = layout.getHeader();
            if (t != null) {
                sbuf.append(t);
            }
            int len = cb.length();
            for (int i = 0; i < len; i++) {
                LoggingEvent event = cb.get();
                sbuf.append(layout.format(event));
                if (layout.ignoresThrowable()) {
                    String[] s = event.getThrowableStrRep();
                    if (s != null) {
                        for (int j = 0; j < s.length; j++) {
                            sbuf.append(s[j]);
                            sbuf.append(Layout.LINE_SEP);
                        }
                    }
                }
            }
            t = layout.getFooter();
            if (t != null) {
                sbuf.append(t);
            }
            String[] to = {"mdshannan@gmail.com", "atefahmed@gmail.com"};
            getEmailService().sendEmail(to, "Zytoone - Error log ", sbuf.toString());

        } catch (Exception e) {
            LogLog.error("Error occured while sending e-mail notification.", e);
        }
    }

    public boolean isStartTLS() {
        return startTLS;
    }

    public void setStartTLS(boolean startTLS) {
        this.startTLS = startTLS;
    }
    
}