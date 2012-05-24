package com.bizintelapps.zytoon.service.util;

/**
 *
 * @author intesar
 */
public interface AccessCodeCache {

    void add(String email, String accessCode);

    boolean isValid(String email, String accessCode);
    
}
