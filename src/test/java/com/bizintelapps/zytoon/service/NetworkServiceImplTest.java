/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class NetworkServiceImplTest {

    @Autowired
    protected NetworkService instance;
    @Autowired
    protected MessageService ms;
    @Autowired
    protected UsersService us;
    String to = "intesar@ymail.com";
    String type = "family";
    String senderUsername = "mdshannan@gmail.com";

    public NetworkServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of requestJoinNetwork method, of class NetworkServiceImpl.
     */
    @Test
    public void testRequestJoinNetwork1() {
        System.out.println("requestJoinNetwork");
        try {
            instance.requestJoinNetwork(to, type, to);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestJoinNetwork2() {
        System.out.println("requestJoinNetwork");
        try {
            instance.requestJoinNetwork(to, type, null);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestJoinNetwork3() {
        System.out.println("requestJoinNetwork");
        try {
            instance.requestJoinNetwork(null, type, to);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestJoinNetwork4() {
        System.out.println("requestJoinNetwork");
        try {
            instance.requestJoinNetwork(to, null, to);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestJoinNetwork5() {
        System.out.println("requestJoinNetwork");
        try {
            instance.requestJoinNetwork(to, type, senderUsername);
            instance.requestJoinNetwork(to, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestJoinNetwork() {
        System.out.println("requestJoinNetwork");
        instance.requestJoinNetwork(to, type, senderUsername);
    }

    /**
     * Test of requestAction method, of class NetworkServiceImpl.
     */
    @Test
    public void testRequestAction1() {
        System.out.println("requestAction");
        Integer requestId = null;
        Integer action = 1;
        try {
            instance.requestAction(requestId, action, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestAction2() {
        System.out.println("requestAction");
        Integer requestId = 0;
        Integer action = 1;
        try {
            instance.requestAction(requestId, action, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestAction3() {
        System.out.println("requestAction");
        Integer requestId = 1;
        Integer action = 0;
        try {
            instance.requestAction(requestId, action, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestAction4() {
        System.out.println("requestAction");
        Integer requestId = 1;
        Integer action = 3;
        try {
            instance.requestAction(requestId, action, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestAction5() {
        System.out.println("requestAction");
        Integer requestId = -1;
        Integer action = 1;
        try {
            instance.requestAction(requestId, action, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestAction7() {
        System.out.println("requestAction");
        Integer requestId = 1;
        Integer action = 1;
        try {
            instance.requestAction(requestId, action, type, senderUsername);
            instance.requestAction(requestId, action, type, senderUsername);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void testRequestAction() {
        System.out.println("requestAction");
        Integer action = 1;
        instance.requestJoinNetwork(to, type, senderUsername);
        List<Message> list = ms.getMessages(us.findUserBasicByUsername(to).getId(), 0, 10);
        instance.requestAction(list.get(0).getId(), action, type, to);
    }

    /**
     * Test of myNetwork method, of class NetworkServiceImpl.
     */
    @Test
    public void testMyNetwork() {
        System.out.println("myNetwork");
        //instance.requestJoinNetwork(to, type, senderUsername);
        List<Message> list = ms.getMessages(us.findUserBasicByUsername(to).getId(), 0, 10);
        assertTrue(list.isEmpty() );
    }
    
    @Test
    public void testMyNetwork1() {
        System.out.println("myNetwork");
        instance.requestJoinNetwork(to, type, senderUsername);
        List<Message> list = ms.getMessages(us.findUserBasicByUsername(to).getId(), 0, 10);
        assertTrue(list.size() > 0 );
    }


    /**
     * Test of sendInvite method, of class NetworkServiceImpl.
     */
    
    @Test
    public void testSendInvite1() {
        System.out.println("sendInvite");
        String toEmail = null;
        String fromEmail = senderUsername;
        try {
            instance.sendInvite(toEmail, fromEmail);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }
    
    @Test
    public void testSendInvite2() {
        System.out.println("sendInvite");
        String toEmail = "";
        String fromEmail = senderUsername;
        try {
            instance.sendInvite(toEmail, fromEmail);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }
    
    @Test
    public void testSendInvite3() {
        System.out.println("sendInvite");
        String toEmail = to;
        String fromEmail = null;
        try {
            instance.sendInvite(toEmail, fromEmail);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }
    @Test
    public void testSendInvite4() {
        System.out.println("sendInvite");
        String toEmail = to;
        String fromEmail = "";
        try {
            instance.sendInvite(toEmail, fromEmail);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }
    
    @Test
    public void testSendInvite5() {
        System.out.println("sendInvite");
        String toEmail = to;
        String fromEmail = senderUsername;
        try {
            instance.sendInvite(toEmail, fromEmail);
            fail("should be an exception");
        } catch (RuntimeException re) {
        }
    }
    
    @Test
    public void testSendInvite6() {
        System.out.println("sendInvite");
        String toEmail = "intesar.mohammed@gmail.com";
        String fromEmail = senderUsername;
        try {
            instance.sendInvite(toEmail, fromEmail);
            
        } catch (RuntimeException re) {
            fail("should be an exception");
        }
    }

    /**
     * Test of networkingRequests method, of class NetworkServiceImpl.
     */
    @Test
    public void testNetworkingRequests() {
        System.out.println("networkingRequests");
        String username = to;
        int first = 0;
        int max = 10;
        List result = instance.networkingRequests(username, first, max);
        assertTrue(result.isEmpty());
    }
    
     
    @Test
    public void testNetworkingRequests1() {
        System.out.println("networkingRequests");
        Integer action = 1;
        instance.requestJoinNetwork(to, type, senderUsername);
        List<Message> list = ms.getMessages(us.findUserBasicByUsername(to).getId(), 0, 10);
        instance.requestAction(list.get(0).getId(), action, type, to);
        
        String username = to;
        int first = 0;
        int max = 10;
        List result = instance.networkingRequests(username, first, max);
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void testNetworkingRequests2() {
        System.out.println("networkingRequests");
        Integer action = 1;
        instance.requestJoinNetwork(to, type, senderUsername);
        List<Message> list = ms.getMessages(us.findUserBasicByUsername(to).getId(), 0, 10);
        instance.requestAction(list.get(0).getId(), action, type, to);
        
        String username = senderUsername;
        int first = 0;
        int max = 10;
        List result = instance.networkingRequests(username, first, max);
        assertTrue(result.size() > 0);
    }
    
}
