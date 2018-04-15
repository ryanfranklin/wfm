package com.ryanfranklin.audit.service;

import com.ryanfranklin.audit.exception.BadRequestException;
import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.model.AuditSearch;
import com.ryanfranklin.audit.model.AuditSearchCombination;
import com.ryanfranklin.audit.repository.AuditRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private AuditRepository auditRepository;

  @Autowired
  public AuditService(AuditRepository auditRepository) {
    super();
    this.auditRepository = auditRepository;
  }

  @Transactional
  public void saveAudit(Audit audit) {
    Audit savedAudit = auditRepository.save(audit);
    log.trace("Saved audit: {} for type: {}.", savedAudit.getId(), savedAudit.getEntity());

  }

  public List<Audit> getAudits(AuditSearch auditSearch) {

    AuditSearchCombination searchCombo = auditSearch.getCombination();

    switch (searchCombo) {
      case NONE:
        return auditRepository.findAll();

      case ENTITY:
        return auditRepository.findByEntity(auditSearch.getEntity());

      case ACTION:
        return auditRepository.findByAction(auditSearch.getAction());

      case ENTITY_ACTION:
        return auditRepository.findByEntityAndAction(auditSearch.getEntity(), auditSearch.getAction());

      default:
        log.debug("Audit search combination {} not supported.", searchCombo);
        throw new BadRequestException(); //TODO: Return descriptive message with response.
    }
  }
}
