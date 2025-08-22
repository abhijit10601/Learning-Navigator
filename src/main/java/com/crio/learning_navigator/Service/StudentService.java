package com.crio.learning_navigator.Service;

//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.learning_navigator.DTO.ExamDTO;
//import javax.security.auth.Subject;
import com.crio.learning_navigator.DTO.StudentDTO;
import com.crio.learning_navigator.DTO.SubjectDTO;
import com.crio.learning_navigator.Entity.Exam;
import com.crio.learning_navigator.Entity.Student;
import com.crio.learning_navigator.Entity.Subject;
import com.crio.learning_navigator.Exception.AlreadyEnrolledExamException;
import com.crio.learning_navigator.Exception.AlreadyEnrolledException;
import com.crio.learning_navigator.Repository.ExamRepository;
import com.crio.learning_navigator.Repository.StudentRepository;
import com.crio.learning_navigator.Repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private ExamRepository examRepo;

    @Autowired
    private ModelMapper modelMapper;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }


    @Transactional
    public StudentDTO enrollInSubject(Long studentId, Long subjectId) {
        // Student student = studentRepo.findById(studentId)
        //     .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        // Subject subject = subjectRepo.findById(subjectId)
        //     .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
        // // Enroll the student in the subject (avoid duplicates)
        // if (!student.getEnrolledSubjects().contains(subject)) {
        //     student.getEnrolledSubjects().add(subject);
        //     student = studentRepo.save(student);
        // }
        
        // // Map the updated student to StudentDTO
        // return mapStudentToDTO(student);
        Student student = studentRepo.findById(studentId)
        .orElseThrow(() ->
            new RuntimeException("Student not found with ID: " + studentId));
    Subject subject = subjectRepo.findById(subjectId)
        .orElseThrow(() ->
            new RuntimeException("Subject not found with ID: " + subjectId));

    // avoid duplicated subject enrollment        
    boolean already = student.getEnrolledSubjects()
                             .stream()
                             .anyMatch(s -> s.getId().equals(subjectId));
    if (already) {
        throw new AlreadyEnrolledException(studentId, subjectId);
    }
    student.getEnrolledSubjects().add(subject);
    Student updated = studentRepo.save(student);
    // map to DTO
    StudentDTO dto = modelMapper.map(updated, StudentDTO.class);
    updated.getEnrolledSubjects().forEach(sub -> {
        dto.getEnrolledSubjects().add(modelMapper.map(sub, SubjectDTO.class));
    });
    return dto;
}
    
    /**
     * Helper method to map Student entity to StudentDTO with nested SubjectDTOs.
     */
    private StudentDTO mapStudentToDTO(Student student) {
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        // Map enrolledSubjects to SubjectDTO
        student.getEnrolledSubjects().forEach(subject -> {
            SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
            studentDTO.getEnrolledSubjects().add(subjectDTO);
        });
        return studentDTO;
    }

    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        return modelMapper.map(student, StudentDTO.class);
    }
    
    
   @Transactional
   public StudentDTO enrollInExam(Long studentId, Long examId) {
       Student student = studentRepo.findById(studentId)
           .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
       Exam exam = examRepo.findById(examId)
           .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));
       // Enroll the student in the exam (avoid duplicates)
       if (student.getEnrolledExams().contains(exam)) {
        throw new AlreadyEnrolledExamException(studentId, examId);   
       }

           student.getEnrolledExams().add(exam);
           student = studentRepo.save(student);
       // Map the updated student to StudentDTO
       return mapStudentToExamDTO(student);
   }
   private StudentDTO mapStudentToExamDTO(Student student) {
    StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
    // Map enrolledExams to ExamDTO
    student.getEnrolledExams().forEach(exam -> {
        ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
        studentDTO.getEnrolledExams().add(examDTO);
    });
    return studentDTO;
}


/*public StudentDTO enrollInSubject(Long studentId, Long subjectId) {
        Student student = studentRepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        Subject subject = subjectRepo.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
        student.getEnrolledSubjects().add(subject);
        Student updated = studentRepo.save(student);
        return modelMapper.map(updated, StudentDTO.class);
    }
*/
    // public StudentDTO enrollInSubject(Long studentId, Long subjectId) {
    //     Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
    //     Subject subject = subjectRepo.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found"));

    //     student.getEnrolledSubjects().add(subject);
    //     studentRepo.save(student);
    //     return modelMapper.map(student, StudentDTO.class);
    // }
   /* 
    public List<StudentDTO> getAllStudents() {
        // List<Student> students = studentRepo.findAll();
        // return students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
        List<Student> students = studentRepo.findAll();
    return students.stream()
                   .map(this::mapStudentToDTOAll) // Use mapStudentToDTO for nested mapping
                   .collect(Collectors.toList());
    }



    private StudentDTO mapStudentToDTOAll(Student student) {
    StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);

    // Map enrolledSubjects to SubjectDTO
    student.getEnrolledSubjects().forEach(subject -> {
        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        studentDTO.getEnrolledSubjects().add(subjectDTO);
    });

    // Map enrolledExams to ExamDTO
    student.getEnrolledExams().forEach(exam -> {
        ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
        studentDTO.getEnrolledExams().add(examDTO);
    });

    return studentDTO;
}*/







 
public List<StudentDTO> getAllStudents() {
    List<Student> students = studentRepo.findAll();
    // return students.stream()
    //                .map(this::mapStudentToDTOAll) // Use custom mapping method
    //                .collect(Collectors.toList());
    List<StudentDTO> studentDTOs = new ArrayList<>();
    // Use a loop to map each Student to StudentDTO
    for (Student student : students) {
        StudentDTO studentDTO = mapStudentToDTOAll(student);
        studentDTOs.add(studentDTO);
    }
    return studentDTOs;
}
private StudentDTO mapStudentToDTOAll(Student student) {
    StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
    // Map enrolledSubjects to SubjectDTO
    student.getEnrolledSubjects().forEach(subject -> {
        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        studentDTO.getEnrolledSubjects().add(subjectDTO);
    });
    // Map enrolledExams to ExamDTO
    student.getEnrolledExams().forEach(exam -> {
        ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
        studentDTO.getEnrolledExams().add(examDTO);
    });
    return studentDTO;
}

}