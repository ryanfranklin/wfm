package com.ryanfranklin.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.ryanfranklin.employee.model.Employee;
import com.ryanfranklin.employee.model.audit.Audit;
import com.ryanfranklin.employee.model.audit.AuditAction;
import com.ryanfranklin.employee.model.audit.AuditEntity;
import com.ryanfranklin.employee.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

  private static long EMPLOYEE_ID = 123456;
  private static String EMPLOYEE_FIRST_NAME = "ryan";
  private static String EMPLOYEE_LAST_NAME = "franklin";
  private static String EMPLOYEE_EMAIL = "rfranklin1998@gmail.com";

  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private KafkaTemplate<String, Audit> kafkaTemplate;

  @InjectMocks
  private EmployeeService employeeService;


  private Employee employee;
  private Audit audit;

  @Before
  public void setup() {
    employee = new Employee();
    employee.setId(EMPLOYEE_ID);
    employee.setFirstName(EMPLOYEE_FIRST_NAME);
    employee.setLastName(EMPLOYEE_LAST_NAME);
    employee.setEmail(EMPLOYEE_EMAIL);

    audit = new Audit(
        10000,
        AuditEntity.EMPLOYEE,
        AuditAction.CREATE,
        10000
    );
  }


  @Test
  public void getEmployeeByIdSuccess() {
    given(employeeRepository.findOne(employee.getId())).willReturn(employee);

    Employee result = employeeService.getEmployeeById(employee.getId());

    assertThat(result).isEqualTo(employee);
  }

  @Test
  public void updateEmployeeSuccess() {
    given(employeeRepository.findOne(employee.getId())).willReturn(employee);
    given(employeeRepository.save(employee)).willReturn(employee);
    given(kafkaTemplate.send("audit", "eventId", audit)).willReturn(null);

    Employee result = employeeService.updateEmployee(employee.getId(), employee);

    assertThat(result).isEqualTo(employee);
  }

  @Test
  public void createEmployee() {
    given(employeeRepository.save(employee)).willReturn(employee);
    given(kafkaTemplate.send("audit", "eventId", audit)).willReturn(null);

    Employee result = employeeService.createEmployee(employee);

    assertThat(result).isEqualTo(employee);
  }

  @Test
  public void deleteEmployeeById() {
    given(employeeRepository.findOne(employee.getId())).willReturn(employee);
    doNothing().when(employeeRepository).delete(employee.getId());

    try {
      employeeService.deleteEmployeeById(employee.getId());
    } catch (Exception e) {
      fail("Should not have thrown any exception");
    }
  }

}