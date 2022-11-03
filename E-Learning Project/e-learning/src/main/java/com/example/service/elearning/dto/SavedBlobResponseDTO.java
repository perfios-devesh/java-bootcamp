package com.example.service.elearning.dto;

import com.example.service.elearning.util.CommonServiceResponse;
import lombok.Data;

@Data
public class SavedBlobResponseDTO extends CommonServiceResponse {
    private Long blobId;
}
