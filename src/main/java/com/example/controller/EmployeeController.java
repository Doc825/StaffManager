package com.example.controller;

import com.example.dto.Employee;
import com.example.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeRepository repository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.getAllEmployees();
    }
    @GetMapping("/findByLastName/{condition}")
    public List<Employee> getByLastName(@PathVariable String condition) {
        return repository.getEmployeesBySomeString("last_name", condition);
    }
    @GetMapping("/findByFirstName/{condition}")
    public List<Employee> getByFirstName(@PathVariable String condition) {
        return repository.getEmployeesBySomeString("first_name", condition);
    }
    @GetMapping("/findByEmail/{condition}")
    public List<Employee> getByEmail(@PathVariable String condition) {
        return repository.getEmployeesBySomeString("email", condition);
    }
    @GetMapping("/findByPhone/{condition}")
    public List<Employee> getByPhone(@PathVariable String condition) {
        return repository.getEmployeesBySomeString("phone_number", condition);
    }
    @GetMapping("/findById/{condition}")
    public List<Employee> getById(@PathVariable Integer condition) {
        return repository.getEmployeesBySomeInt("employee_id", condition);
    }
    @GetMapping("/findBySalaryRange/{max},{min}")
    public List<Employee> getBySalaryRange(@PathVariable BigDecimal max,
                                           @PathVariable BigDecimal min) {
        return repository.getEmployeesBySalary("salary", max, min);
    }

}