/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizintelapps.zytoon.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author intesar
 */
public class ReportDto {

    private String name;
    private Integer days;
    private String description;
    private String category;
    private Integer day;
    private String date;
    private Date reportDate;
    private Integer reportId;
    private Integer fajr;
    private Integer zuhr;
    private Integer asr;
    private Integer magrib;
    private Integer isha;

    public ReportDto(Integer reportId, String name, Integer days, String description,
            String category, Integer day, Date reportDate, Integer fajr, Integer zuhr, 
            Integer asr, Integer magrib, Integer isha) {
        this.reportId = reportId;
        this.name = name;
        this.days = days;
        this.description = description;
        this.category = category;
        this.day = day;
        this.reportDate = reportDate;
        this.fajr = fajr;
        this.zuhr = zuhr;
        this.asr = asr;
        this.magrib = magrib;
        this.isha = isha;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("EEE, MMM dd");
        setDate(df.format(reportDate));
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAsr() {
        return asr;
    }

    public void setAsr(Integer asr) {
        this.asr = asr;
    }

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getIsha() {
        return isha;
    }

    public void setIsha(Integer isha) {
        this.isha = isha;
    }

    public Integer getMagrib() {
        return magrib;
    }

    public void setMagrib(Integer magrib) {
        this.magrib = magrib;
    }

    public Integer getZuhr() {
        return zuhr;
    }

    public void setZuhr(Integer zuhr) {
        this.zuhr = zuhr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportDto other = (ReportDto) obj;
        if (this.reportId != other.reportId && (this.reportId == null || !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.reportId != null ? this.reportId.hashCode() : 0);
        return hash;
    }
}
