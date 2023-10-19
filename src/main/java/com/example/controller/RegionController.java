package com.example.controller;

import com.example.dto.Region;
import com.example.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/regions")
@AllArgsConstructor
public class RegionController {
    private RegionRepository repository;

    @GetMapping()
    public List<Region> getAllRegions() {
        return repository.getAllRegions();
    }

    @GetMapping("/{name}")
    public List<Region> getRegionByName(@PathVariable String name) {
        return repository.getRegionByName(name);
    }
}
