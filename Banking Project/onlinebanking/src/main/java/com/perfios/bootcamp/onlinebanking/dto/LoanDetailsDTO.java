package com.perfios.bootcamp.onlinebanking.dto;

import lombok.Data;

@Data
public class LoanDetailsDTO {
    private Long loanId;
    private Double loanAmount;
    private Double loanPeriod;
    private Double emi;
    private Double roi;
    private Long accountNumber;
}
