package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.FoodDaily;
import com.bizintelapps.zytoon.domain.UserProfile;
import java.util.List;

/**
 * DAO Interface for {@link UserProfile}
 */
public interface FoodDailyDao extends GenericDao<FoodDaily, Integer> {
    
    List<FoodDaily> findDailyByUser(Integer userBasicId);
    
}
