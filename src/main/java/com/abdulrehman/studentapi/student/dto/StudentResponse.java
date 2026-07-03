package com.abdulrehman.studentapi.student.dto;
import io.swagger.v3.oas.annotations.media.Schema;
public record StudentResponse(

        @Schema(example = "1")
        Long id,

        @Schema(example = "Abdul Rehman Choudhry")
        String fullName,

        @Schema(example = "abdul@gmail.com")
        String email,

        @Schema(example = "9876543210")
        String phoneNumber,

        @Schema(example = "Male")
        String gender,

        @Schema(example = "Mumbai")
        String address,

        @Schema(example = "21")
        Integer age

) {}