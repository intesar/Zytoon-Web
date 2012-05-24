/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.service;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author intesar
 */
public class UserEnrollmentServiceImplTest {
    
    public UserEnrollmentServiceImplTest() {
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

    /**
     * Test of saveReport method, of class UserEnrollmentServiceImpl.
     */
    @Test
    public void testSaveReport() {
        Calendar cal = Calendar.getInstance();
        // 9/20/2011 time is before 9/20/2011 time-2
    }
    
    @Test
    public void testAddDays() {
        Calendar cal = Calendar.getInstance();
        
        int days = 60;
        cal.add(Calendar.DAY_OF_MONTH, days-1);
        
        System.out.println ( cal.getTime() );
    }

    
}
