package com.example.service.elearning.dto;

import com.example.service.elearning.util.CommonServiceResponse;
import lombok.Data;

@Data
public class AddCourseResponse extends CommonServiceResponse {
    private Long courseId;
    private Long trainerId;
    private String trainerName;
    private String courseName;
    private Long courseDuration;
    private String courseDescription;
    private String level;
}
