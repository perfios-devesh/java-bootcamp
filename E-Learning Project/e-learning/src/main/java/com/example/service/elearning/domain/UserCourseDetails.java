package com.example.service.elearning.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class UserCourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userCourseDetailsId;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference(value = "courseUserCourseDetails")
    Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "userUserCourseDetails")
    Users users;

    private LocalDateTime registeredOn;
    private Long currentBlobId;
    private Double percentageCompleted;


}
