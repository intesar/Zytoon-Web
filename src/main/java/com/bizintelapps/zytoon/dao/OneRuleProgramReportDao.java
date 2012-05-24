/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.OneRuleProgramReport;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface OneRuleProgramReportDao extends GenericDao<OneRuleProgramReport, Integer> {

    OneRuleProgramReport findByEnrollment(Integer id);

    List<OneRuleProgramReport> findByEnrollmentId(Integer id, Boolean isDue);

    OneRuleProgramReport findById(Integer id);
}
