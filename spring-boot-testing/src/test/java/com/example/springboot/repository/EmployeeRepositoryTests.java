package com.example.springboot.repository;


import com.example.springboot.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    public void setup(){

        employee = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();
    }

    //JUnit test for save Employee operation
    @DisplayName("JUnit test for save Employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSaveEmployeeObject() {

        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();

        //when - action or behavior we are going to test
        Employee saveEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for find all employee operation")
    //JUnit test for
    @Test
    public void givenEmployeeList_whenFindAll_thenReturnEmployeeList() {

        //given - precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Osada")
                .lastName("Wathsala")
                .email("osadasliit@gmail.com")
                .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when - action or behavior we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or behavior we are going to test
        Employee retrieveEmployee = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(retrieveEmployee).isNotNull();

    }

    //JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or behavior we are going to test
        Employee retrieveEmployee = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
        assertThat(retrieveEmployee).isNotNull();
    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or behavior we are going to test
        Employee saveEmployee = employeeRepository.findById(employee.getId()).get();
        saveEmployee.setEmail("osadawathsala@gmail.com");
        saveEmployee.setLastName("Kalubadanage");

        Employee updatedEmployee = employeeRepository.save(saveEmployee);

        //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("osadawathsala@gmail.com");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Kalubadanage");
    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or behavior we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        //then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    //JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

        //when - action or behavior we are going to test
        Employee retrievedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        //then - verify the output
        assertThat(retrievedEmployee).isNotNull();
        assertThat(retrievedEmployee.getFirstName()).isEqualTo(firstName);
        assertThat(retrievedEmployee.getLastName()).isEqualTo(lastName);
    }

    //JUnit test for custom query using JPQL with name params
    @DisplayName("JUnit test for custom query using JPQL with name params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLWithNameParams_thenReturnEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

        //when - action or behavior we are going to test
        Employee retrievedEmployee = employeeRepository.findByJPQLWithNameParams(firstName, lastName);

        //then - verify the output
        assertThat(retrievedEmployee).isNotNull();
        assertThat(retrievedEmployee.getFirstName()).isEqualTo(firstName);
        assertThat(retrievedEmployee.getLastName()).isEqualTo(lastName);
    }

    //JUnit test for custom query using Native SQL query with index params
    @DisplayName("JUnit test for custom query using Native SQL query with index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLWithIndexParams_thenReturnEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();

        //when - action or behavior we are going to test
        Employee retrievedEmployee = employeeRepository.findByNativeSQL(firstName, lastName);

        //then - verify the output
        assertThat(retrievedEmployee).isNotNull();
        assertThat(retrievedEmployee.getFirstName()).isEqualTo(firstName);
        assertThat(retrievedEmployee.getLastName()).isEqualTo(lastName);
    }

    //JUnit test for custom query using Native SQL query with name params
    @DisplayName("JUnit test for custom query using Native SQL query with names ")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLWithNameParams_thenReturnEmployee() {
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Osada")
//                .lastName("Wathsala")
//                .email("osadasliit@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or behavior we are going to test
        Employee retrievedEmployee = employeeRepository.findByNativeSQLWithNameParams(employee.getFirstName(),employee.getLastName());

        //then - verify the output
        assertThat(retrievedEmployee).isNotNull();
    }
}
