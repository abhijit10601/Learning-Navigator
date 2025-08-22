package com.crio.learning_navigator.DTO;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import com.crio.learning_navigator.Entity.Exam;
import com.crio.learning_navigator.Entity.Subject;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private List<SubjectDTO> enrolledSubjects=new ArrayList<>();
    private List<ExamDTO> enrolledExams=new ArrayList<>();
}