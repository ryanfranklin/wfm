package com.ryanfranklin.employee.model.audit;

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
  String getEntityString() {
    return entity;
  }
}
