package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class Employee {
    @Value("${defaultValue}")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @Value("${defaultValue}")
    private String phoneNum;
    @Value("${defaultValue}")
    private Date hireDate;
    @Value("${defaultValue}")
    private BigDecimal salary;
    @Value("${defaultValue}")
    private BigDecimal commissionPc;

}
