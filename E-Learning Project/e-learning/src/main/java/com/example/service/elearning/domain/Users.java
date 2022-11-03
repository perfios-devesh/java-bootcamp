package com.example.service.elearning.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    @OneToMany(mappedBy = "users")
    @JsonBackReference(value = "userUserCourseDetails")
    private List<UserCourseDetails> userCourseDetails;

    @OneToOne
    @JoinColumn(name="userNameDetails")
    @JsonBackReference(value = "userUserNameDetails")
    private UserNameDetails userNameDetails;
}
