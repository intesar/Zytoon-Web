package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.Report;
import com.bizintelapps.zytoon.domain.SalahReport;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class SalahReportDaoImpl extends HibernateGenericDao<Report, Integer> implements SalahReportDao {

    public SalahReportDaoImpl() {
        super(Report.class);
    }

    @Override
    public SalahReport findByEnrollment(Integer id) {
        try {
            List<SalahReport> list = this.getEntityManager().createNamedQuery("SalahReport.findByEnrollment").setParameter("userEnrollmentId", id).setMaxResults(1).getResultList();
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            } 
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<SalahReport> findByEnrollmentId (Integer id, Boolean isDue) {
        return  this.getEntityManager().createNamedQuery("SalahReport.findByEnrollmentId")
                .setParameter("userEnrollmentId", id)
                .setParameter("isDue", isDue)
                .getResultList();
            
    }
    
    @Override
    public SalahReport findById(Integer id) {
        return this.getEntityManager().find(SalahReport.class, id);
    }
}
