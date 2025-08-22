package com.crio.learning_navigator.Exception;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyEnrolledExamException.class)
    public ResponseEntity<Map<String,String>> handleAlreadyExamEnrolled(AlreadyEnrolledExamException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(AlreadyEnrolledException.class)
    public ResponseEntity<Map<String,String>> handleAlreadyEnrolled(AlreadyEnrolledException ex) {
        // 409 Conflict, JSON body with "message"
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}