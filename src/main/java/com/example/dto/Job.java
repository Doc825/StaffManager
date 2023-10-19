package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
public class Job {
    private String id;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
