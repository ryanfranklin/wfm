package com.ryanfranklin.employee.model.audit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Represents audit information of supported entities.
 */
public class Audit {

  private long entityId;
  @NotNull
  @Size(min = 1)
  private AuditEntity entity;
  @NotNull
  private AuditAction auditAction;
  private long updatedEpochMilli;

  /**
   * Constructs an audit object.
   *
   * @param entityId the identifier of the entity being audited
   * @param entity the entity being audited
   * @param auditAction the action on the entity that spurred the audit
   * @param updatedEpochMilli the time stamp that the audit occurred in epoch milliseconds
   */
  public Audit(long entityId, AuditEntity entity, AuditAction auditAction, long updatedEpochMilli) {
    this.entityId = entityId;
    this.entity = entity;
    this.auditAction = auditAction;
    this.updatedEpochMilli = updatedEpochMilli;
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
  public AuditEntity getEntity() {
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
