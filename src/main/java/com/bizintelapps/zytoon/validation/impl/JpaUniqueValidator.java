/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3:src/main/java/web/validation/impl/JpaUniqueValidator.p.vm.java
 */
package com.bizintelapps.zytoon.validation.impl;

import static java.lang.String.format;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;

import com.bizintelapps.zytoon.validation.JpaUnique;
import com.bizintelapps.zytoon.dao.hibernate.HibernateFilterContext;
import com.bizintelapps.zytoon.domain.Identifiable;

/**
 * Implementation of the {@link JpaUnique} constraint, the implementation is as follows:
 * <ul>
 * <li>Check we have a {@link JpaTemplate} and an {@link HibernateFilterContext} so we can make call to the repository</li>
 * <li>Check the presence of the {@link Entity} annotation</li>
 * <li>Grab all the {@link Column} that are part of the {@link Id} or are {@link UniqueConstraint}</li>
 * <li>Grab all the {@link Column} that are part of the {@link Id} or are {@link UniqueConstraint}</li>
 * <li>Then using the {@link JpaTemplate} make a request to see if there is an existing entity</li>
 * </ul>
 * @see JpaUnique
 * @see EntityManager
 * @see HibernateFilterContext
 * @See http://docs.jboss.org/hibernate/validator/4.0.1/reference/en/html_single/#validator-customconstraints
 */
public class JpaUniqueValidator implements ConstraintValidator<JpaUnique, Identifiable<?>> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private HibernateFilterContext hibernateFilterContext;

    public void initialize(JpaUnique constraintAnnotation) {
    }

    public boolean isValid(Identifiable<?> value, ConstraintValidatorContext context) {
        if (value == null || entityManager == null || hibernateFilterContext == null) {
            return true;
        }
        checkJpaEntityPresence(value);
        Set<String> uniqueColumns = getUniqueProperties(value);
        if (uniqueColumns.size() == 0) {
            return true;
        } else {
            return checkConstraints(value, context, uniqueColumns);
        }
    }

    private void checkJpaEntityPresence(Identifiable<?> value) {
        Entity entity = findAnnotation(value.getClass(), Entity.class);
        if (entity == null) {
            throw new IllegalStateException(value.getClass().getSimpleName()
                    + " is not a a JPA entity, it cannot support " + JpaUnique.class.getSimpleName());
        }
    }

    private boolean checkConstraints(Identifiable<?> value, ConstraintValidatorContext context,
            Set<String> uniqueColumns) {
        boolean valid = true;
        hibernateFilterContext.disableFilters();
        try {
            context.disableDefaultConstraintViolation();
            for (String propertyName : uniqueColumns) {
                List<?> ret = getResults(value, propertyName);
                if (ret != null && ret.size() > 0) {
                    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                            .addNode(propertyName).addConstraintViolation();
                    valid = false;
                }
            }
        } finally {
            hibernateFilterContext.enableFilters();
        }
        return valid;
    }

    private List<?> getResults(Identifiable<?> value, String propertyName) {
        String entityName = getEntityName(value);
        if (value.isPrimaryKeySet()) {
            String pkAttributeName = getPkAttributeName(value.getClass());
            String query = format("from %s where %s = ? and %s != ?", entityName, propertyName, pkAttributeName);
            return new JpaTemplate(entityManager).find(query,
                    new BeanWrapperImpl(value).getPropertyValue(propertyName), value.getPrimaryKey());
        } else {
            String query = format("from %s where %s = ?", entityName, propertyName);
            return new JpaTemplate(entityManager)
                    .find(query, new BeanWrapperImpl(value).getPropertyValue(propertyName));
        }
    }

    public String getEntityName(Identifiable<?> value) {
        Entity entity = findAnnotation(value.getClass(), Entity.class);
        if ("".equals(entity.name())) {
            return value.getClass().getSimpleName();
        }
        return entity.name();
    }

    public Set<String> getUniqueProperties(Identifiable<?> value) {
        Set<String> uniqueColumns = new HashSet<String>();
        for (Method m : value.getClass().getMethods()) {
            String property = getFromMethod(m);
            if (property != null) {
                uniqueColumns.add(property);
            }
        }
        for (Field f : value.getClass().getFields()) {
            String property = getFromField(f);
            if (property != null) {
                uniqueColumns.add(property);
            }
        }
        return uniqueColumns;
    }

    private String getFromMethod(Method m) {
        Column constraint = findAnnotation(m, Column.class);
        if (constraint == null || !constraint.unique()) {
            return null;
        }
        if (findAnnotation(m, Id.class) != null) {
            return null;
        }
        return methodToProperty(m);
    }

    private String getFromField(Field f) {
        Column constraint = f.getAnnotation(Column.class);
        if (constraint == null || !constraint.unique()) {
            return null;
        }
        if (f.getAnnotation(Id.class) != null) {
            // it's the ID
            return null;
        }
        return f.getName();
    }

    private String getPkAttributeName(Class<?> clazz) {
        for (Method m : clazz.getMethods()) {
            if (findAnnotation(m, Id.class) != null) {
                return methodToProperty(m);
            }
        }
        for (Field f : clazz.getFields()) {
            if (f.getAnnotation(Id.class) != null) {
                return f.getName();
            }
        }
        return null;
    }

    private String methodToProperty(Method m) {
        String value = m.getName();
        if (value.startsWith("get")) {
            int length = "get".length();
            return value.substring(length, length + 1).toLowerCase() + value.substring(length + 1);
        } else if (value.startsWith("is")) {
            int length = "is".length();
            return value.substring(length, length + 1).toLowerCase() + value.substring(length + 1);
        } else {
            return value;
        }
    }
}