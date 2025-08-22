package com.crio.learning_navigator.Exception;

public class AlreadyEnrolledExamException extends RuntimeException {
    public AlreadyEnrolledExamException(Long studentId, Long examId) {
        super("Student with id: " + studentId + " has already enrolled for this particular exam with id: " + examId);
    }
}