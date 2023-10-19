package com.example.controller;

import com.example.dto.Location;
import com.example.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/locations")
@AllArgsConstructor
public class LocationController {
    private LocationRepository repository;

    @GetMapping
    public List<Location> getAllLocations() {
        return repository.getAllLocations();
    }

    @GetMapping("/{city}")
    public List<Location> getLocationsByName(@PathVariable String city) {
        return repository.getLocationsByCity(city);
    }
}
