package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.OneRuleProgramDaily;
import com.bizintelapps.zytoon.domain.UserProfile;
import java.util.List;

/**
 * DAO Interface for {@link UserProfile}
 */
public interface OneRuleProgramDailyDao extends GenericDao<OneRuleProgramDaily, Integer> {
    
    List<OneRuleProgramDaily> findDailyByUser(Integer userBasicId);
    
}
