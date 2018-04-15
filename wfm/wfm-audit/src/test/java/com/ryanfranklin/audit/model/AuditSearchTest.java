package com.ryanfranklin.audit.model;

import static org.junit.Assert.*;

import java.time.Instant;
import org.junit.Test;

public class AuditSearchTest {

  private static Long ENTITY_ID = 123456l;
  private static AuditEntity ENTITY = AuditEntity.EMPLOYEE;
  private static AuditAction ACTION = AuditAction.CREATE;
  private static Long UPDATE_FROM_EPOCH_MILLI = 0l;
  private static Long UPDATE_TO_EPOCH_MILLI = Instant.now().toEpochMilli();

  @Test
  public void auditSearchSettersGettersSuccess() {
    AuditSearch s = new AuditSearch();
    s.setEntityId(ENTITY_ID);
    s.setEntity(ENTITY);
    s.setAction(ACTION);
    s.setUpdatedFromEpochMilli(UPDATE_FROM_EPOCH_MILLI);
    s.setUpdatedToEpochMilli(UPDATE_TO_EPOCH_MILLI);

    assertEquals(s.getEntityId(), ENTITY_ID);
    assertEquals(s.getEntity(), ENTITY);
    assertEquals(s.getAction(), ACTION);
    assertEquals(s.getUpdatedFromEpochMilli(), UPDATE_FROM_EPOCH_MILLI);
    assertEquals(s.getUpdatedToEpochMilli(), UPDATE_TO_EPOCH_MILLI);
  }

  @Test
  public void getAuditSearchCombinationNone() {
    AuditSearch s = new AuditSearch();

    assertEquals(AuditSearchCombination.NONE, s.getCombination());
  }

  @Test
  public void getAuditSearchCombinationEntity() {
    AuditSearch s = new AuditSearch();
    s.setEntity(ENTITY);

    assertEquals(AuditSearchCombination.ENTITY, s.getCombination());
  }

  @Test
  public void getAuditSearchCombinationAction() {
    AuditSearch s = new AuditSearch();
    s.setAction(ACTION);

    assertEquals(AuditSearchCombination.ACTION, s.getCombination());
  }

  @Test
  public void getAuditSearchCombinationEntityAction() {
    AuditSearch s = new AuditSearch();
    s.setEntity(ENTITY);
    s.setAction(ACTION);

    assertEquals(AuditSearchCombination.ENTITY_ACTION, s.getCombination());
  }


}
