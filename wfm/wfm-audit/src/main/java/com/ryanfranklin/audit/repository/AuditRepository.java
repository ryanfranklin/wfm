package com.ryanfranklin.audit.repository;

import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.model.AuditAction;
import com.ryanfranklin.audit.model.AuditEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, Long> {

  List<Audit> findAll();
  List<Audit> findByEntity(AuditEntity entity);
  List<Audit> findByAction(AuditAction action);
  List<Audit> findByEntityAndAction(AuditEntity auditEntity, AuditAction auditAction);
}
