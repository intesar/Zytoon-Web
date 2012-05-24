package com.bizintelapps.zytoon.domain;

import java.util.Date;
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
@Table(name = "onerule_program_report")
@NamedQueries({
    @NamedQuery(name = "OneRuleProgramReport.findByEnrollment",
    query = "SELECT s FROM OneRuleProgramReport s WHERE s.userEnrollmentId = :userEnrollmentId "
    + "AND s.isSubmitted = false AND s.isDue = false  ORDER BY s.daySequence"),
    @NamedQuery(name = "OneRuleProgramReport.findByEnrollmentId",
    query = "SELECT s FROM OneRuleProgramReport s WHERE s.userEnrollmentId = :userEnrollmentId AND s.isDue = :isDue ")
})
public class OneRuleProgramReport extends Report {

    private static final long serialVersionUID = 1L;

    public OneRuleProgramReport() {
    }

    public OneRuleProgramReport(Integer daySequence, Date reportDate, Date dueDateTime, Boolean isDue, Boolean isSubmitted, Integer points) {
        this.daySequence = daySequence;
        this.reportDate = reportDate;
        this.dueDateTime = dueDateTime;
        this.isDue = isDue;
        this.isSubmitted = isSubmitted;
        this.points = points;
    }
}
