package com.crio.learning_navigator.DTO;

import lombok.Data;

@Data
public class ExamDTO {
    private Long id;
    private String examName;
    //private Long subjectId;--> not needed in dto transfer
}