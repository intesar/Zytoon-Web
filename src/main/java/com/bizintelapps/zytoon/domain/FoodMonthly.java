package com.bizintelapps.zytoon.domain;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "food_monthly")
@Audited
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name = "FoodMonthly.findDailyByUserAndDate",
    query = "SELECT h.dailys FROM FoodMonthly h WHERE h.user.id = :userId AND h.month = :month AND h.year = :year"),
    @NamedQuery(name = "FoodMonthly.findByUserAndDate",
    query = "SELECT h FROM FoodMonthly h WHERE h.user.id = :userId AND h.month = :month AND h.year = :year"),
    @NamedQuery(name = "FoodMonthly.findAllByUserAndDate",
    query = "SELECT h FROM FoodMonthly h WHERE (h.user.id in (SELECT u.friend.id FROM UserNetwork u WHERE u.user = :userId AND u.active = true) OR h.user.id = :userId) "
    + "AND h.month = :month AND h.year = :year ORDER BY h.user.name")
})
public class FoodMonthly implements Identifiable<Integer>, Serializable, Copyable<FoodMonthly> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FoodMonthly.class);
    // Raw attributes
    private Integer id; // pk
    private UserBasic user;
    private Integer month;
    private Integer year;
    private Integer points;
    private Integer breakfast;
    private Integer lunch;
    private Integer dinner;
    private List<FoodDaily> dailys;

    // ---------------------------
    // Constructors
    // ---------------------------
    public FoodMonthly() {
    }

    public FoodMonthly(Integer primaryKey) {
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

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getDinner() {
        return dinner;
    }

    public void setDinner(Integer dinner) {
        this.dinner = dinner;
    }

    public Integer getLunch() {
        return lunch;
    }

    public void setLunch(Integer lunch) {
        this.lunch = lunch;
    }

    @Column(name = "month_")
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Transient
    public String getMonthName() {
        if (getMonth() != null) {
            DateFormat df = new SimpleDateFormat("MMMMM");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, getMonth());
            return df.format(cal.getTime());
        }
        return null;
    }

    public void setMonthName(String monthName) {
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @ManyToOne
    public UserBasic getUser() {
        return user;
    }

    public void setUser(UserBasic user) {
        this.user = user;
    }

    @Column(name = "year_")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @OneToMany(mappedBy = "monthly")
    public List<FoodDaily> getDailys() {
        return dailys;
    }

    public void setDailys(List<FoodDaily> dailys) {
        this.dailys = dailys;
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
        final FoodMonthly other = (FoodMonthly) obj;
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

    @Override
    public String toString() {
        return "FoodMonthly{" + "id=" + id + ", user=" + user + ", month=" + month + ", year=" + year + ", points=" + points + ", breakfast=" + breakfast + ", lunch=" + lunch + ", dinner=" + dinner + ", dailys=" + dailys + '}';
    }

    // -----------------------------------------
    // Copyable Implementation
    // (Support for REST web layer)
    // -----------------------------------------
    /**
     * Return a copy of the current object
     */
    @Override
    public FoodMonthly copy() {
        FoodMonthly userProfile = new FoodMonthly();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(FoodMonthly userProfile) {
        userProfile.setBreakfast(this.getBreakfast());
        userProfile.setLunch(this.getLunch());
        userProfile.setDinner(this.getDinner());
        userProfile.setMonth(this.getMonth());
        userProfile.setPoints(this.getPoints());
        userProfile.setUser(this.getUser());
        userProfile.setYear(this.getYear());
    }
}