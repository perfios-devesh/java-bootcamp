package com.example.service.elearning.dto;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private Long courseId;
    private Long trainerId;
    private String courseName;
    private String trainerName;
    private String courseDescription;
    private Long courseDuration;
    private String level;
    private List<Long> blobStoreIds;
}
