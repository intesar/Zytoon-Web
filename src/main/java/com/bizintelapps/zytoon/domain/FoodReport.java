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
@Table(name = "food_report")
@NamedQueries({
    @NamedQuery(name = "FoodReport.findByEnrollment",
    query = "SELECT s FROM FoodReport s WHERE s.userEnrollmentId = :userEnrollmentId "
    + "AND s.isSubmitted = false AND s.isDue = false  ORDER BY s.daySequence"),
    @NamedQuery(name = "FoodReport.findByEnrollmentId",
    query = "SELECT s FROM FoodReport s WHERE s.userEnrollmentId = :userEnrollmentId AND s.isDue = :isDue ")
})
public class FoodReport extends Report {

    private static final long serialVersionUID = 1L;
    private Integer breakfast;
    private Integer lunch;
    private Integer dinner;

    public FoodReport() {
    }

    public FoodReport(Integer daySequence, Date reportDate, Date dueDateTime, Boolean isDue, Boolean isSubmitted, Integer breakfast, Integer lunch, Integer dinner, Integer points) {
        this.daySequence = daySequence;
        this.reportDate = reportDate;
        this.dueDateTime = dueDateTime;
        this.isDue = isDue;
        this.isSubmitted = isSubmitted;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.points = points;
    }

    // -- [fajr] ------------------------
    @Column(name = "breakfast", precision = 1, insertable = true, updatable = true)
    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    @Column(name = "dinner", precision = 1, insertable = true, updatable = true)
    public Integer getDinner() {
        return dinner;
    }

    public void setDinner(Integer dinner) {
        this.dinner = dinner;
    }

    @Column(name = "lunch", precision = 1, insertable = true, updatable = true)
    public Integer getLunch() {
        return lunch;
    }

    public void setLunch(Integer lunch) {
        this.lunch = lunch;
    }

    @Override
    public String toString() {
        return "FoodReport{" + "breakfast=" + breakfast + ", lunch=" + lunch + ", dinner=" + dinner + '}';
    }
}
