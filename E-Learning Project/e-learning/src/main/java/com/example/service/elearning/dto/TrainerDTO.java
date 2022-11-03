package com.example.service.elearning.dto;

import com.example.service.elearning.domain.Course;
import lombok.Data;
import java.util.List;

@Data
public class TrainerDTO {
    private Long trainerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;
    private List<Course> courses;
}
