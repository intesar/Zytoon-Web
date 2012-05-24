package com.bizintelapps.zytoon.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Intesar Mohammed
 */
@Component
public class JobServiceImpl implements JobService {
    
    private static final Logger logger = Logger.getLogger(JobServiceImpl.class);
    @Autowired
    protected ProgramService programService;
    
    // -------- jobs  ------
    //@Scheduled(fixedRate=1000 * 60 * 5) // testing
    @Scheduled(cron = "0 0 1 * * *") // production
    @Override
    public void activateDueReports() {
        if (logger.isTraceEnabled()) {
            logger.trace("Executing activateDueReports()");
        }
        this.programService.activateDueReports();
    }

    //@Scheduled(fixedRate=1000 * 60 * 5) // testing
    @Scheduled(cron = "0 0 1 * * *") // production
    @Override
    public void updateExpiredPrograms() {
        if (logger.isTraceEnabled()) {
            logger.trace("Executing updateExpiredPrograms()");
        }
        this.programService.updateExpiredPrograms();
    }

    //@Scheduled(fixedRate=1000 * 60 * 5) // testing
    @Scheduled(cron = "0 0 1 * * MON,WED,FRI") // production
    @Override
    public void programCeationJob() {
        if (logger.isTraceEnabled()) {
            logger.trace("Executing programCeationJob()");
        }
        this.programService.programCeationJob();
    }
    
    //@Scheduled(fixedRate=1000 * 60 * 5) // testing
    @Scheduled(cron = "0 0 1 * * *") // production
    @Override
    public void notifyDueReports() {
        if (logger.isTraceEnabled()) {
            logger.trace("Executing notifyDueReports()");
        }
        this.programService.notifyDueReports();
    }
}
