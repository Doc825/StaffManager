package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CustomerView {
    private String firstName;
    private String lastName;
    private String departmentName;
    private String jobTitle;
    private String regionName;
    private String countryName;
    private String state;
    private String city;
}
