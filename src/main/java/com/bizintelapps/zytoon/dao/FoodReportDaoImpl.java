package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.FoodReport;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class FoodReportDaoImpl extends HibernateGenericDao<FoodReport, Integer> implements FoodReportDao {

    public FoodReportDaoImpl() {
        super(FoodReport.class);
    }

    @Override
    public FoodReport findByEnrollment(Integer id) {
        try {
            List<FoodReport> list = this.getEntityManager().createNamedQuery("FoodReport.findByEnrollment").setParameter("userEnrollmentId", id).setMaxResults(1).getResultList();
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            } 
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<FoodReport> findByEnrollmentId (Integer id, Boolean isDue) {
        return  this.getEntityManager().createNamedQuery("FoodReport.findByEnrollmentId")
                .setParameter("userEnrollmentId", id)
                .setParameter("isDue", isDue)
                .getResultList();
            
    }
    
    @Override
    public FoodReport findById(Integer id) {
        return this.getEntityManager().find(FoodReport.class, id);
    }
}
