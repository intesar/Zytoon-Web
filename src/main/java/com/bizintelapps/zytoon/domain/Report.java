package com.bizintelapps.zytoon.domain;

import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;
import javax.persistence.GenerationType;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cache;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import org.springframework.format.annotation.DateTimeFormat;
import static com.bizintelapps.zytoon.util.ResourcesUtil.DATE_FORMAT_PATTERN;

/**
 *
 * @author Intesar Mohammed
 */
@Entity
@Audited
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Report implements Identifiable<Integer>, Serializable, Copyable<Report>  {

    // Technical attributes for query by example
    protected Integer id;
    protected Integer daySequence;
    protected Date reportDate;
    protected Date dueDateTime;
    protected Boolean isDue;
    protected Boolean isSubmitted;
    protected Date submittedDate;
    protected Integer points;
    protected UserEnrollment userEnrollment;
    protected Integer userEnrollmentId;
    

    public Report() {
    }

    public Report(Integer daySequence, Date reportDate, Date dueDateTime, Boolean isDue) {
        this.daySequence = daySequence;
        this.reportDate = reportDate;
        this.dueDateTime = dueDateTime;
        this.isDue = isDue;
    }

    

    // ---------------------------
    // Identifiable implementation
    // ---------------------------
    @Transient
    @XmlTransient
    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public void setPrimaryKey(Integer id) {
        setId(id);
    }

    @Transient
    @XmlTransient
    @Override
    public boolean isPrimaryKeySet() {
        return isIdSet();
    }

    // -- [id] ------------------------
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Transient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [daySequence] ------------------------
    @Column(name = "day_sequence", precision = 3, insertable = true, updatable = true)
    public Integer getDaySequence() {
        return daySequence;
    }

    public void setDaySequence(Integer daySequence) {
        this.daySequence = daySequence;
    }

    // -- [dueDateTime] ------------------------
    @Column(name = "due_date_time", length = 10)
    @Temporal(DATE)
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    public Date getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(Date dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    // -- [reportDate] ------------------------
    @Column(name = "report_date", length = 10)
    @Temporal(DATE)
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    // -- [isDue] ------------------------
    @Column(name = "is_due", insertable = true, updatable = true)
    public Boolean getIsDue() {
        return isDue;
    }

    public void setIsDue(Boolean isDue) {
        this.isDue = isDue;
    }

    // -- [isSubmitted] ------------------------
    @Column(name = "is_submitted", insertable = true, updatable = true)
    public Boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    // -- [reportDate] ------------------------
    @Column(name = "submitted_date", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    

    // -- [points] ------------------------
    @Column(name = "points", precision = 2, insertable = true, updatable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Column(name = "user_enrollment_id", precision = 10, insertable = false, updatable = false)
    public Integer getUserEnrollmentId() {
        return userEnrollmentId;
    }

    public void setUserEnrollmentId(Integer userEnrollmentId) {
        this.userEnrollmentId = userEnrollmentId;
    }

    
    
    // --------------------------------------------------------------------
    // Many to One support
    // --------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Authorities.username ==> Users.username
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @Cache(usage = NONSTRICT_READ_WRITE)
    @JoinColumn(name = "user_enrollment_id")
    @ManyToOne(cascade = PERSIST, fetch = LAZY)
    public UserEnrollment getUserEnrollment() {
        return userEnrollment;
    }

    /**
     * Set the users without adding this Authorities instance on the passed users
     * If you want to preserve referential integrity we recommend to use
     * instead the corresponding adder method provided by Users
     */
    public void setUserEnrollment(UserEnrollment userEnrollment) {
        this.userEnrollment = userEnrollment;

//        // We set the foreign key property so it can be used by Hibernate search by Example facility.
        if (userEnrollment != null) {
            setUserEnrollmentId(userEnrollment.getId());
        } // when null, we do not propagate it to the pk.
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report{" + "daySequence=" + daySequence + ", reportDate=" + reportDate + ", dueDateTime=" + dueDateTime + ", isDue=" + isDue + ", isSubmitted=" + isSubmitted + ", points=" + points + '}';
    }
    
    
    // -----------------------------------------
    // Copyable Implementation
    // (Support for REST web layer)
    // -----------------------------------------

    /**
     * Return a copy of the current object
     */
    @Override
    public Report copy() {
        Report report = null;
        copyTo(report);
        return report;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(Report report) {
        report.setId(getId());
        //program.setProgramStructureId(getProgramStructureId());
        //report.setStartDate(getStartDate());
        //report.setStatus(getStatus());
        
    }
}
