/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3:src/main/java/formatter/support/CustomFormattingConversionServiceFactory.p.vm.java
 */
package com.bizintelapps.zytoon.formatter.support;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;

import com.bizintelapps.zytoon.dao.support.OrderByDirection;
import com.bizintelapps.zytoon.dao.support.SearchMode;
import com.bizintelapps.zytoon.web.util.NullRestriction.NullRestrictionKind;

/**
 * This class gather all the {@link Converter}, {@link DiscoverableFormatter} and {@link AnnotationFormatterFactory} and add them in the
 * {@link FormattingConversionService}
 * 
 * @see Converter
 * @see DiscoverableFormatter
 * @see AnnotationFormatterFactory
 * @see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/validation.html#format-FormatterRegistry-SPI
 */
@Service("customFormattingConversionService")
public class CustomFormattingConversionServiceFactory extends FormattingConversionServiceFactoryBean {
    private static final Logger logger = Logger.getLogger(CustomFormattingConversionServiceFactory.class);

    @Autowired
    private List<Converter<?, ?>> converters;

    @Autowired
    private List<DiscoverableFormatter<?>> formatters;

    @Autowired
    private List<AnnotationFormatterFactory<?>> annotationsFormatters;

    @Autowired
    private MessageSource messageSource;

    protected void installFormatters(FormatterRegistry registry) {
        super.installFormatters(registry);
        addConverters(registry);
        addAnnotationFormatters(registry);
        addFormatters(registry);
        addEnumFormatters(registry, //
                NullRestrictionKind.class, //
                OrderByDirection.class, //
                SearchMode.class);

    }

    private void addConverters(FormatterRegistry registry) {
        for (Converter<?, ?> converter : converters) {
            if (logger.isDebugEnabled()) {
                logger.debug("Registering converter " + converter.toString());
            }
            registry.addConverter(converter);
        }
    }

    private void addFormatters(FormatterRegistry registry) {
        for (DiscoverableFormatter<?> formatter : formatters) {
            if (logger.isDebugEnabled()) {
                logger.debug("Registering formatter " + formatter.getTarget().getSimpleName());
            }
            registry.addFormatterForFieldType(formatter.getTarget(), formatter);
        }
    }

    private void addAnnotationFormatters(FormatterRegistry registry) {
        for (AnnotationFormatterFactory<?> annotationsFormatter : annotationsFormatters) {
            if (logger.isDebugEnabled()) {
                logger.info("Registering annotation formatter " + annotationsFormatter.getClass().getSimpleName());
            }
            registry.addFormatterForFieldAnnotation(annotationsFormatter);
        }
    }

    private void addEnumFormatters(FormatterRegistry registry, Class<?>... classes) {
        for (Class<?> clazz : classes) {
            if (logger.isDebugEnabled()) {
                logger.debug("Registering default enum formatter for " + clazz.getSimpleName());
            }
            registry.addFormatterForFieldType(clazz, new DefaultEnumFormatter(clazz, messageSource));
        }
    }
}
