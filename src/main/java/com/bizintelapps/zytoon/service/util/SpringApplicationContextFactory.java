/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.service.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author intesar
 * Simple bean class which implements ApplicationContextAware interface
 */
@Component
public class SpringApplicationContextFactory implements ApplicationContextAware {

    protected static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getContext() {
        return ctx;
    }
}
