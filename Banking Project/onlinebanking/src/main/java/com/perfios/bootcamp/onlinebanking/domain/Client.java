package com.perfios.bootcamp.onlinebanking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String firstName;
    private String lastName;
    private String motherName;
    private String fatherName;
    private Long registeredMobileNumber;
    private Long alternateMobileNumber;
    private String email;
    private Long aadhaarNumber;
    private String panNumber;
    private String username;
    private String password;
    private String transactionPassword;
    private String profilePassword;
    private String status;              //status is to be updated when the user from bank, if allows it, and then he will assign a new account
    @OneToMany(mappedBy = "client")
    private List<Account> accountList;
}
