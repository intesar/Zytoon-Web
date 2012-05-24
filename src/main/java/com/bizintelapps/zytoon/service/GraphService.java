package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.domain.FoodMonthly;
import com.bizintelapps.zytoon.domain.SalahMonthly;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Intesar Mohammed
 */
public interface GraphService {

    List getHistory(String username);

    void onSubmitSalah(Integer userProfileId, Date dt, Integer fajr, Integer dhuhr, Integer asr, Integer magrib, Integer ishaa);
    
    void onSubmitFood(Integer userProfileId, Date dt, Integer breakfast, Integer lunch, Integer dinner);
    
    SalahMonthly getSalahMonthly(Integer _month, String username);
    
    List<SalahMonthly> getSalahFamily(Integer _month, String username);
    
    void evictAll();
    
    FoodMonthly getFoodMonthly(Integer _month, String username);
    
    List<FoodMonthly> getFoodFamily(Integer _month, String username);

    public FoodMonthly getOneRuleProgramMonthly(Integer month, String username);

    public List<FoodMonthly> getOneRuleProgramFamily(Integer month, String username);

    public void onSubmitOneRuleProgram(Integer userId, Integer psId, Date reportDate, Integer points);
}
