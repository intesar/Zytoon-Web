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
@Table(name = "salah_daily")
//history_salah_daily
@Audited
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name="SalahDaily.findDailyByUser", 
        query="SELECT h.monthly.year, h.monthly.month, h.day, h.points "
        + "FROM SalahDaily h WHERE h.monthly.user.id = :userId")
})
public class SalahDaily implements Identifiable<Integer>, Serializable, Copyable<SalahDaily> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SalahDaily.class);
    // Raw attributes
    private Integer id; // pk
    private SalahMonthly monthly;
    private Integer day;
    private Double points;

    // ---------------------------
    // Constructors
    // ---------------------------
    public SalahDaily() {
    }

    public SalahDaily(Integer primaryKey) {
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
    public SalahMonthly getMonthly() {
        return monthly;
    }

    public void setMonthly(SalahMonthly monthly) {
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
        final SalahDaily other = (SalahDaily) obj;
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
        return "HistorySalahDaily{" + "id=" + id + ", monthly=" + monthly + ", day=" + day + ", points=" + points + '}';
    }
    
    

    // -----------------------------------------
    // Copyable Implementation
    // (Support for REST web layer)
    // -----------------------------------------
    /**
     * Return a copy of the current object
     */
    @Override
    public SalahDaily copy() {
        SalahDaily userProfile = new SalahDaily();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(SalahDaily userProfile) {
    }
}