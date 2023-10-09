package com.example.controller;

import com.example.dto.Job;
import com.example.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
@AllArgsConstructor
public class JobController {
    private JobRepository repository;
    @GetMapping
    public List<Job> getAllJobs() {
        return repository.getAllJobs();
    }
    @GetMapping("/{title}")
    public List<Job> getJobByTitle(@PathVariable String title) {
        return repository.getJobByTitle(title);
    }
}
