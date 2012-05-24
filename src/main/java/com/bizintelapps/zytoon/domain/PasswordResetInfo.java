/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author intesar
 */
public class PasswordResetInfo implements Serializable{

    private String email;
    private String accessCode;
    private Date requestedTime;
    private int attempts;

    public PasswordResetInfo(String email, String accessCode, Date requestedTime, int attempts) {
        this.email = email;
        this.accessCode = accessCode;
        this.requestedTime = requestedTime;
        this.attempts = attempts;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PasswordResetInfo other = (PasswordResetInfo) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PasswordResetInfo{" + "email=" + email + ", accessCode=" + accessCode + ", requestedTime=" + requestedTime + ", attempts=" + attempts + '}';
    }
    
    
}
