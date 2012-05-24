package com.bizintelapps.zytoon.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.FoodDaily;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Hibernate implementation of the UserProfile DAO interface.
 */
@Repository
public class FoodDailyDaoImpl extends HibernateGenericDao<FoodDaily, Integer> implements FoodDailyDao {
    public FoodDailyDaoImpl() {
        super(FoodDaily.class);
    }
    
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(FoodDailyDaoImpl.class);

    @Override
    public List<FoodDaily> findDailyByUser(Integer userBasicId) {
        List<FoodDaily> list = null;
        try {
            list = getEntityManager().createNamedQuery("FoodDaily.findDailyByUser")
                    .setParameter("userId", userBasicId)
                    .getResultList();
        } catch (RuntimeException re){
            logger.warn(re.getMessage(), re);
            list = new ArrayList<FoodDaily>();
        }
        return list;
    }

   
}