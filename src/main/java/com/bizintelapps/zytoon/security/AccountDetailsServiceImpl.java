/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/main/java/project/security/AccountDetailsServiceImpl-spring3.p.vm.java
 */
package com.bizintelapps.zytoon.security;

import java.util.Collection;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizintelapps.zytoon.dao.support.SearchTemplate;
import com.bizintelapps.zytoon.dao.support.SearchMode;
import com.bizintelapps.zytoon.domain.Users;
import com.bizintelapps.zytoon.service.UsersService;

/**
 * An implementation of Spring Security's UserDetailsService.
 */
@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(AccountDetailsServiceImpl.class);

    private UsersService usersService;

    public AccountDetailsServiceImpl() {
    }

    @Autowired
    public AccountDetailsServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Retrieve an account depending on its login this method is not case sensitive.<br>
     * use <code>obtainAccount</code> to match the login to either email, login or whatever is your login logic
     *
     * @param login the account login
     * @return a Spring Security userdetails object that matches the login
     * @see #obtainAccount(String)
     * @throws UsernameNotFoundException when the user could not be found
     * @throws DataAccessException when an error occured while retrieving the account
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
        if (login == null || login.trim().isEmpty()) {
            throw new UsernameNotFoundException("Empty login");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Security verification for user '" + login + "'");
        }

        Users account = obtainAccount(login);

        if (account == null) {
            if (logger.isInfoEnabled()) {
                logger.info("Account " + login + " could not be found");
            }
            throw new UsernameNotFoundException("account " + login + " could not be found");
        }

        Collection<GrantedAuthority> grantedAuthorities = obtainGrantedAuthorities(login);

        if (grantedAuthorities == null) {
            grantedAuthorities = SpringSecurityContext.toGrantedAuthorities(account.getRoleNames());
        }

        String password = obtainPassword(login);

        if (password == null) {
            password = account.getPassword();
        }

        boolean enabled = account.getEnabled();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(login, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }

    /**
     * Return the account depending on the login provided by spring security.
     * @return the users if found
     */
    protected Users obtainAccount(String login) {
        Users account = new Users();
        account.setUsername(login);

        SearchTemplate searchTemplate = new SearchTemplate();
        searchTemplate.setSearchMode(SearchMode.EQUALS);
        searchTemplate.setCaseSensitive(false);

        return usersService.getUsersByUsername(login);
    }

    /**
     * Returns null. Subclass may override it to provide their own granted authorities.
     */
    protected Collection<GrantedAuthority> obtainGrantedAuthorities(String username) {
        return null;
    }

    /**
     * Returns null. Subclass may override it to provide their own password.
     */
    protected String obtainPassword(String username) {
        return null;
    }
}