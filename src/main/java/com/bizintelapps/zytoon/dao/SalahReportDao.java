/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.Report;
import com.bizintelapps.zytoon.domain.SalahReport;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface SalahReportDao extends GenericDao<Report, Integer> {
    
    SalahReport findByEnrollment(Integer id);
    
    List<SalahReport> findByEnrollmentId (Integer id, Boolean isDue);
    
    SalahReport findById(Integer id);
}
