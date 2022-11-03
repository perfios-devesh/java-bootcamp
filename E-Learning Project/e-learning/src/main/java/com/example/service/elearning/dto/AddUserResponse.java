package com.example.service.elearning.dto;

import com.example.service.elearning.util.CommonServiceResponse;
import lombok.Data;

@Data
public class AddUserResponse extends CommonServiceResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;
}
