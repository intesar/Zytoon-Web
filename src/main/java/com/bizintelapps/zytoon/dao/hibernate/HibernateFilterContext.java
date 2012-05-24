/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/main/java/project/hibernate/support/HibernateFilterContext.p.vm.java
 */
package com.bizintelapps.zytoon.dao.hibernate;

import org.hibernate.Session;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import com.bizintelapps.zytoon.domain.Users;
import com.bizintelapps.zytoon.context.AccountContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Handle hibernate filters that is applying specific sql clauses to marked classes with &lt;filter name="XX"&gt;. <br>
 * Hibernate filters can be used for
 * <ul>
 * <li>security filtering, if you specify a filter such as <code>username = (:currentAccountId)</code></li>
 * <li>database views feature like, if you specify generic restriction clausis such as <code>category_id = 4 and producer_id in (2, 3) </code></li>
 * </ul>
 * Hibernate filters can be activated or disabled at will.
 */
@Service
public class HibernateFilterContext {
    private static final Logger logger = Logger.getLogger(HibernateFilterContext.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * enable hibernate filters
     */
    public void enableFilters() {
        enableFilter("myAuthoritiesFilter");
        enableFilter("myUsersFilter");
    }

    /**
     * disable all filters
     */
    public void disableFilters() {
        disableFilter("myAuthoritiesFilter");
        disableFilter("myUsersFilter");
    }

    /**
     * enable the given filter
     */
    public void enableFilter(String filtername) {
        if (AccountContext.getAccountContext().hasRole("ROLE_ADMIN")) {
            return;
        }

        String currentAccountId = getCurrentAccountId();
        if (logger.isDebugEnabled()) {
            logger.debug("filter " + filtername + " is enabled with currentAccountId = " + currentAccountId);
        }

        getCurrentSession().enableFilter(filtername).setParameter("currentAccountId", currentAccountId);
    }

    /**
     * get the current account id of the connected user, -1 if not connected
     */
    private String getCurrentAccountId() {
        String currentAccountId = "-1";
        Users account = AccountContext.getAccountContext().getAccount();
        if (account != null) {
            currentAccountId = account.getUsername();
        }
        return currentAccountId;
    }

    /**
     * disable the given filter
     */
    public void disableFilter(String filtername) {
        if (getCurrentSession().isOpen()) {
            if (isFilterEnabled(filtername)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("disabling filter " + filtername);
                }
                getCurrentSession().disableFilter(filtername);
            }
        } else {
            logger.warn("Attempt to disable filter " + filtername + " on closed session.");
        }
    }

    /**
     * Is the given filter currently enabled for the given session ?
     * @param filtername
     */
    public boolean isFilterEnabled(String filtername) {
        return getCurrentSession().getEnabledFilter(filtername) != null;
    }

    private Session getCurrentSession() {
        if (entityManager == null) {
            logger.error("entityManager is not initialized");
            throw new RuntimeException("entityManager is not initialized");
        }
        return (Session) entityManager.getDelegate();
    }
}
