package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.SalahDaily;
import com.bizintelapps.zytoon.domain.UserProfile;
import java.util.List;

/**
 * DAO Interface for {@link UserProfile}
 */
public interface SalahDailyDao extends GenericDao<SalahDaily, Integer> {
    
    List findDailyByUser(Integer userBasicId);
    
}
