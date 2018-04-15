package com.ryanfranklin.audit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuditEntityTest {

  private static String AUDIT_ENTITY_STRING = "employee";

  @Test
  public void auditEntityToStringSuccess() {

    assertEquals(AuditEntity.EMPLOYEE.toString(), AUDIT_ENTITY_STRING);
  }

  @Test
  public void getAuditEntitySuccess() {

    assertEquals(AuditEntity.getAuditEntity(AUDIT_ENTITY_STRING), AuditEntity.EMPLOYEE);
  }
}