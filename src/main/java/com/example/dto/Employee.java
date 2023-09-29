package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private Date hireDate;
    private BigDecimal salary;
    private BigDecimal commissionPc;

}
