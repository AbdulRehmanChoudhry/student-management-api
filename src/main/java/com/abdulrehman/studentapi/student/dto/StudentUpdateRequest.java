package com.abdulrehman.studentapi.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

public record StudentUpdateRequest(
        @Schema(
                description = "Student first name",
                example = "Abdul"
        )
        @Size(min = 2, max = 30,
                message = "First name must be between 2 and 30 characters")
        String firstName,

        @Schema(
                description = "Student last name",
                example = "Choudhry"
        )
        @Size(min = 2, max = 30,
                message = "Last name must be between 2 and 30 characters")
        String lastName,

        @Schema(
                description = "Student email",
                example = "abdul@gmail.com"
        )
        @Email(message = "Invalid email format")
        String email,


        @Schema(
                description = "Indian mobile number",
                example = "9876543210"
        )
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Phone number must be a valid 10-digit Indian mobile number"
        )
        String phoneNumber

) {}