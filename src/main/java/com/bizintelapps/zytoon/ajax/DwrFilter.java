package com.bizintelapps.zytoon.ajax;

import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

/**
 *
 * @author intesar
 *
 * Filter for checking user has session
 */
public class DwrFilter implements AjaxFilter {

    @Override
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
        try {
            Object object = chain.doFilter(obj, method, params);
            return object;

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
    protected static final Log logger = LogFactory.getLog(DwrFilter.class);
}
