package com.bizintelapps.zytoon.dao;

import com.bizintelapps.zytoon.dao.support.GenericDao;
import com.bizintelapps.zytoon.domain.FoodMonthly;
import com.bizintelapps.zytoon.domain.UserProfile;
import java.util.List;

/**
 * DAO Interface for {@link UserProfile}
 */
public interface FoodMonthlyDao extends GenericDao<FoodMonthly, Integer> {
    
    FoodMonthly findByUserAndDate(Integer userId, Integer month, Integer year);
    List<FoodMonthly> findAllByUserAndDate(Integer userId, Integer month, Integer year);
    List<FoodMonthly> findDailyByUserAndDate(Integer userId, Integer month, Integer year);
}
