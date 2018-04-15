package com.ryanfranklin.audit.repository;

import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.model.AuditAction;
import com.ryanfranklin.audit.model.AuditEntity;
import java.time.Instant;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class AuditDataGenerator {

  private static long SECONDS_PER_DAY = 86400;
  private static long DAYS_PER_WEEK = 7;
  private final AuditRepository auditRepository;

  @Autowired
  public AuditDataGenerator(AuditRepository auditRepository) {
    this.auditRepository = auditRepository;
  }

  @PostConstruct
  public void generateTestData() {
    long now = Instant.now().toEpochMilli();
    long yesterday = Instant.now().minusSeconds(SECONDS_PER_DAY).toEpochMilli();
    long lastWeek = Instant.now().minusSeconds(DAYS_PER_WEEK).toEpochMilli();

    auditRepository.save(new Audit(1, AuditEntity.EMPLOYEE, AuditAction.CREATE, now));
    auditRepository.save(new Audit(2, AuditEntity.EMPLOYEE, AuditAction.UPDATE, yesterday));
    auditRepository.save(new Audit(3, AuditEntity.EMPLOYEE, AuditAction.DELETE, lastWeek));
  }
}
