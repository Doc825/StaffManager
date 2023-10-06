package com.example.controller;

import com.example.dto.EmployeeJobView;
import com.example.repository.EmployeeJobViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employeeDetails")
@AllArgsConstructor
public class EmployeeJobViewController {
    private EmployeeJobViewRepository repository;

    @GetMapping
    public List<EmployeeJobView> getAllEmployeesDetails() {
        return repository.getAllEmployeesDetails();
    }

    @GetMapping("/findByFirstName/{firstName}")
    public List<EmployeeJobView> getByName(@PathVariable String firstName) {
        return repository.getEmployeeDetailsByPartOfName("first_name", firstName);
    }

    @GetMapping("/findByLastName/{lastName}")
    public List<EmployeeJobView> getByLastName(@PathVariable String lastName) {
        return repository.getEmployeeDetailsByPartOfName("last_name", lastName);
    }
}
