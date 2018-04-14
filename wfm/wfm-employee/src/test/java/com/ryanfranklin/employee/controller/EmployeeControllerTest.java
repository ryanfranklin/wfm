package com.ryanfranklin.employee.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanfranklin.employee.exception.NotFoundException;
import com.ryanfranklin.employee.model.Employee;
import com.ryanfranklin.employee.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

  private static String URL_PATH = "/employee/";
  private static long EMPLOYEE_ID = 123456;
  private static String EMPLOYEE_FIRST_NAME = "ryan";
  private static String EMPLOYEE_LAST_NAME = "franklin";

  private static String EMPLOYEE_EMAIL = "rfranklin1998@gmail.com";
  private MockMvc mockMvc;

  @Mock
  private EmployeeService employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  private JacksonTester<Employee> jacksonEmployeeTester;
  private Employee employee;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
    mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice(new EmployeeControllerExceptionHandler())
        .build();

    employee = new Employee();
    employee.setId(EMPLOYEE_ID);
    employee.setFirstName(EMPLOYEE_FIRST_NAME);
    employee.setLastName(EMPLOYEE_LAST_NAME);
    employee.setEmail(EMPLOYEE_EMAIL);
  }


  @Test
  public void getEmployeeByIdSuccess() throws Exception {

    given(employeeService.getEmployeeById(employee.getId())).willReturn(employee);

    MockHttpServletResponse response = mockMvc.perform(get(URL_PATH + employee.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
        jacksonEmployeeTester.write(employee).getJson());
  }

  @Test
  public void getEmployeeByIdNotFound() throws Exception {

    given(employeeService.getEmployeeById(employee.getId())).willThrow(new NotFoundException());

    mockMvc.perform(get(URL_PATH + employee.getId())
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
  }

  @Test
  public void getEmployeeByIdMethodNotSupported() throws Exception {

    mockMvc.perform(patch(URL_PATH + employee.getId())
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
  }

  @Test
  public void updateEmployeeByIdSuccess() throws Exception {

    given(employeeService.updateEmployee(employee.getId(), employee)).willReturn(employee);

    MockHttpServletResponse response = mockMvc.perform(put(URL_PATH + employee.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employee).getJson()))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
        jacksonEmployeeTester.write(employee).getJson());
  }

  @Test
  public void updateEmployeeByIdNotFound() throws Exception {

    given(employeeService.updateEmployee(employee.getId(), employee))
        .willThrow(new NotFoundException());

    mockMvc.perform(put(URL_PATH + employee.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employee).getJson()))
        .andExpect(status().isNotFound());

  }

  @Test
  public void updateEmployeeByIdWithNullFirstName() throws Exception {

    Employee employeeBadJson = new Employee();
    employeeBadJson.setId(EMPLOYEE_ID);
    employeeBadJson.setLastName(EMPLOYEE_LAST_NAME);
    employeeBadJson.setEmail(EMPLOYEE_EMAIL);

    mockMvc.perform(put(URL_PATH + employeeBadJson.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employeeBadJson).getJson()))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateEmployeeByIdWithEmptyName() throws Exception {

    Employee employeeBadJson = new Employee();
    employeeBadJson.setId(EMPLOYEE_ID);
    employeeBadJson.setFirstName("");
    employeeBadJson.setLastName(EMPLOYEE_LAST_NAME);
    employeeBadJson.setEmail(EMPLOYEE_EMAIL);

    mockMvc.perform(put(URL_PATH + employeeBadJson.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employeeBadJson).getJson()))
        .andExpect(status().isBadRequest());

  }

  @Test
  public void createEmployeeByIdSuccess() throws Exception {

    given(employeeService.createEmployee(employee)).willReturn(employee);

    MockHttpServletResponse response = mockMvc.perform(post(URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employee).getJson()))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
        jacksonEmployeeTester.write(employee).getJson());
  }

  @Test
  public void createEmployeeByIdWithNullFirstName() throws Exception {

    Employee employeeBadJson = new Employee();
    employeeBadJson.setLastName(EMPLOYEE_LAST_NAME);
    employeeBadJson.setEmail(EMPLOYEE_EMAIL);

    mockMvc.perform(post(URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employeeBadJson).getJson()))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void createEmployeeByIdWithEmptyName() throws Exception {

    Employee employeeBadJson = new Employee();
    employeeBadJson.setFirstName("");
    employeeBadJson.setLastName(EMPLOYEE_LAST_NAME);
    employeeBadJson.setEmail(EMPLOYEE_EMAIL);

    mockMvc.perform(post(URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonEmployeeTester.write(employeeBadJson).getJson()))
        .andExpect(status().isBadRequest());

  }

  @Test
  public void deleteEmployeeByIdSuccess() throws Exception {

    doNothing().when(employeeService).deleteEmployeeById(employee.getId());

    mockMvc.perform(delete(URL_PATH + employee.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteEmployeeByIdNotFound() throws Exception {
    doThrow(new NotFoundException()).when(employeeService).deleteEmployeeById(employee.getId());

    mockMvc.perform(delete(URL_PATH + employee.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}