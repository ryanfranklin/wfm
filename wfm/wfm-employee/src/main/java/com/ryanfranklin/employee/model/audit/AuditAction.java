package com.ryanfranklin.employee.model.audit;

/**
 * Represents different actions made to an entity that could spur an audit.
 */
public enum AuditAction {

  CREATE("create"),
  UPDATE("update"),
  DELETE("delete");

  /** The string identifier for the audit action type.  */
  private String action;

  private AuditAction(String action) {
    this.action = action;
  }

  /**
   * Gets the string identifier for the audit action type.
   *
   * @return the string identifier of this action enum
   */
  @Override
  public String toString() {
    return action;
  }

  /**
   * Gets the audit action by the name given.
   * @param name the string representation of the enum
   * @return the AuditAction enum or null if it is not found by the name
   */
  public static AuditAction getAuditAction(String name)  {
    for (AuditAction action : AuditAction.values()) {
      if (action.toString().equals(name)) {
        return action;
      }
    }
    return null;
  }
}
