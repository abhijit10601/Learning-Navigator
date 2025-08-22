package com.crio.learning_navigator.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.learning_navigator.DTO.ExamDTO;
import com.crio.learning_navigator.Entity.Exam;
import com.crio.learning_navigator.Entity.Subject;
import com.crio.learning_navigator.Repository.ExamRepository;
import com.crio.learning_navigator.Repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;
    /**
     * Create a new exam.
     *
     * @param subjectId the ID of the subject to which the exam belongs
     * @param examDTO the exam data transfer object
     * @return the created exam DTO
     * @throws ResourceNotFoundException if the subject is not found
     */
    public ExamDTO createExam(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
    
        Exam exam = new Exam();
        exam.setExamName(subject.getSubjectName() + " EXAM"); // Construct examName
        exam.setSubject(subject); // Link the exam to the subject
    
        Exam saved = examRepository.save(exam);
        return modelMapper.map(saved, ExamDTO.class);
    }
    // public ExamDTO createExam(Long subjectId, ExamDTO examDTO) {
    //     //1
    //     // Subject subject = subjectRepository.findById(subjectId)
    //     //                                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
    //     // Exam exam = modelMapper.map(examDTO, Exam.class);
    //     // exam.setSubject(subject); // Link the exam to the subject
    //     // Exam savedExam = examRepository.save(exam);
    //     // return modelMapper.map(savedExam, ExamDTO.class);
       
    //     //2
    //     Subject subject = subjectRepository.findById(subjectId)
    //         .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
    //     Exam exam = new Exam();
    //     // Use subject name + " EXAM"
    //     exam.setExamName(subject.getSubjectName() + " EXAM");
    //     exam.setSubject(subject);
    //     Exam saved = examRepository.save(exam);
    //     return modelMapper.map(saved, ExamDTO.class);
    // }
    /**
     * Get an exam by its ID.
     *
     * @param examId the ID of the exam
     * @return the exam DTO
     * @throws ResourceNotFoundException if the exam is not found
     */
    public ExamDTO getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                                 .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));
        return modelMapper.map(exam, ExamDTO.class);
    }

    /**
     * Get all exams.
     *
     * @return a list of all exam DTOs
     */
    public List<ExamDTO> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream()
                    .map(exam -> modelMapper.map(exam, ExamDTO.class))
                    .collect(Collectors.toList());
    }
}
