package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Location {
    private Integer id;
    private String street;
    private String postalCode;
    private String city;
    private String stateProvince;
}
