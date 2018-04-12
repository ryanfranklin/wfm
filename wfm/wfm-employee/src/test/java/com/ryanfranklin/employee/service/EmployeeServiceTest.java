package com.ryanfranklin.employee.service;

import com.ryanfranklin.employee.model.Employee;
import com.ryanfranklin.employee.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private static String URL_PATH = "/employee/";
    private static long EMPLOYEE_ID = 123456;
    private static String EMPLOYEE_FIRST_NAME = "ryan";
    private static String EMPLOYEE_LAST_NAME = "franklin";

    private static String EMPLOYEE_EMAIL = "rfranklin1998@gmail.com";

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    private Employee employee;
    @Before
    public void setup() {
        employee = new Employee();
        employee.setId(EMPLOYEE_ID);
        employee.setFirstName(EMPLOYEE_FIRST_NAME);
        employee.setLastName(EMPLOYEE_LAST_NAME);
        employee.setEmail(EMPLOYEE_EMAIL);
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

        Employee result = employeeService.updateEmployee(employee.getId(), employee);

        assertThat(result).isEqualTo(employee);
    }

    @Test
    public void createEmployee() {
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertThat(result).isEqualTo(employee);
    }

    @Test
    public void deleteEmployeeById() {
        given(employeeRepository.findOne(employee.getId())).willReturn(employee);
        doNothing().when(employeeRepository).delete(employee.getId());

        try{
            employeeService.deleteEmployeeById(employee.getId());
        }
        catch(Exception e){
            fail("Should not have thrown any exception");
        }
    }

}