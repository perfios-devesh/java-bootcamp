package com.example.service.elearning.dto;

import lombok.Data;

import java.util.List;

@Data
public class RemoveBlobsDTO {
    private Long courseId;
    private List<Long> toKeepIds;
}
