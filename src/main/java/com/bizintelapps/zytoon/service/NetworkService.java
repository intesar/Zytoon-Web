/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.domain.Message;
import com.bizintelapps.zytoon.domain.UserNetwork;
import java.util.List;

/**
 *
 * @author Intesar Mohammed
 */
public interface NetworkService {
    
    String FRIEND_REQUEST = "Friend request";
    String FRIEND_RESPONSE = "Friend response";

    void requestAction(Integer requestId, Integer action, String type, String username);

    void requestJoinNetwork(String to, String type, String senderUsername);
    
    List<UserNetwork> myNetwork(String username);
    
    void sendInvite(String toEmail, String fromEmail);
    
    List<Message> networkingRequests(String username, int first, int max);
    
}
