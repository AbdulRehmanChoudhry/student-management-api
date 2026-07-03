package com.abdulrehman.studentapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentManagementAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Student Management REST API")
                        .version("2.0.0")
                        .description("""
                                A professional Student Management REST API built using Spring Boot.

                                Features
                                • CRUD Operations
                                • DTO Validation
                                • Global Exception Handling
                                • Custom Exceptions
                                • Pagination
                                • Sorting
                                • Dynamic Search using JPA Specifications
                                • PostgreSQL Integration
                                """)
                        .contact(new Contact()
                                .name("Abdul Rehman Choudhry")
                                .email("abdulrehmanchowdhry.com")
                                .url("https://github.com/AbdulRehmanChoudhry"))
                        .license(new License()
                                .name("MIT License")));
    }
}