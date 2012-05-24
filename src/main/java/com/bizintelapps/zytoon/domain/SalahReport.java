/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author intesar
 */
@Entity
@Audited
@Table(name = "salah_report")
@NamedQueries({
    @NamedQuery(name = "SalahReport.findByEnrollment",
    query = "SELECT s FROM SalahReport s WHERE s.userEnrollmentId = :userEnrollmentId "
    + "AND s.isSubmitted = false AND s.isDue = false  ORDER BY s.daySequence"),
    @NamedQuery(name = "SalahReport.findByEnrollmentId",
    query = "SELECT s FROM SalahReport s WHERE s.userEnrollmentId = :userEnrollmentId AND s.isDue = :isDue ")
})
public class SalahReport extends Report {

    private static final long serialVersionUID = 1L;
    private Integer fajr;
    private Integer zuhr;
    private Integer asr;
    private Integer magrib;
    private Integer isha;

    public SalahReport() {
    }

    public SalahReport(Integer daySequence, Date reportDate, Date dueDateTime, Boolean isDue, Boolean isSubmitted, Integer fajr, Integer zuhr, Integer asr, Integer magrib, Integer isha, Integer points) {
        this.daySequence = daySequence;
        this.reportDate = reportDate;
        this.dueDateTime = dueDateTime;
        this.isDue = isDue;
        this.isSubmitted = isSubmitted;
        this.fajr = fajr;
        this.zuhr = zuhr;
        this.asr = asr;
        this.magrib = magrib;
        this.isha = isha;
        this.points = points;
    }

    // -- [fajr] ------------------------
    @Column(name = "fajr", precision = 1, insertable = true, updatable = true)
    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    // -- [zuhr] ------------------------
    @Column(name = "zuhr", precision = 1, insertable = true, updatable = true)
    public Integer getZuhr() {
        return zuhr;
    }

    public void setZuhr(Integer zuhr) {
        this.zuhr = zuhr;
    }

    // -- [asr] ------------------------
    @Column(name = "asr", precision = 1, insertable = true, updatable = true)
    public Integer getAsr() {
        return asr;
    }

    public void setAsr(Integer asr) {
        this.asr = asr;
    }

    // -- [magrib] ------------------------
    @Column(name = "magrib", precision = 1, insertable = true, updatable = true)
    public Integer getMagrib() {
        return magrib;
    }

    public void setMagrib(Integer magrib) {
        this.magrib = magrib;
    }

    // -- [isha] ------------------------
    @Column(name = "isha", precision = 1, insertable = true, updatable = true)
    public Integer getIsha() {
        return isha;
    }

    public void setIsha(Integer isha) {
        this.isha = isha;
    }

    @Override
    public String toString() {
        return "";
    }
}
