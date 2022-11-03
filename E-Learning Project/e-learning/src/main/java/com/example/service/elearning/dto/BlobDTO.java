package com.example.service.elearning.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlobDTO {
    MultipartFile file;
    private Long blobId;
    private String blobType;
    private String section;
    private String itemHeading;
    private Long itemDuration;
    private String itemType;
    private Long courseId;
}
