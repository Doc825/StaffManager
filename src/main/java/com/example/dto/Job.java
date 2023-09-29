package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Job {
    private  Integer id;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
