/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/main/java/project/hibernate/DAOHibernate.e.vm.java
 */
package com.bizintelapps.zytoon.dao;

import org.springframework.stereotype.Repository;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.UserProgramHistory;
import java.util.List;

/**
 * Hibernate implementation of the UserProfile DAO interface.
 */
@Repository
public class UserProgramHistoryDaoImpl extends HibernateGenericDao<UserProgramHistory, Integer> implements UserProgramHistoryDao {

    public UserProgramHistoryDaoImpl() {
        super(UserProgramHistory.class);
    }

    @Override
    public UserProgramHistory findByProgramStructure(Integer programStructureId, Integer userId) {
        return getEntityManager().createNamedQuery("UserProgramHistory.findByProgramStructure", UserProgramHistory.class).setParameter("programStructureId", programStructureId).setParameter("userId", userId).getSingleResult();
    }

    @Override
    public List<UserProgramHistory> findByProgramStructureIds(List<Integer> programStructureIds, Integer userId) {
        List<UserProgramHistory> list = null;
        try {
            list = getEntityManager().createNamedQuery("UserProgramHistory.findByProgramStructureIds", UserProgramHistory.class).setParameter("programStructureIds", programStructureIds).setParameter("userId", userId).getResultList();
        } catch (RuntimeException ex) {
        }
        return list;
    }
}