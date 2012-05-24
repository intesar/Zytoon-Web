/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3:src/main/java/web/util/AutoCompleteResult.p.vm.java
 */
package com.bizintelapps.zytoon.web.util;

/**
 * Simple class that holds a primary key and a label for autocomplete
 */
public class AutoCompleteResult {
    private String id;
    private String label;

    public AutoCompleteResult(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}