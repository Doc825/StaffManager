package com.example.controller;

import com.example.dto.Country;
import com.example.repository.CountriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/countries")
public class CountryController {
    private CountriesRepository repository;

    @GetMapping
    public List<Country> getAllCountries() {
        return repository.getAllCountries();
    }

    @GetMapping("/{countryName}")
    public List<Country> getCountryByName(@PathVariable String countryName) {
        return repository.getCountryByName(countryName);
    }
}
