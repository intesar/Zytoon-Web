/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/main/java/project/domain/Entity.e.vm.java
 */
package com.bizintelapps.zytoon.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.TemporalType.DATE;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import static com.bizintelapps.zytoon.util.ResourcesUtil.DATE_FORMAT_PATTERN;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.springframework.format.annotation.DateTimeFormat;
import com.bizintelapps.zytoon.validation.JpaUnique;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "program")
@Audited
@Cache(usage = NONSTRICT_READ_WRITE)
@JpaUnique
@NamedQueries({
    @NamedQuery(name = "Program.findByStatus1", query = "SELECT p FROM Program p WHERE p.isActive = true AND p.id NOT IN "
    + "(SELECT e.programId FROM UserEnrollment e WHERE e.userId IN "
    + "(SELECT u.id from UserProfile u WHERE u.username = :username) AND e.result IN ('p','In Progress') ) "
    + "ORDER BY p.startDate"),
    @NamedQuery(name = "Program.findByStatus2",
    query = "SELECT p FROM Program p WHERE p.isActive = true AND p.programStructure.id NOT IN "
    + " ( "
    + "   SELECT p1.programStructure.id FROM Program p1 WHERE id IN "
    + "    ( "
    + "	     SELECT u.program.id FROM UserEnrollment u WHERE u.user.id = "
    + "            (SELECT u1.id FROM UserProfile u1 WHERE u1.username = :username) "
    + "       AND u.result in (\'p\', \'In Progress\')       "
    + "    )    "
    + " ) "
    + "ORDER BY p.startDate "),
    @NamedQuery(name = "Program.findByStatus",
    query = "SELECT p FROM Program p WHERE p.isActive = true AND p.id NOT IN "
    + " ( SELECT u.program.id FROM UserEnrollment u WHERE u.user.id = "
    + "   (SELECT u1.id FROM UserProfile u1 WHERE u1.username = :username)) "
    + "ORDER BY p.programStructure.days, p.startDate")
})
public class Program implements Identifiable<Integer>, Serializable, Copyable<Program> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Program.class);
    // Raw attributes
    private Integer id; // pk
    private Date startDate;
    // active, expired
    private Boolean isActive;
    // Technical attributes for query by example
    private Integer programStructureId;
    // Many to one
    private ProgramStructure programStructure; // (programStructureId)
    private UserProgramHistory history;

    // ---------------------------
    // Constructors
    // ---------------------------
    public Program() {
    }

    public Program(Integer primaryKey) {
        this();
        setPrimaryKey(primaryKey);
    }

    // ---------------------------
    // Identifiable implementation
    // ---------------------------
    @Transient
    @XmlTransient
    public Integer getPrimaryKey() {
        return getId();
    }

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

    @Transient
    public String getFormatedStartDate() {
        String dt = "";
        if (startDate != null) {
            String PATTERN = "EEE, MMM d";
            DateFormat df = new SimpleDateFormat(PATTERN);
            dt = df.format(startDate);
        }
        return dt;
    }

    public void setFormatedStartDate(String dt) {
    }

    @Transient
    public String getEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_YEAR, this.programStructure.getDays() - 1);
        String dt = "";

        String PATTERN = "EEE, MMM d";
        DateFormat df = new SimpleDateFormat(PATTERN);
        dt = df.format(cal.getTime());

        return dt;
    }

    public void setEndDate(String et) {
    }

    @Transient
    public UserProgramHistory getHistory() {
        return history;
    }

    public void setHistory(UserProgramHistory history) {
        this.history = history;
    }

    // -- [programStructureId] ------------------------
    @Column(name = "program_structure_id", precision = 10, insertable = false, updatable = false)
    public Integer getProgramStructureId() {
        return programStructureId;
    }

    private void setProgramStructureId(Integer programStructureId) {
        this.programStructureId = programStructureId;
    }

    // -- [startDate] ------------------------
    @Column(name = "start_date", length = 10)
    @Temporal(DATE)
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // -- [isActive] ------------------------
    @Column(name = "is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    // --------------------------------------------------------------------
    // Many to One support
    // --------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Program.programStructureId ==> ProgramStructure.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @Cache(usage = NONSTRICT_READ_WRITE)
    @JoinColumn(name = "program_structure_id")
    @ManyToOne(cascade = PERSIST, fetch = FetchType.EAGER)
    public ProgramStructure getProgramStructure() {
        return programStructure;
    }

    /**
     * Set the programStructure without adding this Program instance on the passed programStructure
     * If you want to preserve referential integrity we recommend to use
     * instead the corresponding adder method provided by ProgramStructure
     */
    public void setProgramStructure(ProgramStructure programStructure) {
        this.programStructure = programStructure;

        // We set the foreign key property so it can be used by Hibernate search by Example facility.
        if (programStructure != null) {
            setProgramStructureId(programStructure.getId());
        } else {
            setProgramStructureId(null);
        }
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
        final Program other = (Program) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    // -----------------------------------------
    // toString
    // -----------------------------------------
    /**
     * Construct a readable string representation for this Program instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("program.id=[").append(getId()).append("]\n");
        result.append("program.programStructureId=[").append(getProgramStructureId()).append("]\n");
        result.append("program.startDate=[").append(getStartDate()).append("]\n");
        result.append("program.isActive=[").append(getIsActive()).append("]\n");
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
    public Program copy() {
        Program program = new Program();
        copyTo(program);
        return program;
    }

    /**
     * Copy the current properties to the given object
     */
    @Override
    public void copyTo(Program program) {
        program.setId(getId());
        //program.setProgramStructureId(getProgramStructureId());
        program.setStartDate(getStartDate());
        program.setIsActive(getIsActive());
        if (getProgramStructure() != null) {
            program.setProgramStructure(new ProgramStructure(getProgramStructure().getPrimaryKey()));
        }
    }
}
