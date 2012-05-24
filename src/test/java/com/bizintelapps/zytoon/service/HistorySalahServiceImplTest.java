/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.service;

import com.bizintelapps.zytoon.domain.SalahMonthly;
import com.bizintelapps.zytoon.dao.SalahMonthlyDao;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class HistorySalahServiceImplTest {
    
    @Autowired
    protected GraphService service;
    @Autowired
    protected SalahMonthlyDao dao;
    
    public HistorySalahServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testX(){}
    /**
     * Test of onSubmit method, of class HistorySalahServiceImpl.
     */
    //@Test
    public void testOnSubmit() {
        Calendar cal = Calendar.getInstance();
        service.onSubmitSalah(1, cal.getTime(), 1, 1,1,1,1);
        SalahMonthly hs = dao.findByUserAndDate(4, cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
        assertNotNull(hs);
        
    }

    /**
     * Test of getHistory method, of class HistorySalahServiceImpl.
     */
    //@Test
    public void testGetHistory() {
       Calendar cal = Calendar.getInstance();
       service.onSubmitSalah(1, cal.getTime(), 1, 1,1,1,1);
       SalahMonthly hs = dao.findByUserAndDate(4, cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
       System.out.println(hs);
       assertNotNull(hs);
    }

    /**
     * Test of getBestGroupHistory method, of class HistorySalahServiceImpl.
     */
    //@Test
    public void testGetBestGroupHistory() {
        Calendar cal = Calendar.getInstance();
        service.onSubmitSalah(1, cal.getTime(), 1, 1,1,1,1);
        assertNotNull (dao.findDailyByUserAndDate(4, cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)));
    }
}
