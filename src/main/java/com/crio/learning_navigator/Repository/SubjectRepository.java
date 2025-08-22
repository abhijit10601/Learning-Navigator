package com.crio.learning_navigator.Repository;

import com.crio.learning_navigator.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> { }
