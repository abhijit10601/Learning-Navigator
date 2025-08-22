package com.crio.learning_navigator.Repository;

import com.crio.learning_navigator.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> { }