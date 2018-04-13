package com.ryanfranklin.audit.events;

import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class AuditKafkaListener {

	private final Logger log = LoggerFactory.getLogger(AuditKafkaListener.class);

	private AuditService auditService;

	public AuditKafkaListener(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	@KafkaListener(topics = "audit")
	public void auditListener(Audit audit, Acknowledgment acknowledgment) {
		log.info("Revceived audit  " + audit.getId());
		auditService.audit(audit);
		acknowledgment.acknowledge();
	}

}
