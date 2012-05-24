/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3:src/main/java/web/listener/SessionListener.p.vm.java
 */
package com.bizintelapps.zytoon.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * SessionListener class used to listen to session creation and destruction.
 * <p>
 * Please add in web.xml its definition to enable it.
 * <pre>
 *  &lt;listener&gt;
 *      &lt;listener-class&gt;com.bizintelapps.zytoon.web.listener.SessionListener&lt;/listener-class&gt;
 *  &lt;/listener&gt;
 * </pre>
 */
public class SessionListener implements HttpSessionListener {

    private Logger logger = Logger.getLogger(SessionListener.class);

    /**
     * called upon session creation
     */
    public void sessionCreated(HttpSessionEvent se) {
        if (logger.isInfoEnabled()) {
            HttpSession session = se.getSession();
            logger.info("sessionId=" + session.getId() + " maxInactiveInterval=" + session.getMaxInactiveInterval()
                    + " seconds");
        }
    }

    /**
     * called upon session deletion
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        if (logger.isInfoEnabled()) {
            HttpSession session = se.getSession();
            // session has been invalidated and all session data (except Id)is no longer available
            logger.info("sessionId=" + session.getId());
        }
    }
}
