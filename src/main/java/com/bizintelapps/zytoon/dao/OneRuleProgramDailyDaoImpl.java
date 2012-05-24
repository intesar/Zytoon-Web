package com.bizintelapps.zytoon.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.FoodDaily;
import com.bizintelapps.zytoon.domain.OneRuleProgramDaily;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Hibernate implementation of the UserProfile DAO interface.
 */
@Repository
public class OneRuleProgramDailyDaoImpl extends HibernateGenericDao<OneRuleProgramDaily, Integer> implements OneRuleProgramDailyDao {
    public OneRuleProgramDailyDaoImpl() {
        super(OneRuleProgramDaily.class);
    }
    
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(FoodDailyDaoImpl.class);

    @Override
    public List<OneRuleProgramDaily> findDailyByUser(Integer userBasicId) {
        List<OneRuleProgramDaily> list = null;
        try {
            list = getEntityManager().createNamedQuery("FoodDaily.findDailyByUser")
                    .setParameter("userId", userBasicId)
                    .getResultList();
        } catch (RuntimeException re){
            logger.warn(re.getMessage(), re);
            list = new ArrayList<OneRuleProgramDaily>();
        }
        return list;
    }

   
}