package com.abdulrehman.studentapi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;


@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student abdul = new Student(
                    "Abdul-Rehman",
                    "Choudhry",
                    "abdul@gmail.com",
                    "9876543210",
                    LocalDate.of(2004, OCTOBER, 20),
                    "Male",
                    "Mumbai Central, Mumbai"




            );
            Student ali = new Student(
                    "Mohammedali",
                    "Fateh",
                    "ali@gmail.com",
                    "9123456789",
                    LocalDate.of(2005, NOVEMBER, 18),
                    "Male",
                    "Goregaon, Mumbai"

            );
            repository.saveAll(
                    List.of(abdul, ali)
            );

        };
    }
}

