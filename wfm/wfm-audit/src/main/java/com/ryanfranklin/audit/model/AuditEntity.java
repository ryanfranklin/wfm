package com.ryanfranklin.audit.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum AuditEntity {

  EMPLOYEE("employee");

  /**
   * The string identifier for the audit entity type.
   */
  private String entity;

  private AuditEntity(String entity) {
    this.entity = entity;
  }

  /**
   * Gets the string identifier for the audit entity type.
   *
   * @return the string identifier of this entity enum
   */
  @Override
  public String toString() {
    return entity;
  }

  /**
   * Gets the audit entity by the name given.
   * @param name the string representation of the enum
   * @return the AuditEntity enum or null if it is not found by the name
   */
  public static AuditEntity getAuditEntity(String name)  {
    for (AuditEntity entity : AuditEntity.values()) {
      if (entity.toString().equals(name)) {
        return entity;
      }
    }
    return null;
  }

}
