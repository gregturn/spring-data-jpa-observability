package com.example;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final ObservationRegistry registry;

    public EmployeeController(EmployeeRepository repository, ObservationRegistry registry) {
        this.repository = repository;
        this.registry = registry;
    }

    @GetMapping("/api/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @GetMapping("/api/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id) //
            .orElseThrow(() -> new RuntimeException("User " + id + " not found!"));
    }
}
