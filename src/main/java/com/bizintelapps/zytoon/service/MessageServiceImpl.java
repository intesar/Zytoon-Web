package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.dao.MessageDao;
import com.bizintelapps.zytoon.dao.UserBasicDao;
import com.bizintelapps.zytoon.domain.Message;
import com.bizintelapps.zytoon.domain.UserBasic;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Intesar Mohammed
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
    @Autowired
    protected MessageDao messageDao;
    @Autowired
    protected UserBasicDao userBasicDao;

    @Override
    @Transactional
    public void send(String subject, String body, String type, Integer parent, Integer senderId, Integer receiverId) {

        Message message = new Message();

        message.setActive(Boolean.TRUE);
        message.setSubject(subject);
        message.setBody(body);
        message.setParent(parent);

        UserBasic receiver = new UserBasic();
        receiver.setId(receiverId);
        receiver = this.userBasicDao.get(receiver);
        message.setReceiver(receiver);

        UserBasic sender = new UserBasic();
        sender.setId(senderId);
        sender = this.userBasicDao.get(sender);
        message.setSender(sender);

        message.setSendDate(new Date());
        message.setType(type);

        messageDao.save(message);

    }

    @Override
    @Transactional
    public void markRead(Integer messageId, Integer userBasicId) {
        Message message = new Message();
        message.setId(messageId);
        message = messageDao.get(message);
        if (!message.getReceiver().getId().equals(userBasicId)) {
            throw new RuntimeException("Unauthorized access " + userBasicId + " trying to access " + messageId + " message Id ");
        }
        message.setReceiverRead(Boolean.TRUE);
        this.messageDao.save(message);
    }

    @Override
    @Transactional
    public void markDelete(Integer messageId, Integer userBasicId) {
        Message message = new Message();
        message.setId(messageId);
        message = messageDao.get(message);
        if (!message.getReceiver().getId().equals(userBasicId)) {
            throw new RuntimeException("Unauthorized access " + userBasicId + " trying to access " + messageId + " message Id ");
        }
        message.setReceiverDeleted(Boolean.TRUE);
        this.messageDao.save(message);
    }

    @Override
    public List<Message> getMessages(Integer userBasicId, int first, int max) {
        return messageDao.findUnread(userBasicId, first, max);
    }

    @Override
    public Message get(Integer id) {
        Message m = new Message(id);
        try {
            m = messageDao.get(m);
        } catch (RuntimeException re) {}
        return m;
    }

    @Override
    @Transactional
    public void save(Message message) {
        messageDao.save(message);
    }

    
}
