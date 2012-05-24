package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.OneRuleProgramMonthly;
import com.bizintelapps.zytoon.domain.UserProfile;
import java.util.List;

/**
 * DAO Interface for {@link UserProfile}
 */
public interface OneRuleProgramMonthlyDao extends GenericDao<OneRuleProgramMonthly, Integer> {
    
    OneRuleProgramMonthly findByUserAndDate(Integer userId, Integer programStructureId, Integer month, Integer year);
    List<OneRuleProgramMonthly> findAllByUserAndDate(Integer userId, Integer programStructureId, Integer month, Integer year);
    List<OneRuleProgramMonthly> findDailyByUserAndDate(Integer userId, Integer programStructureId, Integer month, Integer year);
}
