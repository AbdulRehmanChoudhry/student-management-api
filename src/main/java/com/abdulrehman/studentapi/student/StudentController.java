package com.abdulrehman.studentapi.student;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import com.abdulrehman.studentapi.student.dto.StudentRegistrationRequest;
import com.abdulrehman.studentapi.student.dto.StudentResponse;
import com.abdulrehman.studentapi.student.dto.StudentUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.abdulrehman.studentapi.exception.ApiErrorResponse;

@Tag(
        name = "Student Management",
        description = "REST APIs for managing student records"
)
@RestController
@RequestMapping(path = "api/v2/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }




    @Operation(
            summary = "Register New Student",
            description = "Creates a new student record."
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "Email or phone number already exists",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            )

    })
    @PostMapping
    public ResponseEntity<String> registerNewStudent(
            @Valid @RequestBody StudentRegistrationRequest request
    ) {

        studentService.addNewStudent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Student registered successfully");
    }



    @Operation(
            summary = "Delete Student",
            description = "Deletes a student by ID."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Student deleted successfully"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )

    })
    @DeleteMapping("{studentId}")
    public ResponseEntity<String> deleteStudent(

            @PathVariable Long studentId

    ) {

        studentService.deleteStudent(studentId);

        return ResponseEntity.ok("Student deleted successfully");
    }




    @Operation(
            summary = "Update Existing Student",
            description = "Updates an existing student."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "Email or phone number already exists"
            )

    })
    @PutMapping("{studentId}")
    public ResponseEntity<String> updateStudent(

            @PathVariable Long studentId,

            @Valid @RequestBody StudentUpdateRequest request

    ) {

        studentService.updateStudent(studentId, request);

        return ResponseEntity.ok("Student updated successfully");
    }




    @Operation(
            summary = "Get Students",
            description = """
Returns a paginated list of students with optional
sorting and dynamic filtering based on request parameters.
"""
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Students retrieved successfully"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request parameters"
            )

    })
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getStudents(

            @Parameter(
                    description = "Page number (starts from 0)",
                    example = "0"
            )
            @RequestParam(defaultValue = "0")
            int page,


            @Parameter(
                    description = "Number of records per page",
                    example = "5"
            )
            @RequestParam(defaultValue = "5")
            int size,


            @Parameter(
                    description = "Database field used for sorting",
                    example = "firstName"
            )
            @RequestParam(defaultValue = "id")
            String sortBy,


            @Parameter(
                    description = "Sorting direction",
                    example = "asc"
            )
            @RequestParam(defaultValue = "asc")
            String direction,


            @Parameter(
                    description = "Filter by first name (partial match)",
                    example = "Abdul"
            )
            @RequestParam(required = false)
            String firstName,


            @Parameter(
                    description = "Filter by last name (partial match)",
                    example = "Choudhry"
            )
            @RequestParam(required = false)
            String lastName,


            @Parameter(
                    description = "Filter by email address",
                    example = "abdul@gmail.com"
            )
            @RequestParam(required = false)
            String email,


            @Parameter(
                    description = "Filter by gender",
                    example = "Male"
            )
            @RequestParam(required = false)
            String gender,


            @Parameter(
                    description = "Filter by address",
                    example = "Mumbai"
            )
            @RequestParam(required = false)
            String address,


            @Parameter(
                    description = "Filter by phone number",
                    example = "9876543210"
            )
            @RequestParam(required = false)
            String phoneNumber

    ) {

        return ResponseEntity.ok(

                studentService.getStudents(

                page,
                size,
                sortBy,
                direction,

                firstName,
                lastName,
                email,
                gender,
                address,
                phoneNumber

                )
        );
    }

}
