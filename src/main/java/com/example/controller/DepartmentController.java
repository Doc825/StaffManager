package com.example.controller;

import com.example.dto.Department;
import com.example.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentRepository repository;

    @GetMapping("/{departmentName}")
    public List<Department> getByName(@PathVariable String departmentName) {
        return repository.getDepartmentByName(departmentName);
    }
}
