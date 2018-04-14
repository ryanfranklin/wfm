package com.ryanfranklin.audit.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents audit information of supported entities.
 */
@Entity
@Table(name = "Audit")
public class Audit {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long entityId;
    @Column(nullable = false)
    @NotNull
    @Size(min = 1)
    private String entity;
    @Column(nullable = false)
    @NotNull
    private AuditAction auditAction;
    @Column(nullable = false)
    private long updatedEpochMilli;

    /**
     * Constructs an audit object.
     * @param id the identifier of this audit instance
     * @param entityId the identifier of the entity being audited
     * @param entity the entity being audited
     * @param auditAction the action on the entity that spurred the audit
     * @param updatedEpochMilli the time stamp that the audit occurred in epoch milliseconds
     */
    public Audit(long id, long entityId, String entity, AuditAction auditAction, long updatedEpochMilli) {
        this.id = id;
        this.entityId = entityId;
        this.entity = entity;
        this.auditAction = auditAction;
        this.updatedEpochMilli = updatedEpochMilli;
    }

    /**
     * Gets id
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the of id of the Audit
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets entityId
     *
     * @return the entityId
     */
    public long getEntityId() {
        return entityId;
    }

    /**
     * Sets entityId
     *
     * @param entityId the of entityId of the Audit
     */
    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    /**
     * Gets entity
     *
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Sets entity
     *
     * @param entity the of entity of the Audit
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * Gets auditAction
     *
     * @return the auditAction
     */
    public AuditAction getAuditAction() {
        return auditAction;
    }

    /**
     * Sets auditAction
     *
     * @param auditAction the of auditAction of the Audit
     */
    public void setAuditAction(AuditAction auditAction) {
        this.auditAction = auditAction;
    }

    /**
     * Sets updatedEpochMilli
     *
     * @param updatedEpochMilli the of updatedEpochMilli of the Audit
     */
    public void setUpdatedEpochMilli(long updatedEpochMilli) {
        this.updatedEpochMilli = updatedEpochMilli;
    }

    /**
     * Gets updatedEpochMilli
     *
     * @return the updatedEpochMilli
     */
    public long getUpdatedEpochMilli() {
        return updatedEpochMilli;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
