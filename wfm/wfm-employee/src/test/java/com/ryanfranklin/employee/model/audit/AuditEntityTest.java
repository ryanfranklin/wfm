package com.ryanfranklin.employee.model.audit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuditEntityTest {

  private static String AUDIT_ENTITY_STRING = "employee";

  @Test
  public void getEntityString() {

    assertEquals(AuditEntity.EMPLOYEE.getEntityString(), AUDIT_ENTITY_STRING);
  }
}