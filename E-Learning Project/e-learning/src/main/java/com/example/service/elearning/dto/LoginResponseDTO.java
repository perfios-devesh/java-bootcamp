package com.example.service.elearning.dto;

import com.example.service.elearning.util.CommonServiceResponse;
import lombok.Data;

@Data
public class LoginResponseDTO extends CommonServiceResponse {
    private String username;
    private String name;
    private Long userId;
    private Long trainerId;
    private String role;
}
