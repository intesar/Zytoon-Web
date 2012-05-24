package com.bizintelapps.zytoon.domain;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
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
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.envers.*;

@Entity
@Table(name = "messages")
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name="Message.findUnread", query="SELECT m FROM Message m WHERE m.receiverDeleted = false AND m.active = true AND m.type like 'Friend request' AND m.receiver.id = :id ")
})
public class Message implements Identifiable<Integer>, Serializable, Copyable<Message> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Message.class);
    // Raw attributes
    private Integer id; // pk
    private String subject;
    private String body;
    private Date sendDate;
    private String type;
    private UserBasic sender;
    private UserBasic receiver;
    private Integer parent;
    private Boolean receiverRead = Boolean.FALSE;
    private Boolean receiverDeleted = Boolean.FALSE;
    private Boolean active;

    // ---------------------------
    // Constructors
    // ---------------------------
    public Message() {
    }

    public Message(Integer primaryKey) {
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Boolean getReceiverRead() {
        return receiverRead;
    }

    public void setReceiverRead(Boolean receiverRead) {
        this.receiverRead = receiverRead;
    }

    @Cache(usage = NONSTRICT_READ_WRITE)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    public UserBasic getReceiver() {
        return receiver;
    }

    public void setReceiver(UserBasic receiver) {
        this.receiver = receiver;
    }

    public Boolean getReceiverDeleted() {
        return receiverDeleted;
    }

    public void setReceiverDeleted(Boolean receiverDeleted) {
        this.receiverDeleted = receiverDeleted;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Cache(usage = NONSTRICT_READ_WRITE)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    public UserBasic getSender() {
        return sender;
    }

    public void setSender(UserBasic sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "type_")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        final Message other = (Message) obj;
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
    public Message copy() {
        Message userProfile = new Message();
        copyTo(userProfile);
        return userProfile;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(Message userProfile) {
    }
}