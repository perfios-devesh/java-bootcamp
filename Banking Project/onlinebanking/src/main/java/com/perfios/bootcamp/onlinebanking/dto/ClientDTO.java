package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ClientDTO {
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
    private String profilePassword;
    private String transactionPassword;
    private String status;
}
