package com.abdulrehman.studentapi.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentRegistrationRequest(

        @Schema(
                description = "Student's first name",
                example = "Abdul"
        )
        @NotBlank(message = "First name is required")
        @Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
        String firstName,

        @Schema(
                description = "Student's last name",
                example = "Choudhry"
        )
        @NotBlank(message = "Last name is required")
        @Size(min = 3, max = 30,
                message = "Last name must be between 3 and 30 characters")
        String lastName,

        @Schema(
                description = "Student's email address",
                example = "abdul.choudhry@gmail.com"
        )
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @Schema(
                description = "10-digit Indian mobile number",
                example = "9876543210"
        )
        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Phone number must be a valid 10-digit Indian mobile number"
        )
        String phoneNumber,

        @Schema(
                description = "Date of birth (yyyy-MM-dd)",
                example = "2004-10-20"
        )
        @Past(message = "Date of birth must be in the past")
        LocalDate dob,

        @Schema(
                description = "Gender",
                example = "Male",
                allowableValues = {"Male", "Female", "Other"}
        )
        @NotBlank(message = "Gender is required")
        String gender,

        @Schema(
                description = "Residential address",
                example = "Jogeshwari West, Mumbai"
        )
        @NotBlank(message = "Address is required")
        @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
        String address

) {
}