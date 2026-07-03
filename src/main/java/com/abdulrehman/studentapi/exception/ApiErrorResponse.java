package com.abdulrehman.studentapi.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API Error Response")
public record ApiErrorResponse(

        @Schema(
                description = "Timestamp when the error occurred",
                example = "2026-07-03T18:30:45"
        )
        String timestamp,

        @Schema(
                description = "HTTP status code",
                example = "409"
        )
        int status,

        @Schema(
                description = "HTTP error",
                example = "Conflict"
        )
        String error,

        @Schema(
                description = "Detailed error message",
                example = "Email already exists"
        )
        String message,

        @Schema(
                description = "Request path",
                example = "/api/v2/student"
        )
        String path

) {}