package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewTransactionDTO {

    private Double amount;
    private String transactionType;         //NEFT,UPI,RTGS
    private Long debitAccountNumber;
    private Long creditAccountNumber;
    private String transactionPassword;
}
