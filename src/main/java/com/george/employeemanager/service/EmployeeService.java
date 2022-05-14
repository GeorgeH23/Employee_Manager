package com.george.employeemanager.service;

import com.george.employeemanager.exception.UserNotFoundException;
import com.george.employeemanager.domain.Employee;
import com.george.employeemanager.mapper.EmployeeMapper;
import com.george.employeemanager.model.EmployeeDTO;
import com.george.employeemanager.model.EmployeeListDTO;
import com.george.employeemanager.repositoy.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public EmployeeListDTO findAllEmployees() {
        List<EmployeeDTO> employeeDTOS = employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
        return new EmployeeListDTO(employeeDTOS);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found."));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
