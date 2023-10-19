package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private BigDecimal salary;
    private String department;
    private String jobTitle;
    private String region;
    private String country;
    private String state;
    private String city;
    private BigDecimal commissionPercent;
}
