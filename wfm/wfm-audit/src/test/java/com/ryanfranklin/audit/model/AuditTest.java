package com.ryanfranklin.audit.model;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import org.junit.Test;

public class AuditTest {

  private static long AUDIT_ID = 999;
  private static long ENTITY_ID = 123456;
  private static String ENTITY = AuditEntity.EMPLOYEE.getEntityString();
  private static AuditAction ACTION = AuditAction.CREATE;
  private static long UPDATE_EPOCH_MILLI = Instant.now().toEpochMilli();

  @Test
  public void auditConstuctorGettersSuccess() {
    Audit audit = new Audit(
        AUDIT_ID,
        ENTITY_ID,
        ENTITY,
        ACTION,
        UPDATE_EPOCH_MILLI
    );

    assertEquals(audit.getEntityId(), ENTITY_ID);
    assertEquals(audit.getEntity(), ENTITY);
    assertEquals(audit.getAuditAction(), ACTION);
    assertEquals(audit.getUpdatedEpochMilli(), UPDATE_EPOCH_MILLI);
  }
}