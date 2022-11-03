package com.example.service.elearning.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String courseDescription;
    private Long courseDuration;
    private String level;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<BlobStore> blobStores;

    @ManyToOne
    @JoinColumn(name="trainer")
    @JsonBackReference(value = "courseTrainer")
    private Trainer trainer;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference(value = "courseUserCourseDetails")
    private List<UserCourseDetails> userCourseDetails;
}
