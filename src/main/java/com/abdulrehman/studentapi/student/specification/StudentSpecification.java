package com.abdulrehman.studentapi.student.specification;

import com.abdulrehman.studentapi.student.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null || firstName.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + firstName.toLowerCase() + "%"
                );
    }

    public static Specification<Student> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null || lastName.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")),
                        "%" + lastName.toLowerCase() + "%"
                );
    }

    public static Specification<Student> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null || email.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%"
                );
    }

    public static Specification<Student> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                gender == null || gender.isBlank()
                        ? null
                        : criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("gender")),
                        gender.toLowerCase()
                );
    }

    public static Specification<Student> hasAddress(String address) {
        return (root, query, criteriaBuilder) ->
                address == null || address.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("address")),
                        "%" + address.toLowerCase() + "%"
                );
    }

    public static Specification<Student> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) ->
                phoneNumber == null || phoneNumber.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        root.get("phoneNumber"),
                        "%" + phoneNumber + "%"
                );
    }
}