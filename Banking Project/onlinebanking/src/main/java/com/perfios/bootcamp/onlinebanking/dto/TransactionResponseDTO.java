package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class TransactionResponseDTO extends CommonServiceResponse {

    private Long transactionId;
    private Double amount;
    private Date date;
    private String status;
    private String message;
    private String transactionType;             //UPI, NEFT , RTGS
    private Long debitAccountNumber;
    private Long creditAccountNumber;
    private String creditClientName;
    private String debitClientName;
}
