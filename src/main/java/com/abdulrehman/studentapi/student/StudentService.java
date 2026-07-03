package com.abdulrehman.studentapi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.abdulrehman.studentapi.student.dto.StudentRegistrationRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.abdulrehman.studentapi.student.dto.StudentResponse;
import com.abdulrehman.studentapi.exception.DuplicateResourceException;
import com.abdulrehman.studentapi.exception.ResourceNotFoundException;
import com.abdulrehman.studentapi.student.dto.StudentUpdateRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import com.abdulrehman.studentapi.student.specification.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<StudentResponse> getStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponse)
                .toList();
    }
    private StudentResponse mapToStudentResponse(Student student) {

        return new StudentResponse(

                student.getId(),

                student.getFirstName() + " " + student.getLastName(),

                student.getEmail(),

                student.getPhoneNumber(),

                student.getGender(),

                student.getAddress(),

                student.getAge()
        );
    }

    public void addNewStudent(StudentRegistrationRequest request) {
        Student student = new Student(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber(),
                request.dob(),
                request.gender(),
                request.address()
        );
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(
                        request.email());
        if (studentOptional.isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }
        Optional<Student> phoneOptional =
                studentRepository.findStudentByPhoneNumber(
                        request.phoneNumber());
        if(phoneOptional.isPresent()) {
            throw new DuplicateResourceException("Phone number already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
       boolean exists = studentRepository.existsById(studentID);
       if(!exists){
           throw new ResourceNotFoundException(
                   "Student with id " + studentID + " does not exist"
           );
       }
       studentRepository.deleteById(studentID);
    }
    @Transactional
    public void updateStudent(
            Long studentId,
            StudentUpdateRequest request
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with id " + studentId + " does not exist"
                        ));

        if(request.firstName() != null &&
                !request.firstName().isBlank() &&
                !Objects.equals(student.getFirstName(), request.firstName())) {

            student.setFirstName(request.firstName());
        }

        if(request.lastName() != null &&
                !request.lastName().isBlank() &&
                !Objects.equals(student.getLastName(), request.lastName())) {

            student.setLastName(request.lastName());
        }

        if(request.email() != null &&
                !request.email().isBlank() &&
                !Objects.equals(student.getEmail(), request.email())) {

            Optional<Student> studentOptional =
                    studentRepository.findStudentByEmail(request.email());

            if(studentOptional.isPresent()) {
                throw new DuplicateResourceException("Email already exists");
            }


            student.setEmail(request.email());
        }

        if(request.phoneNumber() != null &&
                !request.phoneNumber().isBlank() &&
                !Objects.equals(student.getPhoneNumber(), request.phoneNumber())) {

            Optional<Student> phoneOptional =
                    studentRepository.findStudentByPhoneNumber(request.phoneNumber());

            if(phoneOptional.isPresent()) {
                throw new DuplicateResourceException("Phone number already exists");
            }

            student.setPhoneNumber(request.phoneNumber());
        }

    }
    public Page<StudentResponse> getStudents(
            int page,
            int size,
            String sortBy,
            String direction,
            String firstName,
            String lastName,
            String email,
            String gender,
            String address,
            String phoneNumber
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Student> specification =
                Specification.where(StudentSpecification.hasFirstName(firstName))
                        .and(StudentSpecification.hasLastName(lastName))
                        .and(StudentSpecification.hasEmail(email))
                        .and(StudentSpecification.hasGender(gender))
                        .and(StudentSpecification.hasAddress(address))
                        .and(StudentSpecification.hasPhoneNumber(phoneNumber));

        Page<Student> studentPage =
                studentRepository.findAll(specification, pageable);

        return studentPage.map(this::mapToStudentResponse);
    }
    
}