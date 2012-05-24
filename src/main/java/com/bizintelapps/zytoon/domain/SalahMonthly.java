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
@Table(name = "salah_monthly")
//history_salah
@Audited
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name = "SalahMonthly.findDailyByUserAndDate",
    query = "SELECT h.dailys FROM SalahMonthly h WHERE h.user.id = :userId AND h.month = :month AND h.year = :year"),
    @NamedQuery(name = "SalahMonthly.findByUserAndDate",
    query = "SELECT h FROM SalahMonthly h WHERE h.user.id = :userId AND h.month = :month AND h.year = :year"),
    @NamedQuery(name = "SalahMonthly.findAllByUserAndDate",
    query = "SELECT h FROM SalahMonthly h WHERE (h.user.id in (SELECT u.friend.id FROM UserNetwork u WHERE u.user = :userId AND u.active = true) OR h.user.id = :userId) "
        + "AND h.month = :month AND h.year = :year ORDER BY h.user.name")
})
public class SalahMonthly implements Identifiable<Integer>, Serializable, Copyable<SalahMonthly> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SalahMonthly.class);
    // Raw attributes
    private Integer id; // pk
    private UserBasic user;
    private Integer month;
    private Integer year;
    private Integer prayed;
    private Integer fajr;
    private Integer dhuhr;
    private Integer asr;
    private Integer magrib;
    private Integer ishaa;
    private List<SalahDaily> dailys;

    // ---------------------------
    // Constructors
    // ---------------------------
    public SalahMonthly() {
    }

    public SalahMonthly(Integer primaryKey) {
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

    public Integer getAsr() {
        return asr;
    }

    public void setAsr(Integer asr) {
        this.asr = asr;
    }

    public Integer getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(Integer dhuhr) {
        this.dhuhr = dhuhr;
    }

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getIshaa() {
        return ishaa;
    }

    public void setIshaa(Integer ishaa) {
        this.ishaa = ishaa;
    }

    public Integer getMagrib() {
        return magrib;
    }

    public void setMagrib(Integer magrib) {
        this.magrib = magrib;
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

    public Integer getPrayed() {
        return prayed;
    }

    public void setPrayed(Integer prayed) {
        this.prayed = prayed;
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
    public List<SalahDaily> getDailys() {
        return dailys;
    }

    public void setDailys(List<SalahDaily> dailys) {
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
        final SalahMonthly other = (SalahMonthly) obj;
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
        return "SalahMonthly{" + "id=" + id + ", user=" + user + ", month=" + month + ", year=" + year + ", prayed=" + prayed + ", fajr=" + fajr + ", dhuhr=" + dhuhr + ", asr=" + asr + ", magrib=" + magrib + ", ishaa=" + ishaa + '}';
    }

    // -----------------------------------------
    // Copyable Implementation
    // (Support for REST web layer)
    // -----------------------------------------
    /**
     * Return a copy of the current object
     */
    @Override
    public SalahMonthly copy() {
        SalahMonthly userProfile = new SalahMonthly();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(SalahMonthly userProfile) {
        userProfile.setAsr(this.getAsr());
        userProfile.setDhuhr(this.getDhuhr());
        userProfile.setFajr(this.getFajr());
        userProfile.setIshaa(this.getIshaa());
        userProfile.setMagrib(this.getMagrib());
        userProfile.setMonth(this.getMonth());
        userProfile.setPrayed(this.getPrayed());
        userProfile.setUser(this.getUser());
        userProfile.setYear(this.getYear());
    }
}