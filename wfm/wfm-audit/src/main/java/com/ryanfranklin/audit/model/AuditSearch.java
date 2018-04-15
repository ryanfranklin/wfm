package com.ryanfranklin.audit.model;

import com.ryanfranklin.audit.exception.BadRequestException;
import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class to hold searchable fields when reading audits
 */
public class AuditSearch {

  private static Logger logger = LoggerFactory.getLogger(AuditSearch.class);

  private Long entityId;
  private AuditEntity entity;
  private AuditAction action;
  private Long updatedFromEpochMilli;
  private Long updatedToEpochMilli;

  /**
   * Gets entityId
   *
   * @return the entityId or null if the field hasn't been set
   */
  public Long getEntityId() {
    return entityId;
  }

  /**
   * Sets entityId
   *
   * @param entityId the of entityId of the AuditSearch
   */
  public void setEntityId(Long entityId) {
    this.entityId = entityId;
  }

  /**
   * Gets entity
   *
   * @return the entity or null if the field hasn't been set
   */
  public AuditEntity getEntity() {
    return entity;
  }

  /**
   * Sets entity
   *
   * @param entity the of entity of the AuditSearch
   */
  public void setEntity(AuditEntity entity) {
    this.entity = entity;
  }

  /**
   * Gets action
   *
   * @return the action or null if the field hasn't been set
   */
  public AuditAction getAction() {
    return action;
  }

  /**
   * Sets action
   *
   * @param action the of action of the AuditSearch
   */
  public void setAction(AuditAction action) {
    this.action = action;
  }

  /**
   * Gets updatedFromEpochMilli
   *
   * @return the updatedFromEpochMilli  or null if the field hasn't been set
   */
  public Long getUpdatedFromEpochMilli() {
    return updatedFromEpochMilli;
  }

  /**
   * Sets updatedFromEpochMilli
   *
   * @param updatedFromEpochMilli the of updatedFromEpochMilli of the AuditSearch
   */
  public void setUpdatedFromEpochMilli(Long updatedFromEpochMilli) {
    this.updatedFromEpochMilli = updatedFromEpochMilli;
  }

  /**
   * Gets updatedToEpochMilli
   *
   * @return the updatedToEpochMilli or null if the field hasn't been set
   */
  public Long getUpdatedToEpochMilli() {
    return updatedToEpochMilli;
  }

  /**
   * Sets updatedToEpochMilli
   *
   * @param updatedToEpochMilli the of updatedToEpochMilli of the AuditSearch
   */
  public void setUpdatedToEpochMilli(Long updatedToEpochMilli) {
    this.updatedToEpochMilli = updatedToEpochMilli;
  }


  public AuditSearchCombination getCombination() {
    StringJoiner joiner = new StringJoiner("_");
    if (getEntityId() != null) {
      joiner.add(AuditSearchValues.ID.name());
    }
    if (getEntity() != null) {
      joiner.add(AuditSearchValues.ENTITY.name());
    }
    if (getAction() != null) {
      joiner.add(AuditSearchValues.ACTION.name());
    }
    if (getUpdatedFromEpochMilli() != null) {
      joiner.add(AuditSearchValues.FROM.name());
    }
    if (getUpdatedToEpochMilli() != null) {
      joiner.add(AuditSearchValues.TO.name());
    }
    String searchCombo = joiner.toString();
    logger.debug("The audit search combination is {}.", searchCombo);

    if (searchCombo.isEmpty()) {
      return AuditSearchCombination.NONE;
    }

    try {
      return AuditSearchCombination.valueOf(searchCombo);
    } catch (IllegalArgumentException e) {
      logger.debug("The audit search combination is not currently supported.");
      //TODO: Return descriptive message with response.
      throw new BadRequestException();
    }
  }

}
