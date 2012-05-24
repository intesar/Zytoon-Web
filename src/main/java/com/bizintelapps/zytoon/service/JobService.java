package com.bizintelapps.zytoon.service;

/**
 *
 * @author Intesar Mohammed
 */
public interface JobService {

    void activateDueReports();

    void updateExpiredPrograms();

    void programCeationJob();
    
    void notifyDueReports();
}
