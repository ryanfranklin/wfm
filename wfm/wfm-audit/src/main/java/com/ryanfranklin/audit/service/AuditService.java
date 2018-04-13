package com.ryanfranklin.audit.service;

import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {
    public void audit(Audit audit) {

    }

    private final Logger log = LoggerFactory.getLogger(AuditService.class);

    private AuditRepository auditRepository;

    @Autowired
    public AuditService(AuditRepository auditRepository) {
        super();
        this.auditRepository = auditRepository;
    }

    @Transactional
    public void saveAudit(Audit audit) {
        //TODO: IMPLEMENT
    }
}
