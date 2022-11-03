package com.example.service.elearning.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserNameDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailsId;
    private String username;
    private String password;
    private String role;

    @OneToOne(mappedBy = "userNameDetails")
    @JsonManagedReference(value = "userUserNameDetails")
    private Users users;

    @OneToOne(mappedBy = "userNameDetails")
    @JsonManagedReference(value = "trainerUserNameDetails")
    private Trainer trainer;
}
