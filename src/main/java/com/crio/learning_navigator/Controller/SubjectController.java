package com.crio.learning_navigator.Controller;

import java.util.List;
import com.crio.learning_navigator.DTO.SubjectDTO;
import com.crio.learning_navigator.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) {
        SubjectDTO createdSubject = subjectService.createSubject(subjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long subjectId) {
        // Call the service to get the subject by ID
        SubjectDTO subjectDTO = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subjectDTO); // Return the subject with 200 OK
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
}