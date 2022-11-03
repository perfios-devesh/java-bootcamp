package com.example.service.elearning.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    @OneToMany(mappedBy = "trainer")
    @JsonManagedReference(value = "courseTrainer")
    private List<Course> course;

    @OneToOne
    @JoinColumn(name="userNameDetails")
    @JsonBackReference(value = "trainerUserNameDetails")
    private UserNameDetails userNameDetails;
}
