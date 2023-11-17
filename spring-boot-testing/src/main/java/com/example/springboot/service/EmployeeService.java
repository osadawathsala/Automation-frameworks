package com.example.springboot.service;

import com.example.springboot.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public Employee saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
    public Optional<Employee> getEmployeeById(long id);
    public Employee updateEmployee(Employee updateEmployee);
    public void deleteEmployee(long id);
}
