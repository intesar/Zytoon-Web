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
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.envers.*;

@Entity
@Table(name = "user_network")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name = "UserNetwork.findByUserBasicId", query = "SELECT u FROM UserNetwork u "
        + "WHERE u.user = (SELECT ub.id FROM UserBasic ub WHERE ub.userProfileId = (SELECT up.id FROM UserProfile up WHERE up.username = :username )) "),
    @NamedQuery(name = "UserNetwork.findByUserAndFriend", query = "SELECT u FROM UserNetwork u "
        + "WHERE u.user = :user AND u.friend.id = :friend")
})
public class UserNetwork implements Identifiable<Integer>, Serializable, Copyable<UserNetwork> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UserNetwork.class);
    // Raw attributes
    private Integer id; // pk
    private Integer user;
    private UserBasic friend;
    private String type;
    private Boolean active;

    // ---------------------------
    // Constructors
    // ---------------------------
    public UserNetwork() {
    }

    public UserNetwork(Integer primaryKey) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Cache(usage = NONSTRICT_READ_WRITE)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id")
    public UserBasic getFriend() {
        return friend;
    }

    public void setFriend(UserBasic friend) {
        this.friend = friend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="user_")
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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
        final UserNetwork other = (UserNetwork) obj;
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
    public UserNetwork copy() {
        UserNetwork userProfile = new UserNetwork();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(UserNetwork userProfile) {
    }
}