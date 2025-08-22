package com.crio.learning_navigator.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.learning_navigator.DTO.SubjectDTO;
import com.crio.learning_navigator.Entity.Subject;
import com.crio.learning_navigator.Exception.GlobalExceptionHandler;
import com.crio.learning_navigator.Repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;
    /**
     * Create a new subject.
     *
     * @param subjectDTO the subject data transfer object
     * @return the created subject DTO
     */
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDTO.class);
    }
    /**
     * Get a subject by its ID.
     *
     * @param subjectId the ID of the subject
     * @return the subject DTO
     * @throws ResourceNotFoundException if the subject is not found
     */
    public SubjectDTO getSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                                           .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
        return modelMapper.map(subject, SubjectDTO.class);
    }
    /**
     * Get all subjects.
     *
     * @return a list of all subject DTOs
     */
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                      .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                      .collect(Collectors.toList());
    }
}