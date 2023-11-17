package com.example.springboot.service;

import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.service.impl.EmployeeServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();
    }

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .id(1L)
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        //  BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or behavior we are going to test
        Employee saveEmployee = employeeService.saveEmployee(employee);

        //then - verify the output
        //Assertions.assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee.getId()).isEqualTo(1L);
    }

    //JUnit test for save employee operation which throws Exception
    @DisplayName("JUnit test for save employee operation which throws Exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowException() {
        //given - precondition or setup

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        //  BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        // given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or behavior we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        //then - verify the output

        verify(employeeRepository, never()).save(any(Employee.class));

    }

    //JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {
        //given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Nimal")
                .lastName("Perera")
                .email("nimalsliit@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        //when - action or behavior we are going to test

        List<Employee> returnedEmployeeList = employeeService.getAllEmployees();

        //then - verify the output

        assertThat(returnedEmployeeList).isNotNull();
        assertThat(returnedEmployeeList.size()).isEqualTo(2);

    }

    //JUnit test for getAllEmployees method that return empty list
    @DisplayName("JUnit test for getAllEmployees method that return empty list")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList() {
        //given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Nimal")
                .lastName("Perera")
                .email("nimalsliit@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when - action or behavior we are going to test

        List<Employee> returnedEmployeeList = employeeService.getAllEmployees();

        //then - verify the output

        assertThat(returnedEmployeeList).isEmpty();
        assertThat(returnedEmployeeList.size()).isEqualTo(0);

    }

    //JUnit test for getEmployeeById method
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {
        //given - precondition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        //when - action or behavior we are going to test
        Employee saveEmployee = employeeService.getEmployeeById(1L).get();

        //then - verify the output

        assertThat(saveEmployee).isNotNull();

    }

    //JUnit test for updateEmployee method
    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() {
        //given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("wathsalasliit@gmail.com");

        //when - action or behavior we are going to test

        Employee updatedEmployee = employeeService.updateEmployee(employee);

        //then - verify the output

        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getEmail()).isEqualTo("wathsalasliit@gmail.com");
    }

    //JUnit test for deleteEmployee method
    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing() {
        //given - precondition or setup
        long employeeId = 1L;
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        //when - action or behavior we are going to test
        employeeService.deleteEmployee(employeeId);

        //then - verify the output
        verify(employeeRepository,times(1)).deleteById(employeeId);

    }

}
