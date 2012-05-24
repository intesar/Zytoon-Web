/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.FoodReport;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface FoodReportDao extends GenericDao<FoodReport, Integer> {
    
    FoodReport findByEnrollment(Integer id);
    
    List<FoodReport> findByEnrollmentId (Integer id, Boolean isDue);
    
    FoodReport findById(Integer id);
}
