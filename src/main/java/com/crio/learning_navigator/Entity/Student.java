package com.crio.learning_navigator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.crio.learning_navigator.DTO.ExamDTO;
import com.crio.learning_navigator.DTO.SubjectDTO;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @ManyToMany
    private List<Subject> enrolledSubjects;

    @ManyToMany
    private List<Exam> enrolledExams;
}