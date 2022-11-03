package com.example.service.elearning.dto;

import com.example.service.elearning.util.CommonServiceResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDetailsDTO extends CommonServiceResponse {
    private String courseName;
    private Long trainerId;
    private String trainerName;
    private String courseDescription;
    private Long courseDuration;
    private String level;
    List<BlobDTO> blobDTOS;
    private LocalDateTime registeredOn;
    private Long currentBlobId;
    private Double percentageCompleted;
}
