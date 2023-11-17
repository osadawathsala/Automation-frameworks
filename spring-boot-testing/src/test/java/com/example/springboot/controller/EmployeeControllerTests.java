package com.example.springboot.controller;

import com.example.springboot.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.springboot.service.EmployeeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest
public class EmployeeControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    //JUnit test for createEmployee method
    @DisplayName("JUnit test for createEmployee method")
    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnEmployee() throws Exception {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();

        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when - action or behavior we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
        );

        //then - verify the output
        response
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));

    }

    //JUnit test for get All Employees REST API
    @DisplayName("JUnit test for get All Employees REST API")
    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeeList() throws Exception {
        //given - precondition or setup
        List<Employee> listOffEmployees = new ArrayList<>();
        listOffEmployees.add(Employee
                .builder()
                .firstName("Nimal")
                .lastName("Perera")
                .email("nimal_perera@gmail.com")
                .build());

        listOffEmployees.add(Employee
                .builder()
                .firstName("Suranga")
                .lastName("Perera")
                .email("suranga_perera@gmail.com")
                .build());
        BDDMockito.given(employeeService.getAllEmployees()).willReturn(listOffEmployees);

        //when - action or behavior we are going to test
        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/employees"));

        //then - verify the output

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.size()",
                                CoreMatchers.is(listOffEmployees.size())));
    }

    //JUnit test for get employee by id REST API
    @DisplayName("JUnit test for get employee by id REST API")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        //given - precondition or setup
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();

        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

        //when - action or behavior we are going to test
        ResultActions response = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/{id}", employeeId));

        //then - verify the output
        response.andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andDo(
                        MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.firstName",
                                CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.lastName",
                                CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));

    }

    //JUnit test for get employee by id REST API
    @DisplayName("JUnit test for get employee by id REST API-invalid:id")
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        //given - precondition or setup
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();

        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

        //when - action or behavior we are going to test
        ResultActions response = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/{id}", employeeId));

        //then - verify the output
        response.andExpect(
                MockMvcResultMatchers.status().isNotFound()).andDo(
                MockMvcResultHandlers.print());

    }

    //JUnit test for update employee REST API - positive scenario
    @DisplayName("JUnit test for update employee REST API - positive scenario ")
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        //given - precondition or setup
        long employeeId = 1L;
        Employee savedEmployee = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();
        Employee updatedEmployee = Employee.builder()
                .firstName("Osadak")
                .lastName("Wathsalak")
                .email("osadaksliit@gmail.com")
                .build();
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(savedEmployee));
        BDDMockito.given(employeeService.updateEmployee(
                        ArgumentMatchers.any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when - action or behavior we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(updatedEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(updatedEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(updatedEmployee.getEmail())));
    }

    //JUnit test for update employee REST API - negative scenario
    @DisplayName("JUnit test for update employee REST API - negative scenario ")
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception {
        //given - precondition or setup
        long employeeId = 1L;
        Employee savedEmployee = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();
        Employee updatedEmployee = Employee.builder()
                .firstName("Osadak")
                .lastName("Wathsalak")
                .email("osadaksliit@gmail.com")
                .build();
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        BDDMockito.given(employeeService.updateEmployee(
                        ArgumentMatchers.any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when - action or behavior we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    //JUnit test for delete employee REST API
    @DisplayName("JUnit test for delete employee REST API")
    @Test
    public void givenEmployeeId_whenDeleteEmployeeById_then200() throws Exception{
        //given - precondition or setup
        long employeeId = 1L;
        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);

        //when - action or behavior we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}",employeeId));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
