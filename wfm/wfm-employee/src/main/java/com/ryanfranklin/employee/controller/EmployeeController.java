package com.ryanfranklin.employee.controller;

import com.ryanfranklin.employee.model.Employee;
import com.ryanfranklin.employee.repository.EmployeeRepository;
import com.ryanfranklin.employee.service.EmployeeService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
class EmployeeController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private EmployeeService employeeService;

  @Autowired
  private EmployeeController(EmployeeService employeeService) {
    super();
    this.employeeService = employeeService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Employee> get(@PathVariable("id") long id) {
    logger.debug("GET employee by id: {}", id);
    Employee employee = employeeService.getEmployeeById(id);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> post(@RequestBody @Valid Employee employee) {
    Employee returnedEmployee = employeeService.createEmployee(employee);
    return new ResponseEntity<>(returnedEmployee, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> put(@PathVariable long id,
      @RequestBody @Valid Employee employee) {
    Employee returnedEmployee = employeeService.updateEmployee(id, employee);
    return new ResponseEntity<>(returnedEmployee, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Employee> delete(@PathVariable("id") long id) {
    employeeService.deleteEmployeeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
