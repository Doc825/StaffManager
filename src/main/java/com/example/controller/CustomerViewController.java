package com.example.controller;

import com.example.dto.CustomerView;
import com.example.repository.CustomerViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employeeDetails")
@AllArgsConstructor
public class CustomerViewController {
    private CustomerViewRepository repository;

    @GetMapping
    public List<CustomerView> getAllEmployeesDetails() {
        return repository.getAllEmployeesDetails();
    }

    @GetMapping("/findByFirstName/{firstName}")
    public List<CustomerView> getByName(@PathVariable String firstName) {
        return repository.getEmployeeDetailsByPartOfName("first_name", firstName);
    }

    @GetMapping("/findByLastName/{lastName}")
    public List<CustomerView> getByLastName(@PathVariable String lastName) {
        return repository.getEmployeeDetailsByPartOfName("last_name", lastName);
    }

    @GetMapping("/findByDepartment/{department}")
    public List<CustomerView> getByDepartment(@PathVariable String department) {
        return repository.getEmployeeDetailsBySomeString("department_name", department);
    }

    @GetMapping("/findByJobTitle/{job}")
    public List<CustomerView> getByJobTitle(@PathVariable String job) {
        return repository.getEmployeeDetailsBySomeString("job_title", job);
    }

    @GetMapping("/findByRegion/{region}")
    public List<CustomerView> getByRegion(@PathVariable String region) {
        return repository.getEmployeeDetailsBySomeString("region_name", region);
    }

    @GetMapping("/findByCountryName/{country}")
    public List<CustomerView> getByCountryName(@PathVariable String country) {
        return repository.getEmployeeDetailsBySomeString("country_name", country);
    }

    @GetMapping("/findByProvince/{province}")
    public List<CustomerView> getByProvince(@PathVariable String province) {
        return repository.getEmployeeDetailsBySomeString("state_province", province);
    }

    @GetMapping("/findByCity/{city}")
    public List<CustomerView> getByCity(@PathVariable String city) {
        return repository.getEmployeeDetailsBySomeString("city", city);
    }
}
