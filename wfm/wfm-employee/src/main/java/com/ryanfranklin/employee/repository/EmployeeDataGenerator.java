package com.ryanfranklin.employee.repository;

import com.ryanfranklin.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EmployeeDataGenerator {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeDataGenerator(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@PostConstruct
	public void generateTestData() {
		employeeRepository.save(new Employee("Ryan", "Franklin", "ryan.franklin@gmail.com"));
		employeeRepository.save(new Employee("Fyan", "Ranklin", "fyan.ranklin@gmail.com"));
		employeeRepository.save(new Employee("Nayr", "Nilknarf", "nayr.nilknarf@gmail.com"));
	}

}
