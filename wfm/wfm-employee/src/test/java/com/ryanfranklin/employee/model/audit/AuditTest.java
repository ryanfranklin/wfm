package com.ryanfranklin.employee.model.audit;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

public class AuditTest {

    private static long ENTITY_ID = 123456;
    private static String ENTITY = AuditEntity.EMPLOYEE.getEntityString();
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