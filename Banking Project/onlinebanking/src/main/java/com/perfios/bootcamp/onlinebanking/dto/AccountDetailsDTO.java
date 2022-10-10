package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountDetailsDTO {
    private Long accountNumber;
    private String type;
    private Double balance;
    private String status;
    private Long branchId;
    private String branchName;
    private Long clientId;
    private String clientName;
}
