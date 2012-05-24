package com.bizintelapps.zytoon.domain;

import javax.persistence.ManyToOne;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import com.bizintelapps.zytoon.validation.JpaUnique;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "onerule_program_daily")
@Audited
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name="OneRuleProgramDaily.findDailyByUser", 
        query="SELECT h.monthly.year, h.monthly.month, h.day, h.points "
        + "FROM OneRuleProgramDaily h WHERE h.monthly.user.id = :userId")
})
public class OneRuleProgramDaily implements Identifiable<Integer>, Serializable, Copyable<OneRuleProgramDaily> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(OneRuleProgramDaily.class);
    // Raw attributes
    private Integer id; // pk
    private OneRuleProgramMonthly monthly;
    private Integer day;
    private Double points;

    // ---------------------------
    // Constructors
    // ---------------------------
    public OneRuleProgramDaily() {
    }

    public OneRuleProgramDaily(Integer primaryKey) {
        this();
        setPrimaryKey(primaryKey);
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
    public boolean isPrimaryKeySet() {
        return isIdSet();
    }

    // -------------------------------
    // Getter & Setter
    // -------------------------------
    // -- [id] ------------------------
    @Column(nullable = false, unique = true, precision = 10)
    @GeneratedValue
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

    @Column(name="day_")
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @ManyToOne
    public OneRuleProgramMonthly getMonthly() {
        return monthly;
    }

    public void setMonthly(OneRuleProgramMonthly monthly) {
        this.monthly = monthly;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    // -----------------------------------------
    // Set defaults values
    // -----------------------------------------
    /**
     * Set the default values.
     */
    public void initDefaultValues() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OneRuleProgramDaily other = (OneRuleProgramDaily) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    // -----------------------------------------
    // toString
    // -----------------------------------------

    @Override
    public String toString() {
        return "HistorySalahDaily{" + "id=" + id +  ", day=" + day + ", points=" + points + '}';
    }
    
    

    // -----------------------------------------
    // Copyable Implementation
    // (Support for REST web layer)
    // -----------------------------------------
    /**
     * Return a copy of the current object
     */
    @Override
    public OneRuleProgramDaily copy() {
        OneRuleProgramDaily userProfile = new OneRuleProgramDaily();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(OneRuleProgramDaily userProfile) {
    }
}