package com.example;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaObservabilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaObservabilityApplication.class, args);
    }

    // Pre-load data
    @Bean
    CommandLineRunner initData(EmployeeRepository repository, ObservationRegistry registry) { // <1>
        return args -> {
            Observation.createNotStarted("init-database", registry).observe(() -> { // <2>
                repository.save(new Employee("Frodo", "ring bearer"));
                repository.save(new Employee("Bilbo", "burglar"));
            });
        };
    }
}
