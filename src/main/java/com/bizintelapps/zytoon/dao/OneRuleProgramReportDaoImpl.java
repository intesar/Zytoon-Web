package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.hibernate.HibernateGenericDao;
import com.bizintelapps.zytoon.domain.OneRuleProgramReport;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class OneRuleProgramReportDaoImpl extends HibernateGenericDao<OneRuleProgramReport, Integer> implements OneRuleProgramReportDao {

    public OneRuleProgramReportDaoImpl() {
        super(OneRuleProgramReport.class);
    }

    @Override
    public OneRuleProgramReport findByEnrollment(Integer id) {
        try {
            List<OneRuleProgramReport> list = this.getEntityManager().createNamedQuery("OneRuleProgramReport.findByEnrollment").setParameter("userEnrollmentId", id).setMaxResults(1).getResultList();
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            } 
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<OneRuleProgramReport> findByEnrollmentId (Integer id, Boolean isDue) {
        return  this.getEntityManager().createNamedQuery("OneRuleProgramReport.findByEnrollmentId")
                .setParameter("userEnrollmentId", id)
                .setParameter("isDue", isDue)
                .getResultList();
            
    }
    
    @Override
    public OneRuleProgramReport findById(Integer id) {
        return this.getEntityManager().find(OneRuleProgramReport.class, id);
    }
}
