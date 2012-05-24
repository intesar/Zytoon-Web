package com.bizintelapps.zytoon.service.util;

import com.bizintelapps.zytoon.domain.UserEnrollment;

/**
 *
 * @author intesar
 */
public interface EmailTemplate {
    
    String DEFAULT_PURPOSE = "This program encourages you to improve your Salaah on time.";
    String BASIC_SALAAH = "This program encourages you to perform your Salaah on time.";
    String INTERMEDIATE_SALAAH ="This program encourages you perform Salaah on time and do Dhikr.";
    String ISHA_MASJID = "This program encourages you to perform Isha in the masjid, pray on time and do Dhikr.";
    String FAJR_MASJID = "This program encourages you to perform Fajr in the masjid, pray on time and do Dhikr.";
    String ALL_MASJID = "This program encourages you to perform all Salaah in the masjid and do Dhikr.";
    
 
    void sendCertificate(UserEnrollment ue);
    
    void sendFailure(UserEnrollment ue);

    void sendWelcomeEmail(String username, String firstName);

    void sendEmail(String email, String subject, String body);
    
    void sendEmail(String[] email, String subject, String body);

    void sendReminder(String username, String subject, String firstName);

    void sendAccessCode(String email, String accessCode);

    void sendEnrollment(UserEnrollment username);
    
    void sendInvite(String to, String from, String name);

    public void sendWelcomeEmailForFB(String username, String firstName);
}
