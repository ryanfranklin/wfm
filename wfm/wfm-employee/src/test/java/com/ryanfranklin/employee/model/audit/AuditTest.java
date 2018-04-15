package com.ryanfranklin.employee.model.audit;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import org.junit.Test;

public class AuditTest {

  private static long ENTITY_ID = 123456;
  private static AuditEntity ENTITY = AuditEntity.EMPLOYEE;
  private static AuditAction ACTION = AuditAction.CREATE;
  private static long UPDATE_EPOCH_MILLI = Instant.now().toEpochMilli();

  @Test
  public void auditConstuctorGettersSuccess() {
    Audit audit = new Audit(
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