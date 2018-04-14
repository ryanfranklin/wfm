package com.ryanfranklin.employee.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import org.junit.Test;

public class EmployeeTest {

  private static long EMPLOYEE_ID = 123456;
  private static String EMPLOYEE_FIRST_NAME = "ryan";
  private static String EMPLOYEE_LAST_NAME = "franklin";
  private static String EMPLOYEE_EMAIL = "rfranklin1998@gmail.com";
  private static long UPDATE_EPOCH_MILLI = Instant.now().toEpochMilli();

  @Test
  public void employeeSettersGettersSuccess() {
    Employee employee = new Employee();
    employee.setId(EMPLOYEE_ID);
    employee.setFirstName(EMPLOYEE_FIRST_NAME);
    employee.setLastName(EMPLOYEE_LAST_NAME);
    employee.setEmail(EMPLOYEE_EMAIL);
    employee.setUpdatedEpochMilli(UPDATE_EPOCH_MILLI);

    assertEquals(employee.getId(), EMPLOYEE_ID);
    assertEquals(employee.getFirstName(), EMPLOYEE_FIRST_NAME);
    assertEquals(employee.getLastName(), EMPLOYEE_LAST_NAME);
    assertEquals(employee.getEmail(), EMPLOYEE_EMAIL);
    assertEquals(employee.getUpdatedEpochMilli(), UPDATE_EPOCH_MILLI);
  }

  @Test
  public void employeeConstructorGettersSuccess() {
    Employee employee = new Employee(
        EMPLOYEE_FIRST_NAME,
        EMPLOYEE_LAST_NAME,
        EMPLOYEE_EMAIL
    );

    assertEquals(employee.getId(), 0l);
    assertEquals(employee.getFirstName(), EMPLOYEE_FIRST_NAME);
    assertEquals(employee.getLastName(), EMPLOYEE_LAST_NAME);
    assertEquals(employee.getEmail(), EMPLOYEE_EMAIL);
    assertNotNull(employee.getUpdatedEpochMilli());
  }
}