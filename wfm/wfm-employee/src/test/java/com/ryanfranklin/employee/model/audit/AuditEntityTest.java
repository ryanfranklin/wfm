package com.ryanfranklin.employee.model.audit;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuditEntityTest {

    private static String AUDIT_ENTITY_STRING = "employee";

    @Test
    public void getEntityString() {

        assertEquals(AuditEntity.EMPLOYEE.getEntityString(), AUDIT_ENTITY_STRING);
    }
}