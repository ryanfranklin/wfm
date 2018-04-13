package com.ryanfranklin.employee.model.audit;

import java.util.Objects;

public class Audit {

    private String id;
    private long entityId;
    private String entity;
    private AuditAction auditAction;
    private long updatedEpochMilli;

    public Audit(String auditId, long entityId, String entity, AuditAction auditAction, long updatedEpochMilli) {
        this.id = auditId;
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
    public String getId() {
        return id;
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
     * Gets entity
     *
     * @return the entity
     */
    public String getEntity() {
        return entity;
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
     * Gets updatedEpochMilli
     *
     * @return the updatedEpochMilli
     */
    public long getUpdatedEpochMilli() {
        return updatedEpochMilli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit that = (Audit) o;
        return entityId == that.entityId &&
                updatedEpochMilli == that.updatedEpochMilli &&
                Objects.equals(id, that.id) &&
                Objects.equals(entity, that.entity) &&
                auditAction == that.auditAction;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, entityId, entity, auditAction, updatedEpochMilli);
    }
}
