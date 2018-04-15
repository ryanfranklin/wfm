package com.ryanfranklin.audit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
  private AuditEntity entity;
  @Column(nullable = false)
  @NotNull
  private AuditAction action;
  @Column(nullable = false)
  private long updatedEpochMilli;

  //For Jackson
  public Audit(){}
  /**
   * Constructs an audit object.
   *
   * @param entityId the identifier of the entity being audited
   * @param entity the entity being audited
   * @param action the action on the entity that spurred the audit
   * @param updatedEpochMilli the time stamp that the audit occurred in epoch milliseconds
   */
  public Audit(long entityId, AuditEntity entity, AuditAction action,
      long updatedEpochMilli) {
    this.entityId = entityId;
    this.entity = entity;
    this.action = action;
    this.updatedEpochMilli = updatedEpochMilli;
  }

  /**
   * Gets id
   *
   * @return the id or null if an id hasn't been generated yet
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
  public AuditEntity getEntity() {
    return entity;
  }

  /**
   * Sets entity
   *
   * @param entity the of entity of the Audit
   */
  public void setEntity(AuditEntity entity) {
    this.entity = entity;
  }

  /**
   * Gets action
   *
   * @return the action
   */
  public AuditAction getAction() {
    return action;
  }

  /**
   * Sets action
   *
   * @param action the of action of the Audit
   */
  public void setAction(AuditAction action) {
    this.action = action;
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
