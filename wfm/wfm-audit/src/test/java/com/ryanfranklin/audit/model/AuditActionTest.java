package com.ryanfranklin.audit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuditActionTest {

  private static String AUDIT_ACTION_CREATE_STRING = "create";

  @Test
  public void auditActionToStringSuccess() {

    assertEquals(AuditAction.CREATE.toString(), AUDIT_ACTION_CREATE_STRING);
  }

  @Test
  public void getAuditActionSuccess() {

    assertEquals(AuditAction.getAuditAction(AUDIT_ACTION_CREATE_STRING), AuditAction.CREATE);
  }
}
