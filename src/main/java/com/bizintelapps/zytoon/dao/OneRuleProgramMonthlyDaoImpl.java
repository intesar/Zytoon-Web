package com.bizintelapps.zytoon.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.OneRuleProgramMonthly;

/**
 * Hibernate implementation of the UserProfile DAO interface.
 */
@Repository
public class OneRuleProgramMonthlyDaoImpl extends HibernateGenericDao<OneRuleProgramMonthly, Integer> implements OneRuleProgramMonthlyDao {
    public OneRuleProgramMonthlyDaoImpl() {
        super(OneRuleProgramMonthly.class);
    }

    @Override
    public List<OneRuleProgramMonthly> findDailyByUserAndDate(Integer userId, Integer categoryId, Integer month, Integer year) {
        List<OneRuleProgramMonthly> list = null;
        try {
            list = this.getEntityManager().createNamedQuery("OneRuleProgramMonthly.findByUserAndDate", OneRuleProgramMonthly.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
        } catch ( RuntimeException re){}
        
        return list;
    }

    @Override
    public OneRuleProgramMonthly findByUserAndDate(Integer userId, Integer categoryId, Integer month, Integer year) {
        OneRuleProgramMonthly hs = null;
        try {
            hs = this.getEntityManager().createNamedQuery("OneRuleProgramMonthly.findByUserAndDate", OneRuleProgramMonthly.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)    
                .setParameter("month", month)
                .setParameter("year", year)
                .getSingleResult();
        } catch ( RuntimeException re){}
        return hs;
    }

    @Override
    public List<OneRuleProgramMonthly> findAllByUserAndDate(Integer userId, Integer categoryId, Integer month, Integer year) {
        List<OneRuleProgramMonthly> hs = null;
        try {
            hs = this.getEntityManager().createNamedQuery("OneRuleProgramMonthly.findAllByUserAndDate", OneRuleProgramMonthly.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)    
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
        } catch ( RuntimeException re){}
        return hs;
    }
   
}