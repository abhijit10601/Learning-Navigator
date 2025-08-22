package com.crio.learning_navigator.Controller;

import java.util.List;
import com.crio.learning_navigator.DTO.ExamDTO;
import com.crio.learning_navigator.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    // @PostMapping("/subjects/{subjectId}")
    // public ResponseEntity<ExamDTO> createExam(
    //         @PathVariable Long subjectId,
    //         @RequestBody ExamDTO examDTO) {
    //     ExamDTO createdExam = examService.createExam(subjectId, examDTO);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(createdExam);
    // }

//     @PostMapping("/subjects/{subjectId}")
// public ResponseEntity<Void> createExam(
//         @PathVariable Long subjectId,
//         @RequestBody ExamDTO examDTO) {
//     examService.createExam(subjectId, examDTO); // Call the service to create the exam
//     return ResponseEntity.status(HttpStatus.CREATED).build(); // Empty body with 201 Created
// }
// @PostMapping("/subjects/{subjectId}")
//     public ResponseEntity<ExamDTO> createExam(@PathVariable Long subjectId,@RequestBody ExamDTO examDTO) {
//         ExamDTO created = examService.createExam(subjectId,examDTO);
//         return ResponseEntity.status(HttpStatus.CREATED).body(created);
//     }

@PostMapping("/subjects/{subjectId}")
public ResponseEntity<ExamDTO> createExam(@PathVariable Long subjectId) {
    ExamDTO created = examService.createExam(subjectId);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}


@GetMapping("/{examId}")
public ResponseEntity<ExamDTO> getExamById(@PathVariable Long examId) {
    ExamDTO examDTO = examService.getExamById(examId);
    return ResponseEntity.ok(examDTO);
}


    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }
}