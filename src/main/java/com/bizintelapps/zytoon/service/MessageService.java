package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.domain.Message;
import java.util.List;

/**
 *
 * @author Intesar Mohammed
 */
public interface MessageService {

    void send(String subject, String body,  String type, Integer parent, Integer senderId, Integer receiverId);

    void markRead(Integer messageId, Integer userBasicId);

    void markDelete(Integer messageId, Integer userBasicId);

    List<Message> getMessages(Integer userBasicId, int first, int max);
    
    Message get(Integer id);
    
    void save (Message message);
}
