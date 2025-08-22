package com.crio.learning_navigator.Controller;

import java.util.List;
import com.crio.learning_navigator.DTO.StudentDTO;
import com.crio.learning_navigator.Entity.Student;
import com.crio.learning_navigator.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        StudentDTO dto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<StudentDTO> enrollInSubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
        StudentDTO updatedStudent = studentService.enrollInSubject(studentId, subjectId);
        return ResponseEntity.ok(updatedStudent);
    }

    // @PostMapping("/{studentId}/subjects/{subjectId}")
    // public ResponseEntity<StudentDTO> enrollInSubject(
    //         @PathVariable Long studentId,
    //         @PathVariable Long subjectId) {
    //     StudentDTO updatedStudent = studentService.enrollInSubject(studentId, subjectId);
    //     return ResponseEntity.ok(updatedStudent);
    // }

    // @PostMapping("/{studentId}/subjects/{subjectId}")
    // public ResponseEntity<StudentDTO> enrollInSubject(
    //         @PathVariable Long studentId, 
    //         @PathVariable Long subjectId) {
    //     StudentDTO updatedStudent = studentService.enrollInSubject(studentId, subjectId);
    //     return ResponseEntity.ok(updatedStudent);
    // }


    @PostMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<StudentDTO> enrollInExam(
            @PathVariable Long studentId,
            @PathVariable Long examId) {
        StudentDTO updatedStudent = studentService.enrollInExam(studentId, examId);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}