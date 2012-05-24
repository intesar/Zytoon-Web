package com.bizintelapps.zytoon.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.FoodMonthly;

/**
 * Hibernate implementation of the UserProfile DAO interface.
 */
@Repository
public class FoodMonthlyDaoImpl extends HibernateGenericDao<FoodMonthly, Integer> implements FoodMonthlyDao {
    public FoodMonthlyDaoImpl() {
        super(FoodMonthly.class);
    }

    @Override
    public List<FoodMonthly> findDailyByUserAndDate(Integer userId, Integer month, Integer year) {
        List<FoodMonthly> list = null;
        try {
            list = this.getEntityManager().createNamedQuery("FoodMonthly.findByUserAndDate", FoodMonthly.class)
                .setParameter("userId", userId)
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
        } catch ( RuntimeException re){}
        
        return list;
    }

    @Override
    public FoodMonthly findByUserAndDate(Integer userId, Integer month, Integer year) {
        FoodMonthly hs = null;
        try {
            hs = this.getEntityManager().createNamedQuery("FoodMonthly.findByUserAndDate", FoodMonthly.class)
                .setParameter("userId", userId)
                .setParameter("month", month)
                .setParameter("year", year)
                .getSingleResult();
        } catch ( RuntimeException re){}
        return hs;
    }

    @Override
    public List<FoodMonthly> findAllByUserAndDate(Integer userId, Integer month, Integer year) {
        List<FoodMonthly> hs = null;
        try {
            hs = this.getEntityManager().createNamedQuery("FoodMonthly.findAllByUserAndDate", FoodMonthly.class)
                .setParameter("userId", userId)
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
        } catch ( RuntimeException re){}
        return hs;
    }
   
}