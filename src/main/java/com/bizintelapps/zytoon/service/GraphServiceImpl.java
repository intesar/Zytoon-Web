package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.dao.FoodDailyDao;
import com.bizintelapps.zytoon.dao.FoodMonthlyDao;
import com.bizintelapps.zytoon.dao.OneRuleProgramDailyDao;
import com.bizintelapps.zytoon.dao.OneRuleProgramMonthlyDao;
import com.bizintelapps.zytoon.dao.SalahDailyDao;
import com.bizintelapps.zytoon.dao.SalahMonthlyDao;
import com.bizintelapps.zytoon.dao.UserBasicDao;
import com.bizintelapps.zytoon.domain.FoodDaily;
import com.bizintelapps.zytoon.domain.FoodMonthly;
import com.bizintelapps.zytoon.domain.OneRuleProgramDaily;
import com.bizintelapps.zytoon.domain.OneRuleProgramMonthly;
import com.bizintelapps.zytoon.domain.OneRuleProgramReport;
import com.bizintelapps.zytoon.domain.SalahMonthly;
import com.bizintelapps.zytoon.domain.SalahDaily;
import com.bizintelapps.zytoon.domain.UserBasic;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Intesar Mohammed
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GraphServiceImpl implements GraphService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
    @Autowired
    protected SalahMonthlyDao salahMonthlyDao;
    @Autowired
    protected SalahDailyDao salahDailyDao;
    @Autowired
    protected FoodMonthlyDao foodMonthlyDao;
    @Autowired
    protected FoodDailyDao foodDailyDao;
    @Autowired
    protected OneRuleProgramMonthlyDao oneRuleProgramMonthlyDao;
    @Autowired
    protected OneRuleProgramDailyDao oneRuleProgramDailyDao;
    @Autowired
    protected UserBasicDao userBasicDao;
    @Autowired
    protected UsersService usersService;

    @Override
    @Transactional
    //@Async
    public void onSubmitSalah(Integer userProfileId, Date dt, Integer fajr, Integer dhuhr, Integer asr, Integer magrib, Integer ishaa) {
        try {
            if (logger.isTraceEnabled()) {
                logger.trace("Processing " + userProfileId);
            }
            // identify user
            UserBasic userBasic = userBasicDao.findByUserProfileId(userProfileId);
            if (userBasic == null) {
                userBasic = usersService.getUserBasicByUserProfileId(userProfileId);
            }


            // create SalahMonthly record if not present
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            Integer month = cal.get(Calendar.MONTH);
            Integer year = cal.get(Calendar.YEAR);

            // find SalahMonthly
            SalahMonthly hs = salahMonthlyDao.findByUserAndDate(userBasic.getId(), month, year);

            if (hs == null) {
                hs = new SalahMonthly();
                hs.setMonth(month);
                hs.setYear(year);
                hs.setUser(userBasic);
            }

            hs.setFajr((hs.getFajr() == null ? 0 : hs.getFajr()) + (fajr == 0 ? -1 : 0));
            hs.setDhuhr((hs.getDhuhr() == null ? 0 : hs.getDhuhr()) + (dhuhr == 0 ? -1 : 0));
            hs.setAsr((hs.getAsr() == null ? 0 : hs.getAsr()) + (asr == 0 ? -1 : 0));
            hs.setMagrib((hs.getMagrib() == null ? 0 : hs.getMagrib()) + (magrib == 0 ? -1 : 0));
            hs.setIshaa((hs.getIshaa() == null ? 0 : hs.getIshaa()) + (ishaa == 0 ? -1 : 0));

            int prayed = fajr + dhuhr + asr + magrib + ishaa;

            hs.setPrayed((hs.getPrayed() == null ? 0 : hs.getPrayed()) + prayed);

            if (logger.isTraceEnabled()) {
                logger.trace("saving " + hs);
            }
            this.salahMonthlyDao.save(hs);

            // create HistorySalahDay record
            SalahDaily daily = new SalahDaily();

            daily.setDay(cal.get(Calendar.DAY_OF_MONTH));
            daily.setMonthly(hs);
            daily.setPoints(new Double(prayed));

            if (logger.isTraceEnabled()) {
                logger.trace("saving " + daily);
            }
            salahDailyDao.save(daily);
            evictAll();
        } catch (RuntimeException re) {
            logger.error(re.getMessage(), re);
        }

    }

    @Override
    @Transactional
    public void onSubmitFood(Integer userProfileId, Date dt, Integer breakfast, Integer lunch, Integer dinner) {
        try {
            // identify user
            UserBasic userBasic = userBasicDao.findByUserProfileId(userProfileId);
            if (userBasic == null) {
                userBasic = usersService.getUserBasicByUserProfileId(userProfileId);
            }

            if (logger.isTraceEnabled()) {
                logger.trace("after ub ");
            }
            System.out.println(" after ub ");
            // create SalahMonthly record if not present
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            Integer month = cal.get(Calendar.MONTH);
            Integer year = cal.get(Calendar.YEAR);

            // find SalahMonthly
            FoodMonthly hs = foodMonthlyDao.findByUserAndDate(userBasic.getId(), month, year);

            if (logger.isTraceEnabled()) {
                logger.trace("after foodMonthly ");
            }

            if (hs == null) {
                hs = new FoodMonthly();
                hs.setMonth(month);
                hs.setYear(year);
                hs.setUser(userBasic);
            }

            if (logger.isTraceEnabled()) {
                logger.trace("after object ");
            }

            hs.setBreakfast((hs.getBreakfast() == null ? 0 : hs.getBreakfast()) + (breakfast == 0 ? -1 : 0));
            hs.setLunch((hs.getLunch() == null ? 0 : hs.getLunch()) + (lunch == 0 ? -1 : 0));
            hs.setDinner((hs.getDinner() == null ? 0 : hs.getDinner()) + (dinner == 0 ? -1 : 0));

            if (logger.isTraceEnabled()) {
                logger.trace("before total ");
            }

            int total = breakfast + lunch + dinner;

            hs.setPoints((hs.getPoints() == null ? 0 : hs.getPoints()) + total);

            if (logger.isTraceEnabled()) {
                logger.trace("after points ");
            }

            if (logger.isTraceEnabled()) {
                logger.trace("saving " + hs);
            }
            this.foodMonthlyDao.save(hs);

            // create HistorySalahDay record
            FoodDaily daily = new FoodDaily();

            daily.setDay(cal.get(Calendar.DAY_OF_MONTH));
            daily.setMonthly(hs);
            daily.setPoints(new Double(total));

            if (logger.isTraceEnabled()) {
                logger.trace("saving " + daily);
            }
            foodDailyDao.save(daily);

            evictAll();

        } catch (RuntimeException re) {
            logger.error(re.getMessage(), re);
        }
    }

    @CacheEvict(value = {"getHistory"}, allEntries = true)
    @Override
    public void evictAll() {
        if (logger.isTraceEnabled()) {
            logger.trace("evictAll");
        }
    }

    @Override
    @Cacheable(value = "getHistory")
    public List getHistory(String username) {
        if (logger.isTraceEnabled()) {
            logger.trace("called getHistory ");
        }
        Integer userBasicId = usersService.findUserBasicByUsername(username).getId();
        return salahDailyDao.findDailyByUser(userBasicId);
    }

    @Override
    @Cacheable(value = "salahMonthly", condition = "#_month < 0")
    public SalahMonthly getSalahMonthly(Integer _month, String username) {
        if (logger.isTraceEnabled()) {
            logger.trace("called getMonthly " + username);
        }

        Integer userBasicId = usersService.findUserBasicByUsername(username).getId();

        Calendar cal = Calendar.getInstance();
        if (_month != 0) {
            cal.add(Calendar.MONTH, _month);
        }

        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);

        SalahMonthly hs = salahMonthlyDao.findByUserAndDate(userBasicId, month, year);

        if (hs == null) {
            hs = new SalahMonthly();
            hs.setMonth(month);
            hs.setYear(year);
        }
        return hs.copy();
    }

    @Override
    @Cacheable(value = "salahFamily", condition = "#_month < 0")
    public List<SalahMonthly> getSalahFamily(Integer _month, String username) {

        if (logger.isTraceEnabled()) {
            logger.trace("called getMonthlyFamily " + username);
        }
        Integer userBasicId = usersService.findUserBasicByUsername(username).getId();

        Calendar cal = Calendar.getInstance();
        if (_month != 0) {
            cal.add(Calendar.MONTH, _month);
        }

        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);

        List<SalahMonthly> list = salahMonthlyDao.findAllByUserAndDate(userBasicId, month, year);

        if (list == null || list.isEmpty()) {
            list = new ArrayList<SalahMonthly>();
        }
        List<SalahMonthly> dtos = new ArrayList<SalahMonthly>(list.size());
        for (SalahMonthly hs : list) {
            dtos.add(hs.copy());
        }
        return dtos;
    }

    @Override
    @Cacheable(value = "foodMonthly", condition = "#_month < 0")
    public FoodMonthly getFoodMonthly(Integer _month, String username) {
        if (logger.isTraceEnabled()) {
            logger.trace("called getMonthly " + username);
        }

        Integer userBasicId = usersService.findUserBasicByUsername(username).getId();

        Calendar cal = Calendar.getInstance();
        if (_month != 0) {
            cal.add(Calendar.MONTH, _month);
        }

        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);

        FoodMonthly hs = foodMonthlyDao.findByUserAndDate(userBasicId, month, year);

        if (hs == null) {
            hs = new FoodMonthly();
            hs.setMonth(month);
            hs.setYear(year);
        }
        return hs.copy();
    }

    @Override
    @Cacheable(value = "foodFamily", condition = "#_month < 0")
    public List<FoodMonthly> getFoodFamily(Integer _month, String username) {

        if (logger.isTraceEnabled()) {
            logger.trace("called getMonthlyFamily " + username);
        }
        Integer userBasicId = usersService.findUserBasicByUsername(username).getId();

        Calendar cal = Calendar.getInstance();
        if (_month != 0) {
            cal.add(Calendar.MONTH, _month);
        }

        Integer month = cal.get(Calendar.MONTH);
        Integer year = cal.get(Calendar.YEAR);

        List<FoodMonthly> list = foodMonthlyDao.findAllByUserAndDate(userBasicId, month, year);

        if (list == null || list.isEmpty()) {
            list = new ArrayList<FoodMonthly>();
        }
        List<FoodMonthly> dtos = new ArrayList<FoodMonthly>(list.size());
        for (FoodMonthly hs : list) {
            dtos.add(hs.copy());
        }
        return dtos;
    }

    @Override
    public FoodMonthly getOneRuleProgramMonthly(Integer month, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<FoodMonthly> getOneRuleProgramFamily(Integer month, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public void onSubmitOneRuleProgram(Integer userProfileId, Integer psId, Date dt, Integer points) {
        try {
            // identify user
            UserBasic userBasic = userBasicDao.findByUserProfileId(userProfileId);
            if (userBasic == null) {
                userBasic = usersService.getUserBasicByUserProfileId(userProfileId);
            }

            if (logger.isTraceEnabled()) {
                logger.trace("after ub ");
            }
            System.out.println(" after ub ");
            // create SalahMonthly record if not present
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            Integer month = cal.get(Calendar.MONTH);
            Integer year = cal.get(Calendar.YEAR);

            // find SalahMonthly
            OneRuleProgramMonthly hs = oneRuleProgramMonthlyDao.findByUserAndDate(userBasic.getId(), psId, month, year);

            if (logger.isTraceEnabled()) {
                logger.trace("after foodMonthly ");
            }

            if (hs == null) {
                hs = new OneRuleProgramMonthly();
                hs.setMonth(month);
                hs.setYear(year);
                hs.setUser(userBasic);
                hs.setCategoryId(psId);
            }

            if (logger.isTraceEnabled()) {
                logger.trace("after object ");
            }

//            hs.setBreakfast((hs.getBreakfast() == null ? 0 : hs.getBreakfast()) + (breakfast == 0 ? -1 : 0));
//            hs.setLunch((hs.getLunch() == null ? 0 : hs.getLunch()) + (lunch == 0 ? -1 : 0));
//            hs.setDinner((hs.getDinner() == null ? 0 : hs.getDinner()) + (dinner == 0 ? -1 : 0));

            if (logger.isTraceEnabled()) {
                logger.trace("before total ");
            }

            int total = points;

            hs.setPoints((hs.getPoints() == null ? 0 : hs.getPoints()) + total);

            if (logger.isTraceEnabled()) {
                logger.trace("after points ");
            }

            if (logger.isTraceEnabled()) {
                logger.trace("saving " + hs);
            }
            this.oneRuleProgramMonthlyDao.save(hs);

            // create HistorySalahDay record
            OneRuleProgramDaily daily = new OneRuleProgramDaily();

            daily.setDay(cal.get(Calendar.DAY_OF_MONTH));
            daily.setMonthly(hs);
            daily.setPoints(new Double(total));

            if (logger.isTraceEnabled()) {
                logger.trace("saving " + daily);
            }
            oneRuleProgramDailyDao.save(daily);

            //evictAll();

        } catch (RuntimeException re) {
            logger.error(re.getMessage(), re);
        }
    }
}
