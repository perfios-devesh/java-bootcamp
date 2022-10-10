package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;

@Data
public class ClientAllPasswordsDTO {
    private Long clientId;
    private String password;
    private String profilePassword;
    private String transactionPassword;
}
