package com.example.service.elearning.util;

import lombok.Data;

@Data
public class CommonServiceResponse {
    private String message;
    private String errorMessage;
    private Boolean apiStatusSuccessful;
}
