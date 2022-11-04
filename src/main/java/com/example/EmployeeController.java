package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	private final EmployeeRepository repository;

	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
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
