/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3:src/main/java/web/util/DateRangeUtil.p.vm.java
 */
package com.bizintelapps.zytoon.web.util;

import java.io.Serializable;
import java.util.Date;

import com.bizintelapps.zytoon.dao.support.DateRange;

/**
 * DateRange for {@link Date} that are used in {@link SearchForm} objects
 */
public class DateRangeUtil extends DateRange implements Serializable {
    static final private long serialVersionUID = 1L;

    /**
     * Constructs a new {@link DateRangeUtil} with no boundaries and no restrictions on date's nullability.
     * @param field the property's name of an existing entity.
     */
    public DateRangeUtil(String field) {
        super(field);
    }

    public Date getFromDate() {
        return (Date) getFrom();
    }

    public void setFromDate(Date from) {
        setFrom(from);
    }

    public Date getToDate() {
        return (Date) getTo();
    }

    public void setToDate(Date to) {
        setTo(to);
    }
}