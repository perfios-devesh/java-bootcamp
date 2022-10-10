package com.perfios.bootcamp.onlinebanking.dto;

import com.perfios.bootcamp.onlinebanking.util.CommonServiceResponse;
import lombok.Data;

@Data
public class LoanDetailsResponseDTO extends CommonServiceResponse {
    private Long loanId;
    private Double loanAmount;
    private Double loanPeriod;
    private Double emi;
    private Double roi;
    private Long accountNumber;
}
