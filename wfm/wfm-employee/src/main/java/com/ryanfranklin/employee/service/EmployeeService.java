package com.ryanfranklin.employee.service;

import com.ryanfranklin.employee.exception.NotFoundException;
import com.ryanfranklin.employee.repository.EmployeeRepository;
import com.ryanfranklin.employee.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmployeeRepository employeeRepository;

	private KafkaTemplate<String, Employee> kafkaTemplate;

	@Autowired
	private EmployeeService(EmployeeRepository employeeRepository, KafkaTemplate<String, Employee> kafkaTemplate) {
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
		// TODO: SEND AUDIT EVENT
		return result;
	}

	public Employee createEmployee(Employee employee) {
		employee.setUpdatedEpochMilli(Instant.now().toEpochMilli());
		Employee result = employeeRepository.save(employee);
		// TODO: SEND AUDIT EVENT
		return result;
	}

	public void deleteEmployeeById(long id) {
		try {
			employeeRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.debug("There was an error deleting an employee.", e);
			throw new NotFoundException();
		}
		// TODO: SEND AUDIT EVENT
	}

	public Employee getEmployeeById(long id) {
		Employee employee = employeeRepository.findOne(id);
		if (employee == null) {
			logger.debug("Could not find the employee by id: {}", id);
			throw new NotFoundException();
		}
		return employee;
	}

}
