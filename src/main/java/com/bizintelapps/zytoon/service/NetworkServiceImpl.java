package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.dao.UserNetworkDao;
import com.bizintelapps.zytoon.domain.Message;
import com.bizintelapps.zytoon.domain.UserBasic;
import com.bizintelapps.zytoon.domain.UserNetwork;
import com.bizintelapps.zytoon.domain.UserProfile;
import com.bizintelapps.zytoon.service.util.EmailTemplate;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Intesar Mohammed
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class NetworkServiceImpl implements NetworkService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected UserNetworkDao networkDao;
    @Autowired
    protected UsersService usersService;
    @Autowired
    protected EmailTemplate emailTemplate;

    @Override
    @Transactional
    public void requestJoinNetwork(String to, String type, String senderUsername) {

        if (to == null || to.equalsIgnoreCase(senderUsername) || type == null) {
            throw new RuntimeException("Invalid Email/Nickname");
            // we should ask user to invite this person -- TODO
        }

        // identify to - by email or nickname
        UserBasic ub = usersService.findUserBasicByUsername(to);

        UserBasic sender = usersService.findUserBasicByUsername(senderUsername);

        if (ub == null || sender == null) {
            throw new RuntimeException("Unable to find sender/receiver");
        }

        // add entry to users friends list
        UserNetwork un = null;
        un = this.networkDao.findByUserAndFriend(sender.getId(), ub.getId());

        if (un == null) {
            un = new UserNetwork();
            un.setActive(Boolean.FALSE);
            un.setFriend(ub);
            un.setUser(sender.getId());
        } else {
            throw new RuntimeException("Request pending!");
        }

        un.setType(type);
        networkDao.save(un);


        // send Message to "to"
        String body = "Assalamu alikum wa rahmatullah ! <br><br>"
                + "<p> " + sender.getName() +" would like to add you to his network on Zytoon.</p><br>"
                + "<p> <a href='http://www.zytoon.me/home/index#family' > View Request </a> ";
                //+ "<p> Note: " + sender.getName() + " is also copied on this email. </p>";
        
        messageService.send("Join my network on Zytoon", body, FRIEND_REQUEST, null, sender.getId(), ub.getId());
        emailTemplate.sendEmail(new String[] {to}, sender.getName() + " invited you to join his network on Zytoon", body);
        // send email alert if user doesn't sees this request in 2 days -- TODO 

    }

    /**
     * 
     * @param requestId
     * @param action 1 - accept, 2 - reject, 3 - remind in 3 days
     * @param userId 
     */
    @Override
    @Transactional
    public void requestAction(Integer requestId, Integer action, String type, String username) {

        if (requestId == null || action == null || type == null || username == null) {
            throw new RuntimeException("Invalid data");
        }

        Message m = this.messageService.get(requestId);
        m.setReceiverDeleted(Boolean.TRUE);
        messageService.save(m);

        // retreive entry from friends table
        UserNetwork un = networkDao.findByUserAndFriend(m.getSender().getId(), m.getReceiver().getId());

        UserBasic ub = usersService.findUserBasicByUsername(username);

        if (!ub.getId().equals(un.getFriend().getId())) {
            throw new RuntimeException("invalid access");
        }

        String body = "";
        // update accordingly
        if (action == 1) {
            un.setActive(Boolean.TRUE);
            networkDao.save(un);

            // add entry to users friends list
            UserNetwork un1 = null;
            un1 = this.networkDao.findByUserAndFriend(un.getFriend().getId(), un.getUser());

            if (un1 == null) {
                un1 = new UserNetwork();
                un1.setActive(Boolean.TRUE);
                un1.setFriend(m.getSender());
                un1.setUser(un.getFriend().getId());
                un1.setType(type);
                networkDao.save(un1);
            }

            body =  un.getFriend().getName() + " has accepted to join your network on Zytoon.";
            // Todo - Email
            String to = usersService.findByUserProfileId(m.getSender().getUserProfileId()).getUsername();
            emailTemplate.sendEmail(new String[] {to}, body, body + "<br>");
            
        } else if (action == 2) {
            //un.setActive(Boolean.TRUE);
            networkDao.delete(un);
            // do nothing
            body = "<p> " + un.getFriend().getName() + " has declined to join your network. </p> <br>";
            // Todo - Email
        } else if (action == 3) {
            // TODO
        }

        // send Message to requeter 
        messageService.send("Congratulations!", body, FRIEND_RESPONSE, null, un.getFriend().getId(), un.getUser());
    }

    @Override
    @Cacheable("myNetwork")
    public List<UserNetwork> myNetwork(String username) {
        return this.networkDao.findByUserBasicId(username);
    }

    @Override
    @Transactional
    public void sendInvite(String toEmail, String fromEmail) {
        if (toEmail == null || toEmail.length() < 4
                || fromEmail == null || fromEmail.length() < 4) {
            throw new RuntimeException("Invalid data");
        }
        
        UserProfile uf1 = null;
        try {
            uf1 = usersService.findByUsername(toEmail);
        } catch (RuntimeException re) {
        }
        if (uf1 != null && uf1.isIdSet()) {
            throw new RuntimeException("Invalid data");
        }


        // how do we stop user from sending multiple requests ?
        // Todo

        // forward this request to EmailTemplate
        UserProfile uf = usersService.findByUsername(fromEmail);
        this.emailTemplate.sendInvite(toEmail, fromEmail, uf.getFirstName());
    }

    @Override
    public List<Message> networkingRequests(String username, int first, int max) {
        UserBasic ub = usersService.findUserBasicByUsername(username);
        return messageService.getMessages(ub.getId(), first, max);
    }
}
