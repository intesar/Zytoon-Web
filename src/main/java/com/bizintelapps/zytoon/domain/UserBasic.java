package com.bizintelapps.zytoon.domain;

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
import org.hibernate.validator.constraints.Length;
import com.bizintelapps.zytoon.validation.JpaUnique;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "user_basic")
@Audited
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name = "UserBasic.findByUserProfileId", query = "SELECT u FROM UserBasic u WHERE u.userProfileId = :userProfileId"),
    @NamedQuery(name = "UserBasic.findByUsername", query = "SELECT u FROM UserBasic u WHERE u.userProfileId = "
        + "(SELECT up.id FROM UserProfile up WHERE up.username = :username )")
})
public class UserBasic implements Identifiable<Integer>, Serializable, Copyable<UserBasic> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UserBasic.class);
    // Raw attributes
    private Integer id; // pk
    private Integer userProfileId;
    private String name;
    private String nickname;
    private String city;
    private String country;

    // ---------------------------
    // Constructors
    // ---------------------------
    public UserBasic() {
    }

    public UserBasic(Integer primaryKey) {
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

    // -- [city] ------------------------
    @Length(max = 80)
    @Column(length = 80)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // -- [country] ------------------------
    @Length(max = 80)
    @Column(length = 80)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
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
        final UserBasic other = (UserBasic) obj;
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
    /**
     * Construct a readable string representation for this UserProfile instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("userProfile.id=[").append(getId()).append("]\n");
        result.append("userProfile.city=[").append(getCity()).append("]\n");
        result.append("userProfile.country=[").append(getCountry()).append("]\n");
        return result.toString();
    }

    // -----------------------------------------
    // Copyable Implementation
    // (Support for REST web layer)
    // -----------------------------------------
    /**
     * Return a copy of the current object
     */
    @Override
    public UserBasic copy() {
        UserBasic userProfile = new UserBasic();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(UserBasic userProfile) {
    }
}