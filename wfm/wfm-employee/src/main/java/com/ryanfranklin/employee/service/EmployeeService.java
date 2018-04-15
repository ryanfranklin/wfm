package com.ryanfranklin.employee.service;

import com.ryanfranklin.employee.exception.NotFoundException;
import com.ryanfranklin.employee.model.Employee;
import com.ryanfranklin.employee.model.audit.Audit;
import com.ryanfranklin.employee.model.audit.AuditAction;
import com.ryanfranklin.employee.model.audit.AuditEntity;
import com.ryanfranklin.employee.repository.EmployeeRepository;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private EmployeeRepository employeeRepository;

  private KafkaTemplate<String, Audit> kafkaTemplate;

  @Autowired
  private EmployeeService(EmployeeRepository employeeRepository,
      KafkaTemplate<String, Audit> kafkaTemplate) {
    this.employeeRepository = employeeRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  public Employee updateEmployee(long id, Employee employee) {
    Employee persistedEmployee = employeeRepository.findOne(id);
    if (persistedEmployee == null) {
      logger.debug("Could not find the employee by id: {}", id);
      throw new NotFoundException();
    }
    persistedEmployee.setFirstName(employee.getFirstName());
    persistedEmployee.setLastName(employee.getLastName());
    persistedEmployee.setEmail(employee.getEmail());
    persistedEmployee.setUpdatedEpochMilli(Instant.now().toEpochMilli());
    Employee result = employeeRepository.save(persistedEmployee);
    createAuditEvent(result.getId(), AuditAction.UPDATE);
    return result;
  }

  public Employee createEmployee(Employee employee) {
    employee.setUpdatedEpochMilli(Instant.now().toEpochMilli());
    Employee result = employeeRepository.save(employee);
    createAuditEvent(result.getId(), AuditAction.CREATE);
    return result;
  }

  public void deleteEmployeeById(long id) {
    try {
      employeeRepository.delete(id);
    } catch (EmptyResultDataAccessException e) {
      logger.debug("There was an error deleting an employee.", e);
      throw new NotFoundException();
    }
    createAuditEvent(id, AuditAction.DELETE);
  }

  public Employee getEmployeeById(long id) {
    Employee employee = employeeRepository.findOne(id);
    if (employee == null) {
      logger.debug("Could not find the employee by id: {}", id);
      throw new NotFoundException();
    }
    return employee;
  }

  private void createAuditEvent(long employeeId, AuditAction action) {

    Audit audit = new Audit(
        employeeId,
        AuditEntity.EMPLOYEE,
        action,
        Instant.now().toEpochMilli()
    );

    String eventId = audit.getEntity() + "_" + audit.getEntityId() + "_" + audit.getUpdatedEpochMilli();
    logger.debug("Sending audit event with id: {}.", eventId);

    kafkaTemplate.send("audit", eventId, audit);
  }

}
