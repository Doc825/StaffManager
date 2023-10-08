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

    @GetMapping("/findByDepartment/{department}")
    public List<EmployeeJobView> getByDepartment(@PathVariable String department) {
        return repository.getEmployeeDetailsBySomeString("department_name", department);
    }

    @GetMapping("/findByJobTitle/{job}")
    public List<EmployeeJobView> getByJobTitle(@PathVariable String job) {
        return repository.getEmployeeDetailsBySomeString("job_title", job);
    }

    @GetMapping("/findByRegion/{region}")
    public List<EmployeeJobView> getByRegion(@PathVariable String region) {
        return repository.getEmployeeDetailsBySomeString("region_name", region);
    }

    @GetMapping("/findByCountryName/{country}")
    public List<EmployeeJobView> getByCountryName(@PathVariable String country) {
        return repository.getEmployeeDetailsBySomeString("country_name", country);
    }

    @GetMapping("/findByProvince/{province}")
    public List<EmployeeJobView> getByProvince(@PathVariable String province) {
        return repository.getEmployeeDetailsBySomeString("state_province", province);
    }

    @GetMapping("/findByCity/{city}")
    public List<EmployeeJobView> getByCity(@PathVariable String city) {
        return repository.getEmployeeDetailsBySomeString("city", city);
    }
}
