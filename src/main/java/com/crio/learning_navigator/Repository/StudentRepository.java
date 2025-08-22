package com.crio.learning_navigator.Repository;

import java.util.Optional;
import com.crio.learning_navigator.Entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> { 


    @Override
    @EntityGraph(attributePaths = {"enrolledSubjects", "enrolledExams"})
    Optional<Student> findById(Long id);
}
