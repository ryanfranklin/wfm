package com.ryanfranklin.employee.model.audit;

import static org.apache.commons.lang.Validate.notNull;

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
  private AuditAction action;
  private long updatedEpochMilli;

  /**
   * Constructs an audit object.
   *
   * @param entityId the identifier of the entity being audited
   * @param entity the entity being audited
   * @param action the action on the entity that spurred the audit
   * @param updatedEpochMilli the time stamp that the audit occurred in epoch milliseconds
   */
  public Audit(long entityId, AuditEntity entity, AuditAction action, long updatedEpochMilli) {
    notNull(entity);
    notNull(action);

    this.entityId = entityId;
    this.entity = entity;
    this.action = action;
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
   * @return the action
   */
  public AuditAction getAction() {
    return action;
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
