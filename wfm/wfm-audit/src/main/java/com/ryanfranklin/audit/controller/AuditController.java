package com.ryanfranklin.audit.controller;

import com.ryanfranklin.audit.exception.BadRequestException;
import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.model.AuditAction;
import com.ryanfranklin.audit.model.AuditSearch;
import com.ryanfranklin.audit.model.AuditEntity;
import com.ryanfranklin.audit.service.AuditService;
import java.util.List;
import java.util.Map;
import javassist.tools.web.BadHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class AuditController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private static String ENTITY = "entity";
  private static String ACTION = "action";

  private AuditService auditService;

  @Autowired
  private AuditController(AuditService auditService) {
    super();
    this.auditService = auditService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<Audit>> get(@RequestParam Map<String, String> queryParameters) {
    logger.debug("Processing GET reuest for audits");

    AuditSearch auditSearch = getAuditSearch(queryParameters);

    List<Audit> audit = auditService.getAudits(auditSearch);
    return new ResponseEntity<>(audit, HttpStatus.OK);
  }

  //TODO: getAllAuditableEntities
  //TODO: getAllAuditableActions

  private AuditSearch getAuditSearch(Map<String, String> queryParameters) {
    //Extract supported queryParams
    String entityString = queryParameters.get(ENTITY);
    String actionString = queryParameters.get(ACTION);
    AuditEntity entity = null;
    AuditAction action = null;
    // TODO: Support other AuditSearch conditions

    if (entityString != null) {
      entity = AuditEntity.getAuditEntity(entityString);
      if (entity == null) {
        logger.debug("The auditible entity given: {} is not supported.", entityString);
        throw new BadRequestException();//TODO: Return descriptive message with response.
      }
    }

    if (actionString != null) {
      action = AuditAction.getAuditAction(actionString);
      if (action == null) {
        logger.debug("The auditible action given: {} is not supported.", actionString);
        throw new BadRequestException();//TODO: Return descriptive message with response.
      }
    }

    AuditSearch auditSearch = new AuditSearch();
    auditSearch.setAuditEntity(entity);
    auditSearch.setAuditAction(action);
    return auditSearch;
  }
}