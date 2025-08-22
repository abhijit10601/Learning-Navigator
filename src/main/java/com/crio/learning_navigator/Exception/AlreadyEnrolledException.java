package com.crio.learning_navigator.Exception;
//student already enrolled in subject
public class AlreadyEnrolledException extends RuntimeException {
    public AlreadyEnrolledException(Long studentId, Long subjectId) {
        super("Student with id: " + studentId + " has already enrolled in subject");
    }
}