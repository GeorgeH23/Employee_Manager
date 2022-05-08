package com.george.employeemanager.service;

import com.george.employeemanager.model.Employee;
import com.george.employeemanager.repositoy.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        employee.setName("John Doe");
        employee.setJobTitle("Software Engineer");
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }
}
